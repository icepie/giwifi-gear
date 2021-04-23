#!/usr/bin/env bash

#############################################
## User Config
#############################################

GW_GTW=''
GW_USER=''
GW_PWD=''

AUTH_TYPE=''     # pc/pad for web auth, android/ios/win/mac for app auth
SERVICE_TYPE='1' # 1: GiWiFi用户 2: 移动用户 3: 联通用户 4: 电信用户
HEART_BEAT=9

AUTH_IFACE=''       # the base interface (get auth info)
EXTRA_IFACE_LIST=() # the extra interface list (Recommended for less than two)

GW_PORT='8060'

#############################################
## Web Auth Mode Config
#############################################

GW_REDIRECT_PORT='8062'
GW_REDIRECT_URL='http://www.baidu.com'
PC_UA='Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36'
PAD_UA='Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25'

#############################################
## Desktop App Auth Mode Config
#############################################

WIN_UA='Asynchronous WinHTTP/1.0 GiWiFiAssist/1.1.4.2'
MAC_UA='GiWiFi/1.1.6.2 (Mac OS X Version 11.2.3 (Build 20D91))'

WIN_APP_VERSION='1.1.4.2'
MAC_APP_VERSION='1.1.6.2'

WIN_MODEL='mac11.2'
MAC_MODEL='Microsoft Windows 10, 64-bit'

WIN_APP_UUID='18C6037C-6379-4317-8FE5-8C9B8E573CF8'
MAC_APP_UUID='bad82d414cb15452938cfe605c7faf02'

STA_NIC_TYPE='2' # 1: Wireless 2: Ethernet

#############################################
## Mobile App Auth Mode Config
#############################################

ANDROID_UA='(GiWiFi;Android11;Xiaomi;Redmi K20 Pro Premium Edition)'
IOS_UA='(GiWiFi;iPhone OS 14_3;Apple;iPad11,3)'

ANDROID_STA_MODEL='Xiaomi,Redmi K20 Pro Premium Edition,30,11' # MANUFACTURER,MODEL,SDK,RELEASE
IOS_STA_MODEL='Apple,iPad11,14,3'

MOBILE_STA_TYPE='phone'                           # pad or phone
MOBILE_IM='00000000-023e-0e94-ffff-ffffef05ac4a'  # IMEI UUID
MOBILE_IS_INSTALL_WX='1'                          # is installed WeChat?
MOBILE_AUTH_MODE='1'                              # 1: Cloud Mode 2: Local Mode
MOBILE_IMSI='00000000-6142-4378-a94954'           # xxx-xxx-xxx-xxx
MOBILE_UUID='b88a2e1b-6142-4378-a94954ea3287cce5' # xxx-xxx-xxx-xxx
MOBILE_APP_ID='gi752e58b11af83d96'
MOBILE_APP_KEY='YXJjc29mdGZhY2VyZWNvZ25pemVkZXRlY3Q'
MOBILE_APP_ENCRYPT_KEY='5447c08b53e8dac4'
MOBILE_APP_VERSION='2.4.1.4'

#############################################
## Tool Config
#############################################

VERSION='0.11'

#############################################
## Network Util
#############################################

check_ip() {
	# check_ip <string>

	printf '%s' $1 | grep "^[0-9]\{1,3\}\.\([0-9]\{1,3\}\.\)\{2\}[0-9]\{1,3\}$" >/dev/null
	if [ $? -ne 0 ]; then
		return 1
	fi
	local ipaddr=$1
	local a="$(echo $ipaddr | awk -F . '{print $1}')"
	local b="$(echo $ipaddr | awk -F . '{print $2}')"
	local c="$(echo $ipaddr | awk -F . '{print $3}')"
	local d="$(echo $ipaddr | awk -F . '{print $4}')"
	for num in $a $b $c $d; do
		if [ $num -gt 255 ] || [ $num -lt 0 ]; then
			return 1
		fi
	done
	return 0

}

get_os_type() {

	local uname="$(uname 2> /dev/null)"
	# Mac OS X
	if [ "$(uname)" = "Darwin" ]; then
		local os="darwin"
	# GNU/Linux
	elif [ "$(uname)" = "Linux" ]; then
		os="linux"
		# Android
		if [ "$(getprop 2> /dev/null)" ]; then
			local os="android"
		fi
		# IOS (ISH)
		if [ "$(cat /dev/clipboard 2> /dev/null)" ]; then
			local os="ish"
		fi
	# Windows
	elif [ "$(chcp.com 2> /dev/null)" ]; then
		local os="windows"
	else
		local os="$(uname)"
	fi
	printf '%s' "$os"

}

