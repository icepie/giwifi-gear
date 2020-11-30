#!/usr/bin/env sh

# config
GW_GTW="172.21.1.2"
GW_USER=""
GW_PWD=""
GW_BTYPE="pc"
UA="Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36"

# url
url_encode() {
    # urlencode <string>

    local length="${#1}"
    for i in $(seq $length); do
        local c="${1:$i-1:1}"
        case $c in
            [a-zA-Z0-9.~_-]) printf '%s' "$c" ;;
            *) printf '%%%02X' "'$c" ;;
        esac
    done

}


url_decode() {
    # urldecode <string>

    local url_encoded="${1//+/ }"
    printf '%b' "${url_encoded//%/\\x}"

}

# str
str_str() {
    #str_str <string> "str" "str"

    local str
    str="${1#*${2}}"
    str="${str%%$3*}"
    echo -n "$str"
}

# json
json_format() {
    # Del escape character and format text
    echo $(echo -e $1) | sed "s@\\\\@@g"
}

get_json_value()
{
    awk -v json="$1" -v key="$2" -v defaultValue=$3 'BEGIN{
        foundKeyCount = 0
        while (length(json) > 0) {
            # pos = index(json, "\""key"\""); ## This line is faster, but if a value is a string that happens to be the same as the key you are looking for, it will be mistaken for a key and the value will be fetched incorrectly.
            pos = match(json, "\""key"\"[ \\t]*?:[ \\t]*");
            if (pos == 0) {if (foundKeyCount == 0) {print defaultValue;} exit 0;}

            ++foundKeyCount;
            start = 0; stop = 0; layer = 0;
            for (i = pos + length(key) + 1; i <= length(json); ++i) {
                lastChar = substr(json, i - 1, 1)
                currChar = substr(json, i, 1)

                if (start <= 0) {
                    if (lastChar == ":") {
                        start = currChar == " " ? i + 1: i;
                        if (currChar == "{" || currChar == "[") {
                            layer = 1;
                        }
                    }
                } else {
                    if (currChar == "{" || currChar == "[") {
                        ++layer;
                    }
                    if (currChar == "}" || currChar == "]") {
                        --layer;
                    }
                    if ((currChar == "," || currChar == "}" || currChar == "]") && layer <= 0) {
                        stop = currChar == "," ? i : i + 1 + layer;
                        break;
                    }
                }
            }

            if (start <= 0 || stop <= 0 || start > length(json) || stop > length(json) || start >= stop) {
                if (foundKeyCount == 0) {print defaultValue;} exit 0;
            } else {
                print substr(json, start, stop - start);
            }

            json = substr(json, stop + 1, length(json) - stop)
        }
    }'
}

# giwifi api
gw_get_gtw_auth()
{
	echo $(json_format "$(curl -s -A "$UA" "$1/getApp.htm?action=getAuthState&os=mac")")
}

gw_logout()
{
	echo $(json_format "$(curl -s -A "$UA" "$1/getApp.htm?action=logout")")
}

gw_get_auth_url()
{
    echo $(curl -s -I -A "$UA" "http://$1:8062/redirect?oriUrl=http://www.baidu.com" | grep "Location" | awk -F ": " '{print $2}')
}

gw_get_login_page()
{
    echo $(curl -s -L -A "$UA" "http://$1:8062/redirect?oriUrl=http://www.baidu.com" | grep "name=")
}

gw_get_hotspot_group()
{
    echo $(json_format "$(curl -s -A "$UA" "http:/$1:$2/wifidog/get_hotspot_group")")
}

