#!/usr/bin/env bash

#############################################
## User Config
#############################################

GW_GTW=''
GW_USER=''
GW_PWD=''

AUTH_IFACE=''       # the base interface (get auth info)
EXTRA_IFACE_LIST=() # the extra interface list (Recommended for less than two)

#############################################
## Config
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
  ./giwifi-gear-cloud.sh -g 172.21.1.1 -u 13000000001 -p mypassword -t pad -r
  # auth with daemon mode
  ./giwifi-gear-cloud.sh -g 172.21.1.1 -u 13000000001 -p mypassword -d
  # quit auth
  ./giwifi-gear-cloud.sh -g 172.21.1.1 -q
(c) 2021 icepie.dev@gmail.com\
"
}

main() {
	echo "$OS"
	echo "$AUTH_IFACE"
	echo "${EXTRA_IFACE_LIST[@]}"
}

#start
(
	# init setting
	init

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
			if [ $OPTARG = pc ]; then
				AUTH_UA=$PC_UA
				GW_BTYPE="pc"
			elif [ $OPTARG = pad ]; then
				AUTH_UA=$PAD_UA
				GW_BTYPE="pad"
			elif [ $OPTARG = phone ]; then
				echo "Error: phone type is not be supported now!"
				exit
			else
				echo "Error: plz use the true value(pc/pad/phone)!"
				exit
			fi
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