get_nic_gateway() {
	# get_nic_gateway <nic_name>

	local os="$(get_os_type)"
	local gateway

	if [ "$os" == 'windows' ]; then
		gateway="$(chcp.com 437 >/dev/null && netsh interface ip show address "$1" 2>/dev/null | grep 'Default Gateway' | awk '{print $3}')"
	elif [ "$os" == 'linux' ] || [ "$os" == 'android' ]; then
		gateway="$(ip route list match 0 table all scope global 2>/dev/null | grep 'proto' | grep "$1" | awk '{print $3}')"
	elif [ "$os" == 'darwin' ]; then
		gateway="$(netstat -rn 2>/dev/null | grep 'default' | grep "$1" | awk '{print $2}')"
	fi

	if $(check_ip "$gateway"); then
		printf '%s' "$gateway"
	fi

}

get_nic_ip() {
	# get_nic_ip <nic_name>

	local os="$(get_os_type)"
	local ip

	if [ "$os" == 'windows' ]; then
		ip="$(chcp.com 437 >/dev/null && netsh interface ipv4 show ipaddress interface="$1" 2>/dev/null | head -2 | awk '{print $2}')"
	elif [ "$os" == 'linux' ] || [ "$os" == 'android' ]; then
		ip="$(ip address show dev "$1" 2>/dev/null | grep 'inet ' | awk '{print $2}' | awk -F '/' '{print $1}')"
	elif [ "$os" == 'darwin' ]; then
		ip="$(ifconfig "$1" 2>/dev/null | grep "inet " | grep -v 127.0.0.1 | cut -d\  -f2 | awk '{print $1}')"
	fi

	if $(check_ip "$ip"); then
		printf '%s' "$ip"
	fi

}

#############################################
## Json Handle
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
## url handle
#############################################

