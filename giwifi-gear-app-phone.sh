#!/usr/bin/env bash
# giwifi-gear-phone bash cli tool 
# by icepie

#############################################
## config
#############################################

GW_GTW='172.21.1.4'
GW_USER=''
GW_PWD=''
GW_SERVICE_TYPE='1' # 1: GiWiFi用户 2: 移动用户 3: 联通用户 4: 电信用户
GW_STA_TYPE='phone' # pad or phone
GW_STA_MODEL='Xiaomi,Redmi K20 Pro Premium Edition,30,11' # MANUFACTURER,MODEL,SDK,RELEASE;
GW_PHONE_UA='(GiWiFi;Android11;Xiaomi;Redmi K20 Pro Premium Edition)' # (GiWiFi;iPhone OS 10_3;Apple;iPad11,3)
GW_PHONE_IM='00000000-023e-0e94-ffff-ffffef05ac4a' # IMEI UUID
GW_PHONE_IS_INSTALL_WX='1' # is installed WeChat?
GW_PHONE_APP_AUTH_MODE='1'
GW_PHONE_APP_IMSI='00000000-6142-4378-a94954' # xxx-xxx-xxx-xxx
GW_PHONE_APP_UUID='b88a2e1b-6142-4378-a94954ea3287cce5' # xxx-xxx-xxx-xxx
GW_PHONE_APP_ID='gi752e58b11af83d96'
GW_PHONE_APP_KEY='YXJjc29mdGZhY2VyZWNvZ25pemVkZXRlY3Q'
GW_PHONE_APP_ENCRYPT_KEY='5447c08b53e8dac4'
GW_PHONE_APP_VERSION='2.4.1.4'
GW_PORT='8060'
HEART_BEAT='10'

#############################################
## url handle
#############################################