gw_get_auth_state()
{
    echo $(json_format "$(curl -s -A "$UA" "http://$1:$2/wifidog/get_auth_state")")
}

gw_loginaction()
{
    # create random three-digit numbers
    local str=$(date +%S%M)
    local rannum=${str:1:3}

    echo $(json_format "$(curl -s  \
    -A "$UA" \
    -X POST \
    -H 'Accept: */*' \
    -H 'Connection: keep-alive' \
    -H 'Content-Type: application/x-www-form-urlencoded' \
    -H 'accept-encoding: gzip, deflate, br' \
    -H 'accept-language: zh-CN,zh-TW;q=0.8,zh;q=0.6,en;q=0.4,ja;q=0.2' \
    -H 'cache-control: max-age=0' \
    -d "$1" \
    "http://login.gwifi.com.cn/cmps/admin.php/api/loginaction?round=$rannum" | gunzip)")
    
}

gw_rebindmac()
{
    # create random three-digit numbers
    local str=$(date +%S%M)
    local rannum=${str:1:3}

    echo $(json_format "$(curl -s  \
    -A "$UA" \
    -X POST \
    -H 'Accept: */*' \
    -H 'Connection: keep-alive' \
    -H 'Content-Type: application/x-www-form-urlencoded' \
    -H 'accept-encoding: gzip, deflate, br' \
    -H 'accept-language: zh-CN,zh-TW;q=0.8,zh;q=0.6,en;q=0.4,ja;q=0.2' \
    -H 'cache-control: max-age=0' \
    -d "$1" \
    "http://login.gwifi.com.cn/cmps/admin.php/api/reBindMac?round=$rannum" | gunzip)")
    
}

gw_auth_token()
{
    echo $(json_format "$(curl -s -A "$UA" "$1")")
}

# main
(
    # gtw auth auth
    GW_GTW_AUTH=$(gw_get_gtw_auth $GW_GTW)

    echo GW_GTW_AUTH:
    echo '-->' $GW_GTW_AUTH
    echo ''


    # get auth url
    GW_ATUH_URL=$(gw_get_auth_url $GW_GTW)
    echo GW_ATUH_URL:
    echo '-->' $GW_ATUH_URL
    echo ''

    GW_PORT=$(str_str "$GW_ATUH_URL" "gw_port=" "&")


    # get login page
    GW_LOGIN_PAGE=$(gw_get_login_page $GW_GTW)
    echo $GW_LOGIN_PAGE

    echo GW_LOGIN_PAGE:
    echo '-->' $GW_LOGIN_PAGE
    echo ''
    echo ''

    # get some values from login page
    GW_SIGN=$(str_str "$GW_LOGIN_PAGE" 'name="sign" value="' '"')
    GW_PAGE_TIME=$(str_str "$GW_LOGIN_PAGE" 'name="page_time" value="' '"')


    echo GW_SIGN: $GW_SIGN
    echo GW_PAGE_TIME: $GW_PAGE_TIME
    echo ''
 
    # get sauth state json
    GW_AUTH_STATE=$(gw_get_auth_state $GW_GTW $GW_PORT)
    GW_AUTH_STATE_DATA=$(get_json_value $GW_AUTH_STATE 'data')
    GW_ID=$(get_json_value $GW_AUTH_STATE_DATA 'gw_id' | sed 's/"//g')

    
    echo GW_AUTH_STATE:
    echo '-->' $GW_AUTH_STATE
    echo ''
    echo GW_ID: $GW_ID
    echo ''


    # login to get the auth token
    GW_LOGIN_DATA="""\
access_type=$(get_json_value $GW_AUTH_STATE_DATA 'access_type' | sed 's/"//g')\
&acsign=$(get_json_value $GW_AUTH_STATE_DATA 'sign' | sed 's/"//g')\
&btype=$GW_BTYPE\
&client_mac=$(url_encode $(get_json_value $GW_AUTH_STATE_DATA 'client_mac' | sed 's/"//g'))\
&contact_phone=$(get_json_value $GW_AUTH_STATE_DATA 'contact_phone' | sed 's/"//g')\
&devicemode=""\
&gw_address=$(str_str "$GW_ATUH_URL" "gw_address=" "&")\
&gw_id=$(str_str "$GW_ATUH_URL" "gw_id=" "&")\
&gw_port=$(str_str "$GW_ATUH_URL" "gw_port=" "&")\
&lastaccessurl=""\
&logout_reason=$(get_json_value $GW_AUTH_STATE_DATA 'logout_reason' | sed 's/"//g')\
&mac=$(url_encode $(str_str "$GW_ATUH_URL" "mac=" "&"))\
&name=$GW_USER\
&online_time=$(get_json_value $GW_AUTH_STATE_DATA 'online_time' | sed 's/"//g')\
&page_time=$GW_PAGE_TIME\
&password=$GW_PWD\
&sign=$(url_encode $GW_SIGN)\
&station_cloud=$(get_json_value $GW_AUTH_STATE_DATA 'station_cloud' | sed 's/"//g')\
&station_sn=$(get_json_value $GW_AUTH_STATE_DATA 'station_sn' | sed 's/"//g')\
&suggest_phone=$(get_json_value $GW_AUTH_STATE_DATA 'suggest_phone' | sed 's/"//g')\
&url=$(str_str "$GW_ATUH_URL" "url=" "&")\
&user_agent=""\
"""

    echo GW_LOGIN_DATA: $GW_LOGIN_DATA
    echo ''

    GW_LOGIN_RT_DATA=$(gw_loginaction $GW_LOGIN_DATA)
    echo $GW_LOGIN_RT_DATA

    GW_LOGIN_RT_DATA_INFO=$(get_json_value "$GW_LOGIN_RT_DATA" 'info' | sed 's/"//g')
    echo ''
    echo $GW_LOGIN_RT_DATA_INFO

    # the last step
    gw_auth_token $GW_LOGIN_RT_DATA_INFO

    # gtw auth auth
    GW_GTW_AUTH=$(gw_get_gtw_auth $GW_GTW)

    echo ''
    echo GW_GTW_AUTH:
    echo '-->' $GW_GTW_AUTH
    echo ''

)

