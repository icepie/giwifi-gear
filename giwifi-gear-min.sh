#!/usr/bin/env sh

# config
GW_GTW=172.21.1.2
GW_USER=
GW_PWD=

PC_UA="Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36"


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

# giwifi api
gw_get_auth_url()
{
    echo $(curl -s -I -A "$PC_UA" "http://$1:8062/redirect?oriUrl=http://www.baidu.com" | grep "Location" | awk -F ": " '{print $2}')
}

gw_get_login_page()
{
    echo $(curl -s -L -A "$PC_UA" "http://$1:8062/redirect?oriUrl=http://www.baidu.com" | grep "name=" | grep "<input")
}

gw_get_auth_state()
{
    echo $(json_format "$(curl -s -A "$PC_UA" "http://$1:$2/wifidog/get_auth_state")")
}


# main
(
    echo $GW_GTW
    echo $GW_USER

    GW_ATUH_URL=$(gw_get_auth_url $GW_GTW)
    echo GW_ATUH_URL:
    echo '-->' $GW_ATUH_URL
    echo ''

    echo ''
    GW_PORT=$(str_str "$GW_ATUH_URL" "gw_port=" "&" )

    GW_LOGIN_PAGE=$(gw_get_login_page $GW_GTW)

    #echo $GW_LOGIN_PAGE

    GW_SIGN=$(url_encode $(str_str "$GW_LOGIN_PAGE" 'name="sign" value="' '"' ))
    GW_PAGE_TIME=$(str_str "$GW_LOGIN_PAGE" 'name="page_time" value="' '"' )
    GW_AUTH_STATE=$(gw_get_auth_state $GW_GTW $GW_PORT)

    echo $GW_SIGN
    echo $GW_PAGE_TIME

	echo $GW_AUTH_STATE

)
