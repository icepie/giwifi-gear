#!/bin/bash
# by icepie

#############################################
## config
#############################################
# tool info
VERSION=0.2

GW_USER=""
GW_PWD=""
GW_USER_IP="172.21.25.52"
GW_USER_MAC="C2:D4:0C:4A:E0:3A"
GW_URL="10.189.1.3"
GW_IF="en0"

GW_NAS_NAME=""
GW_NAS_IP=""
GW_SSID=""

# auth setting
APP_UA="okhttp/3.3.0"
APP_UUID="00000000-0000-0000-0000-000000000000"
STA_MODEl="iPhone,6"
STA_TYPE="phone" # pad pc phone
BTYPE="1"

HEART_BEAT=9

APP_ENCRYPT_KEY='5447c08b53e8dac4'
APP_SIGN_KEY='5447c08b53e8dac47f81269f98cfeada'


#############################################
## Tool Config
#############################################

AWK_TOOL='awk' # it will be upgrade to gawk, when you have gawk...
VERSION='1.3.4'

get_os_type() {

	local os
	# Mac OS X
	if [ "$(uname)" = "Darwin" ]; then
		os="darwin"
	# GNU/Linux
	elif [ "$(uname)" = "Linux" ]; then
		os="linux"
		# Android
		if [ "$(getprop 2>/dev/null)" ]; then
			os="android"
		fi
		# IOS (ISH)
		if [ "$(ls /dev/clipboard 2>/dev/null)" ]; then
			os="ish"
		fi
	# Windows
	elif [ "$(chcp.com 2>/dev/null)" ]; then
		os="windows"
	else
		os="$(uname)"
	fi
	printf '%s' "$os"

}

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

displaytime() {
	local T=$1
	local D=$((T / 60 / 60 / 24))
	local H=$((T / 60 / 60 % 24))
	local M=$((T / 60 % 60))
	local S=$((T % 60))
	[ $D -gt 0 ] && printf '%d days ' $D
	[ $H -gt 0 ] && printf '%d hours ' $H
	[ $M -gt 0 ] && printf '%d minutes ' $M
	[ $S -ge 0 ] && printf '%d seconds\n' $S
}

#############################################
## string handle
#############################################

str_str() {
        #str_str <string> "str" "str"

        local str
        str="${1#*${2}}"
        str="${str%%$3*}"
        echo -n "$str"
}

str2hex() {
    #is_str_c_str <string>

    local length="${#1}"
    for i in $(seq $length); do
        printf '%x' "'${1:$i-1:1}"
    done

}

logcat() {
        #%Y-%m-%d
        local time=$(date "+%H:%M:%S")

        # check the log flag
        if [ "$2" ]; then
                local flag=$2
        else
                local flag='I'
        fi

        if [ "$1" ]; then
                echo -e "$time" "$flag": "$1"
        fi
}

#############################################
## Json Handle
#############################################

cat_json_value() {
	${AWK_TOOL} -v json="$1" -v key="$2" -v defaultValue="$3" 'BEGIN{
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

	local value
	value="$(cat_json_value "$1" "$2")"
	local temp="${value%\"}"
	printf '%s' "${temp#\"}"

}


#############################################
## GiWiFi Encrypt Util
#############################################

get_encrypt() {
	# get_encrypt <plain>

	# aes-128-ecb PKCS5Padding
	# default key: 5447c08b53e8dac4
	# don't need iv
	printf '%s' "$1" | openssl enc -e -aes-128-ecb -K "$(str2hex "$APP_ENCRYPT_KEY")" -nosalt -base64 -A

}

get_decrypt() {
    # get_decrypt <encrypt>

    # aes-128-ecb PKCS5Padding
    # default
    # don't need iv
    printf '%s' "$1" | openssl enc -d -aes-128-ecb -K "$(str2hex "$APP_ENCRYPT_KEY")" -nosalt -base64 -A
}

md5sum() {
    # md5sum <string>
    echo -n $1 | openssl md5
}


#############################################
## giwifi api for local app auth
#############################################

gw_query_auth_state() {
    printf '%s' "$(curl -s \
		-A "$APP_UA" \
        --interface $GW_IF \
		-X POST \
		-d "$1" \
		"http://$GW_URL/gportal/app/queryAuthState"
	)"
}

gw_auth_login() {
	# create random three-digit numbers
	local str="$(date +%S%M)"
	local rannum="${str:1:3}"

	printf '%s' "$(curl -s \
		-A "$APP_UA" \
        --interface $GW_IF \
		-X POST \
		-d "$1" \
		"http://$GW_URL/gportal/web/authLogin?round=$rannum"
	)"
}


#############################################
## main
#############################################

init() {

	OS="$(get_os_type)"
	# check dep

	if [ $OS = 'android' ]; then

		[ -x "$(command -v curl)" ] || {
			logcat 'Error: curl is not installed.' 'E'
			exit 1
		}

		[ -x "$(command -v openssl)" ] || ISNOSSL=1

		[ -x "$(command -v gawk)" ] && AWK_TOOL='gawk'

	else

		hash curl 2>/dev/null || {
			logcat 'Error: curl is not installed.' 'E'
			exit 1
		}

		hash openssl 2>/dev/null || { ISNOSSL=1; }

		hash gawk 2>/dev/null && { AWK_TOOL='gawk'; }

	fi

}

init

d="timestamp=$(date +%s)&userIp=$GW_USER_IP&userName=$GW_USER"
sign=$(md5sum "$d$APP_SIGN_KEY")
echo "$sign"

ed=$(get_encrypt "$d&sign=$sign")

echo "$ed"

ud=$(url_encode "$ed")

ud=$(url_encode "$ud")

echo "$ud"

rte=$(gw_query_auth_state "data=$ud")

echo -e "$rte"

rte_data=$(get_json_value "$rte" "data") 


## decode
rte_data=$(url_decode "$rte_data")

echo -e "$rte_data"

rte_data=$(get_decrypt "$rte_data")

echo -e "$rte_data"



login_data="apMac=&appUuid=$APP_UUID&btype=$BTYPE&nasIp=$GW_NAS_IP&nasName=$GW_NAS_NAME&passwd=$GW_PWD&ssid=&staModel=$STA_MODEl&staType=$STA_TYPE&timestamp=$(date +%s)&userFirstUrl=&userIp=$GW_USER_IP&userMac=$GW_USER_MAC&userName=$GW_USER&vlan=1"
login_sign=$(md5sum "$login_data$APP_SIGN_KEY")

echo "$login_data&sign=$login_sign"

login_ed=$(get_encrypt "$login_data&sign=$login_sign")

echo "$login_ed"

login_ud=$(url_encode "$login_ed")

# login_ud=$(url_encode "$login_ud")

echo "$login_ud"

login_rte=$(gw_auth_login "data=$login_ud")

echo -e "$login_rte"

login_rte_data=$(get_json_value "$login_rte" "data")

echo -e "$login_rte_data"

login_rte_data=$(url_decode "$login_rte_data")

echo -e "$login_rte_data"