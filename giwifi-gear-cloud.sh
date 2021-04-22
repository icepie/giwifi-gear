#!/usr/bin/env bash

#############################################
## User Config
#############################################

GW_GTW=''
GW_USER=''
GW_PWD=''

AUTH_TYPE='' # pc/pad for web auth, android/ios/win/
SERVICE_TYPE='1' # 1: GiWiFi用户 2: 移动用户 3: 联通用户 4: 电信用户
HEART_BEAT=9

AUTH_IFACE=''       # the base interface (get auth info)
EXTRA_IFACE_LIST=() # the extra interface list (Recommended for less than two)

GW_PORT='8060'

#############################################
## Web Auth Mode Config
#############################################

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

MOBILE_STA_TYPE='phone' # pad or phone
MOBILE_IM='00000000-023e-0e94-ffff-ffffef05ac4a' # IMEI UUID
MOBILE_IS_INSTALL_WX='1' # is installed WeChat?
MOBILE_AUTH_MODE='1' # 1: Cloud Mode 2: Local Mode
MOBILE_IMSI='00000000-6142-4378-a94954' # xxx-xxx-xxx-xxx
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

	local uname=$(uname 2>/dev/null)
	local os

	# Mac OS X
	if [ "$(uname)" == "Darwin" ]; then
		os="darwin"
	# GNU/Linux
	elif [ "$(uname)" == "Linux" ]; then
		os="linux"
		# Android
		if [ "$(getprop 2>/dev/null)" ]; then
			os="android"
		fi
		# IOS (ISH)
		if [ "$(cat /dev/clipboard 2>/dev/null)" ]; then
			os="ish"
		fi
	# Windows
	elif [ "$(chcp.com 2>/dev/null)" ]; then
		os="windows"
	else
		os=$(uname)
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
## String Handle
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
		printf '%s' "$(curl $*)"
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

    TOOL_PATH="$0"
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
  -t <TYPE>             auth type(pc/pad for web auth, android/ios/win/mac app auth (default value is pc)
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

    [ $ISLOG ] && \
	echo "TOOL_PATH:" && \
	echo "--> $TOOL_PATH" && \
	echo "";

    # check the auth type
    if [ -z "$AUTH_TYPE" ]; then
        AUTH_MODE='web'
        AUTH_TYPE="pc"
        AUTH_UA="$PC_UA"
    fi

    logcat "You are running with "$AUTH_TYPE" "$AUTH_MODE" auth mode!"

	# check the gateway
	while [ ! $GW_GTW ]; do
		echo -n "Plz enter gateway: "
		read GW_GTW
	done

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
