#!/bin/bash
# giwifi-gear-mix bash cli tool for wfu
# by icepie

#############################################
## config
#############################################
# tool info
VERSION=0.1

GW_USER=""
GW_PWD=""
GW_GTW="192.168.111.117"

CRYPTO_TOOL="./giwifi-mix-crypto"

# auth setting
PC_UA="Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36"
PAD_UA="Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25"
HEART_BEAT=9

#############################################
## url handle
#############################################
function url_encode() {
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

function url_decode() {
	# urldecode <string>

	local url_encoded="${1//+/ }"
	printf '%b' "${url_encoded//%/\\x}"

}

#############################################
## string handle
#############################################
function str_str() {
        #str_str <string> "str" "str"

        local str
        str="${1#*${2}}"
        str="${str%%$3*}"
        echo -n "$str"
}


function logcat() {
        #%Y-%m-%d
        local time=$(date "+%H:%M:%S")

        # check the log flag
        if [ "$2" ]; then
                local flag=$2
        else
                local flag='I'
        fi

        if [ "$1" ]; then
                echo "$time" "$flag": "$1"
        fi
}

#############################################
## json handle
#############################################
function get_json_value() {
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

function json_format() {
	local json=$1
	# Del escape character and format text
	echo $(echo -e $1) | sed "s@\\\\@@g"
}

#############################################
## giwifi api for wfu
#############################################

function gw_get_login_page() {
    echo $(curl -s -L -A "$PC_UA" "http://$GW_GTW/gportal/web/login?account_type=2" | grep "name=")
}

function gw_mix_crypto() {
    echo $($CRYPTO_TOOL -t $1 -i $2)
}

function gw_loginaction() {
	# create random three-digit numbers
	local str=$(date +%S%M)
	local rannum=${str:1:3}

	echo $(curl -s \
		-A "$PC_UA" \
		-X POST \
		-d "$1" \
		"http://$GW_GTW/gportal/web/authLogin?round=$rannum"
	)
}

function gw_rebindmac() {
	# create random three-digit numbers
	local str=$(date +%S%M)
	local rannum=${str:1:3}

	echo $(curl -s \
		-A "$PC_UA" \
		-X POST \
		-d "$1" \
		"http://$GW_GTW/gportal/web/reBindMac?round=$rannum"
	)
}


# get the login page
GW_LOGIN_PAGE=$(gw_get_login_page)

# get params form the login page
GW_NAS_NAME=$(str_str "$GW_LOGIN_PAGE" 'name="nasName" value="' '"')
GW_NAS_IP=$(str_str "$GW_LOGIN_PAGE" 'name="nasIp" value="' '"')
GW_USER_IP=$(str_str "$GW_LOGIN_PAGE" 'name="userIp" value="' '"')
GW_USER_MAC=$(str_str "$GW_LOGIN_PAGE" 'id="userMac" value="' '"')
GW_SSID=$(str_str "$GW_LOGIN_PAGE" 'name="ssid" value="' '"')
GW_AP_MAC=$(str_str "$GW_LOGIN_PAGE" 'name="apMac" value="' '"')
GW_PID=$(str_str "$GW_LOGIN_PAGE" 'name="pid" value="' '"')
GW_VLAN=$(str_str "$GW_LOGIN_PAGE" 'name="vlan" value="' '"')
GW_SIGN=$(str_str "$GW_LOGIN_PAGE" 'name="sign" value="' '"')
GW_IV=$(str_str "$GW_LOGIN_PAGE" 'id="iv" value="' '"')

# nasName=WFXY_NE40E-X8_GateWay_01&nasIp=&userIp=100.65.5.150&userMac=&ssid=&apMac=&pid=20&vlan=&sign=x55KxWFCzAjljfemHhSdI%252B%252FKDtIOOwt6gmiARCrIC%252BA6ithbMIml7022fXxzhl24X3T1CsfsZ%252Bajyc%252BiNT%252FM6A%253D%253D&iv=f86ce181a5dd74b5&name=&password=
GW_LOGIN_RAW_DATA="""\
nasName=$GW_NAS_NAME\
&nasIp=$GW_NAS_IP\
&userIp=$GW_USER_IP\
&userMac=$(url_encode $GW_USER_MAC)\
&ssid=$GW_SSID\
&apMac=$(url_encode $GW_AP_MAC)\
&pid=$GW_PID\
&vlan=$GW_VLAN\
&sign=$(url_encode $GW_SIGN)\
&iv=$GW_IV\
&name=$GW_USER\
&password=$GW_PWD\
"""

# echo "$GW_LOGIN_RAW_DATA \
#  "

# use giwifi-mix-crypto to build the login data
GW_LOGIN_DATA="data=$(url_encode $(gw_mix_crypto $GW_LOGIN_RAW_DATA $GW_IV))&iv=$GW_IV"

echo $GW_LOGIN_DATA

GW_LOGIN_RTE=$(gw_loginaction $GW_LOGIN_DATA)
## {"data":{"resultCode":2,"reasoncode":1},"info":"u7ec8u7aefu5df2u8ba4u8bc1","status":0}

echo $GW_LOGIN_RTE