url_encode() {
	# urlencode <string>

	local length="${#1}"
	for i in $(seq $length); do
		local c="${1:$((i-1)):1}"
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

#############################################
## string handle
#############################################

str_str() {
    # str_str <string> <string> <string>

    local str
    str="${1#*${2}}"
    str="${str%%$3*}"
    printf "$str"

}

is_str_c_str() {
    # is_str_c_str <string> <string>

    [ "${1#*$2*}" = "$1" ] && return 1
    return 0

}

str2hex() {
    # is_str_c_str <string>

    local length="${#1}"
    for i in $(seq $length); do
        printf '%x' "'${1:$((i-1)):1}"
    done

}

#############################################
## json handle
#############################################

cat_json_value() {
	awk -v json="$1" -v key="$2" -v defaultValue="$3" 'BEGIN{
        foundKeyCount = 0
        while (length(json) > 0) {
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

get_json_value() {
    # get_json_value <json> <key>
    
    local value="$(cat_json_value "$1" "$2")"
    local temp="${value%\"}"
    printf '%s' "${temp#\"}"

}

#############################################
## giwifi encrypt util
#############################################

get_challge() {
    # get_challge <password> <token>

    local make_pass="$(printf $1 | base64)"
    local token_challge="$2"
    local length="${#make_pass}"
    for i in $(seq $length); do
        printf '%s' "${make_pass:$i-1:1}${token_challge:32-$i:1}"
    done

}

crypto_encode() {
    # crypto_encode <plain> <key> <iv>
    
    # aes-128-cbc PKCS5Padding (Original data is ZeroPadding, but it is universal on decryption)
    # default key: 1234567887654321
    printf '%s' "$1" | openssl enc -e -aes-128-cbc -K $(str2hex "$2") -iv $(str2hex "$3") -nosalt -base64 -A

}

get_encrypt() {
    # get_encrypt <plain>
    
    # aes-128-ecb PKCS5Padding
    # default key: 5447c08b53e8dac4
    # don't need iv
    printf '%s' "$1" | openssl enc -e -aes-128-ecb -K $(str2hex "$GW_PHONE_APP_ENCRYPT_KEY") -nosalt -base64 -A

}

#############################################
## giwifi api
#############################################

gw_get_auth_state() {
    # gw_get_auth_state <gw_gtw> <gw_port>

	printf '%s' "$(curl -s -A "$GW_PHONE_UA" "http://$1:$2/wifidog/get_auth_state")"

}

gw_get_hotspot_group() {
    # gw_get_hotspot_group <gw_gtw> <gw_port>

	printf '%s' "$(curl -s -A "$GW_PHONE_UA" "http:/$1:$2/wifidog/get_hotspot_group")"

}

gw_phone_app_get_user() {
    # gw_phone_app_login <gw_phone_app_login_data>

    printf '%s' "$(curl -s \
    -A "$GW_PHONE_UA" \
    -X POST \
    -d "$1" \
    'http://login.gwifi.com.cn:8080/wocloud_v2/appUser/getUser.bin')"

}

gw_phone_app_login() {
    # gw_phone_app_login <gw_phone_app_login_data>

    printf '%s' "$(curl -s \
    -A "$GW_PHONE_UA" \
    -X POST \
    -d "$1" \
    'http://login.gwifi.com.cn:8080/wocloud_v2/appUser/appLogin.bin')"

}

gw_get_phone_app_token() {
    # gw_get_phone_app_token <project_id> <timestamp> <user_id>
    
    local sign="$(printf '%s' "app_id=$GW_PHONE_APP_ID&project_id=$1&timestamp=$2&user_id=$3&key=$GW_PHONE_APP_KEY" | openssl md5 | awk '{print $2}')"
    printf '%s' "$(curl -s "http://login.gwifi.com.cn/shop/app/getToken?app_id=$GW_PHONE_APP_ID&project_id=$1&sign=$sign&timestamp=$2&user_id=$3")"

}

gw_phone_app_relogin() {
    # gw_phone_app_relogin <gw_phone_app_login_data>

    printf '%s' "$(curl -s -L \
    -A "$GW_PHONE_UA" \
    -X POST \
    -d "$1" \
    'http://login.gwifi.com.cn:8080/wocloud_v2/appUser/reLogin.bin')"

}

gw_phone_app_rebindmac() {
    # gw_phone_app_rebindmac <gw_phone_app_login_data>

    printf '%s' "$(curl -s \
    -A "$GW_PHONE_UA" \
    -X POST \
    -d "$1" \
    'http://login.gwifi.com.cn:8080/wocloud_v2/appUser/reBindMac.bin')"

}

gw_phone_app_auth_token() {
    # gw_phone_app_auth_token <token>

    printf '%s' "$(curl -s -L "http://$GW_GTW:$GW_PORT/wifidog/auth?token=$1&info=")"

}


# # for pc app
# test=$(get_challge yiyi6666 35f6aa491f6c695c0d77cdce56b94882)
# echo $test
# if [ "$test" = "e2W8l854a9TbY625NejcYd=c" ] ; then
#     echo 'nice!'
# fi

# # for web of special and guest
# crypto_encode 'nasName=WFXY_NE40E-X8_GateWay_01&nasIp=&userIp=100.65.3.95&userMac=&ssid=&apMac=&pid=20&vlan=&sign=LFiSeFHUow0deKzCi5JxC4Ac2gVY1FVrwTtiKCOhQo1UfReGDe3pBFPrw1VFn73h3Z022q3BnOOH1y38R2buDQ%253D%253D&iv=dca6402ddedb2f56&name=20110005&password=123456' 1234567887654321 dca6402ddedb2f56

# for phone app
#get_encrypt '184*****6072'

GW_AUTH_STATE_RTE="$(gw_get_auth_state "$GW_GTW" "$GW_PORT")"
GW_AUTH_STATE_RTE_DATA="$(get_json_value "$GW_AUTH_STATE_RTE" 'data')"

GW_HOTSPOT_GROUP_RTE="$(gw_get_hotspot_group "$GW_GTW" "$GW_PORT")"
GW_HOTSPOT_GROUP_RTE_DATA="$(get_json_value "$GW_HOTSPOT_GROUP_RTE" 'data')"

# GW_HOTSPOT_GROUP_ID="$(get_json_value $GW_HOTSPOT_GROUP_RTE_DATA 'hotspot_group_id')"
# GW_HOTSPOT_GROUP_NAME="$(get_json_value $GW_HOTSPOT_GROUP_RTE_DATA 'hotspot_group_name')"

echo "$GW_AUTH_STATE_RTE_DATA"
echo "$GW_HOTSPOT_GROUP_RTE_DATA"

GW_ID="$(get_json_value "$GW_AUTH_STATE_RTE_DATA" 'gw_id')"
GW_CLIENT_IP="$(get_json_value "$GW_AUTH_STATE_RTE_DATA" 'client_ip')"
GW_CLIENT_MAC="$(get_json_value "$GW_AUTH_STATE_RTE_DATA" 'client_mac')"
GW_ORG_ID="$(get_json_value "$GW_AUTH_STATE_RTE_DATA" 'orgId')"
GW_TS="$(get_json_value "$GW_AUTH_STATE_RTE_DATA" 'timestamp')"
echo ''

GW_PHONE_APP_GET_USER_DATA="$(printf '{"data":"{\\"staticPassword\\":\\"%s\\",\\"phone\\":\\"%s\\"}","version":"%s","mac":"%s","gatewayId":"%s","token":"%s"}' \
"$(get_encrypt "$GW_PWD")" \
"$(get_encrypt "$GW_USER")" \
"$GW_PHONE_APP_VERSION" \
"$(get_encrypt "$GW_CLIENT_MAC")" \
"$(get_encrypt "$GW_ID")" \
"$(get_encrypt '')")"

# echo $GW_PHONE_APP_LOGIN_DATA

# use double printf to handel unicode
GW_USER_RTE="$(printf "$(printf "$(gw_phone_app_get_user $GW_PHONE_APP_GET_USER_DATA)")" | sed "s@\\\\@@g")"

echo "$GW_USER_RTE"

GW_USER_RTE_CODE="$(get_json_value "$GW_USER_RTE" 'resultCode')"

if [ "$GW_USER_RTE_CODE" != '0' ]; then
    GW_USER_RTE_MSG="$(get_json_value "$GW_USER_RTE" 'resultMsg')"
    echo "$GW_USER_RTE_MSG"
    exit 1
fi

GW_USER_RTE_DATA="$(get_json_value "$GW_USER_RTE" 'data')"

GW_USER_UID="$(get_json_value "$GW_USER_RTE_DATA" 'uid')"
GW_USER_LEVEL_NAME="$(get_json_value "$GW_USER_RTE_DATA" 'user_level_name')"

GW_USER_REMAIN_TIME="$(get_json_value "$GW_USER_RTE_DATA" 'remain_time')"
GW_USER_LAN_REMAIN_TIME="$(get_json_value "$GW_USER_RTE_DATA" 'lan_remain_time')"

echo "$GW_USER_UID"
echo "$GW_USER_LEVEL_NAME"
echo "$GW_USER_REMAIN_TIME"
echo "$GW_USER_LAN_REMAIN_TIME"

GW_TOKEN_RTE="$(gw_get_phone_app_token "$GW_ORG_ID" "$GW_TS" "$GW_USER_UID")"

GW_TOKEN_RTE_DATA="$(get_json_value "$GW_TOKEN_RTE" 'data')"

GW_ACCESS_TOKEN="$(get_json_value "$GW_TOKEN_RTE_DATA" 'access_token')"

echo "$GW_ACCESS_TOKEN"

GW_PHONE_APP_LOGIN_DATA="$(printf '{"data":"{\\"gwAddress\\":\\"%s\\",\\"service_type\\":\\"%s\\",\\"staticPassword\\":\\"%s\\",\\"im\\":\\"%s\\",\\"app_uuid\\":\\"%s\\",\\"phone\\":\\"%s\\",\\"ip\\":\\"%s\\",\\"staType\\":\\"%s\\",\\"installWX\\":\\"%s\\",\\"btype\\":\\"%s\\",\\"staModel\\":\\"%s\\",\\"apMac\\":\\"\\",\\"auth_mode\\":\\"%s\\",\\"imsi\\":\\"%s\\",\\"ssid\\":\\"%s\\",\\"filter_id\\":\\"%s\\"}","version":"%s","mac":"%s","gatewayId":"%s","token":"%s"}' \
"$(get_encrypt "$GW_GTW")" \
"$(get_encrypt "$GW_SERVICE_TYPE")" \
"$(get_encrypt "$GW_PWD")" \
"$(get_encrypt "$GW_PHONE_IM")" \
"$(get_encrypt "$GW_PHONE_APP_UUID")" \
"$(get_encrypt "$GW_USER")" \
"$(get_encrypt "$GW_CLIENT_IP")" \
"$(get_encrypt "$GW_STA_TYPE")" \
"$(get_encrypt "$GW_PHONE_IS_INSTALL_WX")" \
"$(get_encrypt '1')" \
"$(get_encrypt "$GW_STA_MODEL")" \
"$(get_encrypt "$GW_PHONE_APP_AUTH_MODE")" \
"$(get_encrypt "$GW_PHONE_APP_IMSI")" \
"$(get_encrypt "$GW_ID")" \
"$(get_encrypt '')" \
"$GW_PHONE_APP_VERSION" \
"$(get_encrypt "$GW_CLIENT_MAC")" \
"$(get_encrypt "$GW_ID")" \
"$(get_encrypt $GW_ACCESS_TOKEN)")"

echo "$GW_PHONE_APP_LOGIN_DATA"

GW_PHONE_APP_LOGIN_RTE="$(printf "$(printf "$(gw_phone_app_relogin "$GW_PHONE_APP_LOGIN_DATA")")")"
GW_PHONE_APP_LOGIN_RTE_CODE="$(get_json_value "$GW_PHONE_APP_LOGIN_RTE" 'resultCode')"

echo $GW_PHONE_APP_LOGIN_RTE

if [ "$GW_PHONE_APP_LOGIN_RTE_CODE" != '0' ]; then
    GW_PHONE_APP_RTE_MSG="$(get_json_value "$GW_PHONE_APP_LOGIN_RTE" 'resultMsg')"
    echo "$GW_PHONE_APP_RTE_MSG"

    if [ "$GW_PHONE_APP_LOGIN_RTE_CODE" == '43' ]; then
        read -r -p "Are you sure to rebind your device? [Y/n] " input
        case $input in
            [yY][eE][sS]|[yY])
                # use double printf to handel unicode
                GW_PHONE_APP_REBINDMAC_RTE="$(printf "$(printf "$(gw_phone_app_rebindmac "$GW_PHONE_APP_LOGIN_DATA")")")"
                GW_PHONE_APP_REBINDMAC_RTE_CODE="$(get_json_value "$GW_PHONE_APP_REBINDMAC_RTE" 'resultCode')"
                GW_PHONE_APP_REBINDMAC_RTE_MSG="$(get_json_value "$GW_PHONE_APP_REBINDMAC_RTE" 'resultMsg')"
                if [ "$GW_PHONE_APP_REBINDMAC_RTE_CODE" != '0' ]; then
                    echo "Error: $GW_PHONE_APP_REBINDMAC_RTE_MSG"
                else
                    echo "OK: $GW_PHONE_APP_REBINDMAC_RTE_MSG (mac: $GW_CLIENT_MAC)"
                fi
                ;;

            [nN][oO]|[nN])
                echo "Ok, is ending..."
                ;;

            *)
                echo "Invalid input..."
                ;;
        esac
    fi
    exit 1
fi

GW_PHONE_APP_LOGIN_RTE_DATA="$(get_json_value "$GW_PHONE_APP_LOGIN_RTE" 'data')"

GW_PHONE_APP_USER_TOKEN="$(get_json_value "$GW_PHONE_APP_LOGIN_RTE_DATA" 'userToken')"

i=1
while :; do
    sleep $HEART_BEAT
    echo "Heartbeat: $i" && i=$((i+1))
    AUTH_TOKEN_RTE="$(gw_phone_app_auth_token "$GW_PHONE_APP_USER_TOKEN")"
done