url_encode() {
	# urlencode <string>

	local length="${#1}"
	for i in $(seq $length); do
		local c="${1:$((i - 1)):1}"
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
## String Handle
#############################################

str_str() {
	# str_str <string> <string> <string>

	local str
	str="${1#*${2}}"
	str="${str%%$3*}"
	printf '%s' "$str"

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
		printf '%x' "'${1:$((i - 1)):1}"
	done

}

logcat() {
	# logcat <string> <level>

	#%Y-%m-%d
	local time="$(date "+%H:%M:%S")"

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
## Special Util
#############################################

curl_by_nic() {
	# curl_by_nic <curl_param>

	if [ -z "$AUTH_IFACE" ]; then
		printf '%s' "$(curl $@)"
	else
		printf '%s' "$(curl --interface "$AUTH_IFACE" $*)"
	fi

}

curl_by_nics() {
	# curl_by_nic <curl_param>

	if [ -z "$AUTH_IFACE" ]; then
		printf '%s' "$(curl $*)"
	else
		printf '%s' "$(curl --interface "$AUTH_IFACE" $*)"
	fi

	local extra_count=${#EXTRA_IFACE_LIST[*]}

	for i in $(seq $extra_count); do
		printf '%s' "$(curl --interface "${EXTRA_IFACE_LIST[$(($i - 1))]}" $*)"
	done

}

#############################################
## GiWiFi API
#############################################

gw_get_gateway() {
	local nettest="$(curl $CURL_OPT -s 'http://nettest.gwifi.com.cn' | grep 'delayURL')"
	local delayurl="$(str_str "$nettest" 'delayURL("' '")')"
	printf '%s' "$(str_str "$delayurl" 'http://' ":"$GW_REDIRECT_PORT"/redirect")"
}

gw_get_hotspot_group() {
	printf '%s' "$(curl $CURL_OPT -s -A "$AUTH_UA" "http://"$GW_GTW":"$GW_PORT"/wifidog/get_hotspot_group")"
}

gw_get_auth_state() {
	printf '%s' "$(curl $CURL_OPT -s -A "$AUTH_UA" "http://"$GW_GTW":"$GW_PORT"/wifidog/get_auth_state")"
}

# for web
gw_web_get_login_page() {
	printf '%s' "$(curl $CURL_OPT -s -L -A "$AUTH_UA" "http://$GW_GTW:$GW_REDIRECT_PORT/redirect?oriUrl="$GW_REDIRECT_URL"&account_type=1" | grep 'name')"
}

gw_web_loginaction() {
	# create random three-digit numbers
	local str="$(date +%S%M)"
	local rannum="${str:1:3}"

	printf '%s' "$(curl $CURL_OPT -s \
		-A "$AUTH_UA" \
		-X POST \
		-d "$1" \
		"http://login.gwifi.com.cn/cmps/admin.php/api/loginaction?round=$rannum")"
}

# discard
# gw_web_get_gtw_auth() {
# 	printf '%s' "$(curl_by_nic -s -A "\'$AUTH_UA\'" "http://"$GW_GTW"/getApp.htm?action=getAuthState&os=mac")"
# }
#
# gw_web_logout() {
# 	printf '%s' "$(curl_by_nic -s "http://"$GW_GTW"/getApp.htm?action=logout")"
# 

gw_logout() {
	printf '%s' "$(curl $CURL_OPT -s -L -A "$AUTH_UA" "http://$GW_GTW:$GW_PORT/wifidog/userlogout?ip="$ClIENT_ID"&mac="$(url_encode "$ClIENT_MAC")"")"
}


#############################################
## CLI Related
#############################################

init() {

	OS="$(get_os_type)"
	# check dep
	hash curl 2>/dev/null || {
		echo 'Error: curl is not installed.' >&2
		exit 1
	}
	hash openssl 2>/dev/null || { echo 'Error: openssl is not installed. you can only use the "web auth type (pc/pad)"' >&2; }

}

detect_gateway() {

	if [ "$AUTH_IFACE" ]; then
		CURL_OPT=--interface $AUTH_IFACE
		logcat "Will use the interface $AUTH_IFACE to auth..."
		if [ ! "$GW_GTW" ]; then
			logcat "Try to get the gateway from interface $AUTH_IFACE..."
			GW_GTW="$(get_nic_gateway $AUTH_IFACE)"
		fi
	fi

	[ ! "$GW_GTW" ] && logcat "Try to get the gateway from redirect url..." && GW_GTW="$(gw_get_gateway)"

	[ "$GW_GTW" ] && logcat "Gateway detected as "$GW_GTW""

}

ver() {

	echo "\
giwifi-gear-cloud.sh version $VERSION ($OS) \
"

}

usage() {
	echo "\
giwifi-gear-cloud (bash)
  A cli tool for login giwifi by cloud auth mode (multi-platform, fast, small)
usage:
  giwifi-gear-cloud.sh [-h] [-g <GATEWAY>] [-u <USERNAME>] [-p <PASSWORD>] [-t <TYPE>] [-i] [-q] [-b] [-d] [-v]
optional arguments:
  -h                    show this help message and exit
  -g <GATEWAY>          set the gateway
  -u <USERNAME>         set the username
  -p <PASSWORD>         set the password
  -i <IFACE>            set the interface by name or ip
  -e <EXTRA_IFACE>      set the extra interface (-e vwan1 -e vwan2)
  -t <TYPE>             auth type(pc/pad for web auth, android/ios/win/mac for app auth (default value is pc)
  -b                    bind or rebind your device
  -q                    sign out of account authentication
  -d                    running in the daemon mode (remove sharing restrictions)
  -l                    print the log info
  -v                    show the tool version and exit
example:
  # bind your device with pad type
  ./giwifi-gear-cloud.sh -g 172.21.1.1 -u 13000000001 -p mypassword -t pad -b
  # auth with daemon mode
  ./giwifi-gear-cloud.sh -g 172.21.1.1 -u 13000000001 -p mypassword -d
  # quit auth
  ./giwifi-gear-cloud.sh -g 172.21.1.1 -q
(c) 2021 icepie.dev@gmail.com\
"
}

main() {

	[ $ISLOG ] && echo '' && \
	echo "TOOL_PATH:" && \
	echo "--> $0" && \
	echo ''

	# check the conflicting parameters
	if ([ $ISBIND ] && [ $ISQUIT ]) || ([ $ISBIND ] && [ $ISDAEMON ]) || ([ $ISQUIT ] && [ $ISDAEMON ]); then
		echo "Error: don't use bind, quit or daemon at same time!"
		exit 1
	fi

	if [ ${#EXTRA_IFACE_LIST[@]} -gt 0 ] && [ ! "$AUTH_IFACE" ]; then
		echo "Error: if you want to use the extra interfaces, plz set the auth interface!"
		exit 1
	fi

	# check the auth type
	if [ ! "$AUTH_TYPE" ]; then
		AUTH_MODE='web'
		AUTH_TYPE="pc"
		AUTH_UA="$PC_UA"
	fi

	[ $ISLOG ] && echo '' && \
	echo "AUTH_UA:" && \
	echo "--> "$AUTH_UA"" && \
	echo ''

	logcat "You are running with "$AUTH_TYPE" "$AUTH_MODE" auth mode!"

	# get the gateway
	detect_gateway

	if [ ! "$GW_GTW" ]; then
		printf '%s' "Plz enter gateway: "
		read GW_GTW
	fi

	[ $ISLOG ] && echo '' && \
	echo "GW_HOTSPOT_GROUP:" && \
	echo "--> "$(gw_get_hotspot_group)"" && \
	echo ''

	AUTH_STATE_RTE="$(gw_get_auth_state)"
	##{"resultCode":0,"data":"{\"auth_state\":1,\"gw_id\":\"GWIFI-luoyangligong4\",\"access_type\":\"1\",\"authStaType\":\"0\",\"station_sn\":\"c400ada4a45a\",\"client_ip\":\"172.21.219.234\",\"client_mac\":\"60:F1:89:4A:5C:CB\",\"online_time\":680,\"logout_reason\":32,\"contact_phone\":\"400-038-5858\",\"suggest_phone\":\"400-038-5858\",\"station_cloud\":\"login.gwifi.com.cn\",\"orgId\":\"930\",\"timestamp\":\"1619174989\",\"sign\":\"29C2348DCE52C1E47C9B52076DE26C32\"}"}

	[ ! "$AUTH_STATE_RTE" ] && \
	logcat "Fail to get the auth state! (visit https://blog.icepie.net/giwifi-gear for more info)" "E" && \
	exit 1

	[ $ISLOG ] && echo "" && \
	echo "AUTH_STATE_RTE:" && \
	echo "--> "$AUTH_STATE_RTE"" && \
	echo ''

	# Deserialization auth_state json data
	AUTH_STATE_DATA="$(get_json_value "$AUTH_STATE_RTE" 'data')"
	AUTH_STATE="$(get_json_value "$AUTH_STATE_DATA" 'auth_state')"
	GW_ID="$(get_json_value "$AUTH_STATE_DATA" 'gw_id')"
	ACCESS_TYPE="$(get_json_value "$AUTH_STATE_DATA" 'access_type')"
	# AUTH_STA_TYPE="$(get_json_value "$AUTH_STATE_DATA" 'authStaType')"
	STATION_SN="$(get_json_value "$AUTH_STATE_DATA" 'station_sn')"
	ClIENT_ID="$(get_json_value "$AUTH_STATE_DATA" 'client_ip')"
	ClIENT_MAC="$(get_json_value "$AUTH_STATE_DATA" 'client_mac')"
	ONLINE_TIME="$(get_json_value "$AUTH_STATE_DATA" 'online_time')"
	LOGOUT_REASON="$(get_json_value "$AUTH_STATE_DATA" 'logout_reason')"
	CONTACT_PHONE="$(get_json_value "$AUTH_STATE_DATA" 'contact_phone')"
	SUGGEST_PHONE="$(get_json_value "$AUTH_STATE_DATA" 'suggest_phone')"
	STATION_CLOUD="$(get_json_value "$AUTH_STATE_DATA" 'station_cloud')"
	SIGN="$(get_json_value "$AUTH_STATE_DATA" 'sign')"
	

	if [ $AUTH_STATE -eq 2 ]; then
		logcat "Good! You are authed!"
		if [ $ISQUIT ]; then
			LOGOUT_RTE="$(gw_logout)"
			LOGOUT_RTE_CODE="$(get_json_value "$LOGOUT_RTE" 'resultCode')"
			([ $LOGOUT_RTE_CODE -eq 0 ] && logcat "Successfully logged out!") || logcat "Fail to logout!" "E"
			exit 0
		fi
		[ ! $ISDAEMON ] &&  logcat "exit" && exit 0
	fi

	[ $ISQUIT ] && logcat "You do not need to logout!" "E" && exit 1


	if [ ! "$GW_USER" ]; then
		printf '%s' "Plz enter username: "
		read GW_USER
	fi

	if [ ! "$GW_PWD" ]; then
		printf '%s' "Plz enter password: "
		read -s -t 20 GW_PWD
		echo ''
	fi

	# login and get token...
	case "$AUTH_MODE" in
	'web')
		echo "test web"
		# login to get the auth token
		LOGIN_PAGE="$(gw_web_get_login_page)"
		# get some values from login page
		PAGE_SIGN=$(str_str "$LOGIN_PAGE" 'name="sign" value="' '"')
		PAGE_TIME=$(str_str "$LOGIN_PAGE" 'name="page_time" value="' '"')

		[ $ISLOG ] && echo '' && \
		echo "PAGE_SIGN:" && \
		echo "--> "$PAGE_SIGN"" && \
		echo ''

		[ $ISLOG ] && echo '' && \
		echo "PAGE_TIME:" && \
		echo "--> "$PAGE_TIME"" && \
		echo ''

		# login to get the auth token
		WEB_LOGIN_DATA="""\
account_type=1\
&access_type="$ACCESS_TYPE"\
&acsign="$SIGN"\
&btype="$AUTH_TYPE"\
&client_mac="$(url_encode $ClIENT_MAC)"\
&contact_phone="$CONTACT_PHONE"\
&devicemode=""\
&gw_address="$GW_GTW"\
&gw_id="$GW_ID"\
&gw_port="$GW_PORT"\
&lastaccessurl=""\
&logout_reason="$LOGOUT_REASON"\
&mac="$(url_encode $ClIENT_MAC)"\
&name="$GW_USER"\
&online_time="$ONLINE_TIME"\
&page_time="$PAGE_TIME"\
&password="$GW_PWD"\
&sign="$(url_encode $PAGE_SIGN)"\
&station_cloud="$STATION_CLOUD"\
&station_sn="$STATION_SN"\
&suggest_phone="$SUGGEST_PHONE"\
&url="$(url_encode $GW_REDIRECT_URL)"\
&user_agent=""\
&link_data=""\
"""
		echo "$WEB_LOGIN_DATA"

		echo "$(gw_web_loginaction "$WEB_LOGIN_DATA")"

		;;
	'desktop')
		echo "test desktop"
		;;
	'mobile')
		echo "test mobile"
		;;
	esac


	#echo $(url_encode "$ClIENT_MAC")

	#echo "$AUTH_IFACE"
	#echo "${EXTRA_IFACE_LIST[@]}"
}

#start
(
	# init setting
	init

	# paser the opts
	while getopts "g:u:p:t:i:e:qbdlvh" option; do
		case "$option" in
		g)
			GW_GTW="$OPTARG"
			;;
		u)
			GW_USER="$OPTARG"
			;;
		p)
			GW_PWD="$OPTARG"
			;;
		t)
			case "$OPTARG" in
			'pc')
				AUTH_UA="$PC_UA"
				AUTH_MODE='web'
				;;
			'pad')
				AUTH_UA="$PAD_UA"
				AUTH_MODE='web'
				;;
			'win')
				AUTH_UA="$WIN_UA"
				AUTH_MODE='desktop'
				DESKTOP_APP_VERSION="$WIN_APP_VERSION"
				;;
			'mac')
				AUTH_UA="$MAC_UA"
				AUTH_MODE='desktop'
				DESKTOP_APP_VERSION="$MAC_APP_VERSION"
				;;
			'android')
				AUTH_UA="$ANDROID_UA"
				AUTH_MODE='mobile'
				;;
			'ios')
				AUTH_UA="$IOS_UA"
				AUTH_MODE='mobile'
				;;
			*)
				echo "Error: Do not support the "$OPTARG" type!"
				exit 1
				;;
			esac
			AUTH_TYPE="$OPTARG"
			;;
		i)
			AUTH_IFACE="$OPTARG"
			;;
		e)
			EXTRA_IFACE_LIST+=("$OPTARG")
			;;
		q)
			ISQUIT=1
			;;
		b)
			ISBIND=1
			;;
		d)
			ISDAEMON=1
			;;
		l)
			ISLOG=1
			;;
		v)
			ver
			exit 0
			;;
		h)
			usage
			exit 0
			;;
		*)
			echo "Error: plz use the right options!"
			usage
			exit 1
			;;
		esac
	done

	# main func
	main
)