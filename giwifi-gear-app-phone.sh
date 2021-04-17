#!/usr/bin/env bash
# giwifi-gear-phone bash cli tool 
# by icepie

#############################################
## config
#############################################

GW_PHONE_APP_ID='gi752e58b11af83d96'
GW_PHONE_APP_KEY='YXJjc29mdGZhY2VyZWNvZ25pemVkZXRlY3Q'
GW_PHONE_APP_ENCRYPT_KEY='5447c08b53e8dac4'

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
        printf '%x' "'${1:$i-1:1}"
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

	printf '%s' "$(curl -s "http://$1:$2/wifidog/get_auth_state")"

}

gw_get_hotspot_group() {
    # gw_get_hotspot_group <gw_gtw> <gw_port>

	printf '%s' "$(curl -s "http:/$1:$2/wifidog/get_hotspot_group")"

}

gw_get_phone_app_user() {
    # gw_get_phone_app_token <appid> <project_id> <sign> <timestamp> <user_id>
    
    printf '%s' "$(curl -s "login.gwifi.com.cn/shop/app/getToken?app_id=$1&project_id=$2&sign=$3&timestamp=$4&user_id=$5")"

}

gw_get_phone_app_token() {
    # gw_get_phone_app_token <project_id> <timestamp> <user_id>
    
    local sign="$(printf '%s' "app_id=$GW_PHONE_APP_ID&project_id=$1&timestamp=$2&user_id=$3&key=$GW_PHONE_APP_KEY" | openssl md5 | awk '{print $2}')"
    printf '%s' "$(curl -s "login.gwifi.com.cn/shop/app/getToken?app_id=$GW_PHONE_APP_ID&project_id=$1&sign=$sign&timestamp=$2&user_id=$3")"

}

# gw_phone_app_login() {
    
# }

# # for pc app
# test=$(get_challge yiyi6666 35f6aa491f6c695c0d77cdce56b94882)
# echo $test
# if [ "$test" = "e2W8l854a9TbY625NejcYd=c" ] ; then
#     echo 'nice!'
# fi

# # for web of special and guest
# crypto_encode 'nasName=WFXY_NE40E-X8_GateWay_01&nasIp=&userIp=100.65.3.95&userMac=&ssid=&apMac=&pid=20&vlan=&sign=LFiSeFHUow0deKzCi5JxC4Ac2gVY1FVrwTtiKCOhQo1UfReGDe3pBFPrw1VFn73h3Z022q3BnOOH1y38R2buDQ%253D%253D&iv=dca6402ddedb2f56&name=20110005&password=123456' 1234567887654321 dca6402ddedb2f56

# for phone app
# get_encrypt '184*****6072'

GW_AUTH_STATE_RTE="$(gw_get_auth_state '172.21.1.4' '8060')"
GW_AUTH_STATE_RTE_DATA="$(get_json_value $GW_AUTH_STATE_RTE 'data')"

GW_HOTSPOT_GROUP_RTE="$(gw_get_hotspot_group '172.21.1.4' '8060')"
GW_HOTSPOT_GROUP_RTE_DATA="$(get_json_value $GW_HOTSPOT_GROUP_RTE 'data')"

# GW_HOTSPOT_GROUP_ID="$(get_json_value $GW_HOTSPOT_GROUP_RTE_DATA 'hotspot_group_id')"
# GW_HOTSPOT_GROUP_NAME="$(get_json_value $GW_HOTSPOT_GROUP_RTE_DATA 'hotspot_group_name')"

echo "$GW_AUTH_STATE_RTE_DATA"
echo "$GW_HOTSPOT_GROUP_RTE_DATA"

#GW_APP_PHONE_LOGIN_DATA="{"data":"{\"gwAddress\":\"4t8LHSZpJUFtJNX153a41Q==\",\"service_type\":\"i5MvUsxpruslGcDFWFWBdg==\",\"staticPassword\":\"Ntd4YVjzHnTRNcO4DS5+9Q==\",\"phone\":\"CNjEWzJjKuv/+haAPjwKeg==\",\"ip\":\"NWrK3pPleT9at4RfsqTuMQ==\",\"staType\":\"et9Lc4P9kNDLICPfuKNcSg==\",\"staModel\":\"LChrbcyYCBcTgvqpB5T7rUL1JJSRJV67lH550benImn40jlxN6h6cOIQSnPKiT5i\",\"apMac\":\"\"}","version":"2.4.1.4","mac":"8lDTxzmP6drtQHpvQewOaq4GCP0eHGQ8QHn8jEZP1F8=","gatewayId":"rzRVrRvKItu367DlF2z/yJQg3YeRZAvIvmc3/MjfWtQ=","token":"ZFWLuvEutELIKHcF5mhqnJotjLV3BkA00lyLk/j+AJo2QorZY7HpUzHmz4Rrkp6H"}"

