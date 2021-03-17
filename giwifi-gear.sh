#!/bin/bash
# giwifi-gear bash cli tool
# by icepie

#############################################
## config
#############################################
# tool info
VERSION=0.22

# user info
GW_GTW=""
GW_USER=""
GW_PWD=""

# giwifi default info
GW_PORT_DEF=8060
RD_URL_PORT_DEF=8062

# auth setting
PC_UA="Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36"
PAD_UA="Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25"
HEART_BEAT=9

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
	#str_str <string> "str" "str"

	local str
	str="${1#*${2}}"
	str="${str%%$3*}"
	echo -n "$str"
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
		echo "$time" "$flag": "$1"
	fi
}

#############################################
## json handle
#############################################
get_json_value() {
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

json_format() {
	local json=$1
	# Del escape character and format text
	echo $(echo -e $1) | sed "s@\\\\@@g"
}

#############################################
## giwifi api
#############################################
gw_get_gtw_auth() {
	echo $(json_format "$(curl -s -A "$AUTH_UA" "$1/getApp.htm?action=getAuthState&os=mac")")
	# os type from index.html
	# if (isiOS) url += "&os=ios";
	# if (isAndroid) url += "&os=android";
	# if (isWinPC) url += "&os=windows";
	# if (isMac) url += "&os=mac";
	# if (isLinux || isUbuntuExplorer()) url += "&os=linux";
}

gw_logout() {
	echo $(json_format "$(curl -s -A "$AUTH_UA" "$1/getApp.htm?action=logout")")
}

gw_get_auth_url() {
	echo $(curl -s -I -A "$AUTH_UA" "http://$1:8062/redirect?oriUrl=http://www.baidu.com" | grep "Location" | awk -F ": " '{print $2}')
}

gw_get_login_page() {
	echo $(curl -s -L -A "$AUTH_UA" "http://$1:8062/redirect?oriUrl=http://www.baidu.com" | grep "name=")
}

gw_get_hotspot_group() {
	echo $(json_format "$(curl -s -A "$AUTH_UA" "http:/$1:$2/wifidog/get_hotspot_group")")
}

gw_get_auth_state() {
	echo $(json_format "$(curl -s -A "$AUTH_UA" "http://$1:$2/wifidog/get_auth_state")")
}

gw_loginaction() {
	# create random three-digit numbers
	local str=$(date +%S%M)
	local rannum=${str:1:3}

	echo $(json_format "$(
		curl -s \
		-A "$AUTH_UA" \
		-X POST \
		-H 'Accept: */*' \
		-H 'Connection: keep-alive' \
		-H 'Content-Type: application/x-www-form-urlencoded' \
		-H 'accept-encoding: gzip, deflate, br' \
		-H 'accept-language: zh-CN,zh-TW;q=0.8,zh;q=0.6,en;q=0.4,ja;q=0.2' \
		-H 'cache-control: max-age=0' \
		-d "$1" \
		"http://login.gwifi.com.cn/cmps/admin.php/api/loginaction?round=$rannum" | gunzip
	)")
	# use gunzip cause some curl not support --compressed option
}

gw_rebindmac() {
	# create random three-digit numbers
	local str=$(date +%S%M)
	local rannum=${str:1:3}

	echo $(json_format "$(
		curl -s \
		-A "$AUTH_UA" \
		-X POST \
		-H 'Accept: */*' \
		-H 'Connection: keep-alive' \
		-H 'Content-Type: application/x-www-form-urlencoded' \
		-H 'accept-encoding: gzip, deflate, br' \
		-H 'accept-language: zh-CN,zh-TW;q=0.8,zh;q=0.6,en;q=0.4,ja;q=0.2' \
		-H 'cache-control: max-age=0' \
		-d "$1" \
		"http://login.gwifi.com.cn/cmps/admin.php/api/reBindMac?round=$rannum" | gunzip
	)")
	# use gunzip cause some curl not support --compressed option
}

gw_auth_token() {
	echo $(curl -s -I -A "$AUTH_UA" "$1")
	# maybe somewhere can not return a web page, so use the -I
}

#############################################
## cli related
#############################################
init() {
	# check dep
	hash curl 2>/dev/null || { echo 'Error: curl is not installed.' >&2; exit 1; }

# set default ua
	AUTH_UA=$PC_UA
	GW_BTYPE="pc"

	# set the tool path
	TOOL_PATH=$0

}

ver() {
	echo "\
giwifi-gear version $VERSION\
    "
}

usage() {
	echo "\
giwifi-gear
  A cli tool for login giwifi (multi-platform, fast, small)

usage:
  giwifi-gear.sh [-h] [-g <GATEWAY>] [-u <USERNAME>] [-p <PASSWORD>] [-t <TYPE>] [-i] [-q] [-b] [-d] [-v]

optional arguments:
  -h                    show this help message and exit
  -g <GATEWAY>          set the gateway
  -u <USERNAME>         set the username
  -p <PASSWORD>         set the password
  -t <TYPE>             auth type(use pc/pad/phone, and the default value is pc)
  -i                    print the debug info
  -b                    bind or rebind your devices
  -q                    sign out of account authentication
  -d                    running in the daemon mode (remove sharing restrictions)
  -v                    show the tool version info and exit

example:
  # bind your device with pad type
  ./giwifi-gear.sh -g 172.21.1.1 -u 13000000001 -p mypassword -t pad -r

  # auth with daemon mode
  ./giwifi-gear.sh -g 172.21.1.1 -u 13000000001 -p mypassword -d

  # quit auth
  ./giwifi-gear.sh -g 172.21.1.1 -q

(c) 2021 icepie.dev@gmail.com\
"
}

main() {

	[ $ISINFO ] && \
	echo "TOOL_PATH:" && \
	echo "--> $TOOL_PATH" && \
	echo "";

	# check the conflicting parameters
	if ([ $ISBIND ] && [ $ISQUIT ]) || ([ $ISBIND ] && [ $ISDAEMON ]) || ([ $ISQUIT ] && [ $ISDAEMON ]); then
		echo "Error: don't use bind, quit and daemon at same time!"
		exit 1;
	fi

	# check the necessary parameters
	while [ ! $GW_GTW ]; do
		echo -n "Plz enter gateway: "
		read GW_GTW
	done

	# gtw auth auth
	logcat "Try to get the auth info..."
	GW_GTW_AUTH_RTE=$(gw_get_gtw_auth $GW_GTW)
	[ ! "$GW_GTW_AUTH_RTE" ] && \
	logcat "Failed to get the gateway info, plz check the gateway host" "E" && \
	exit 1;

	[ $ISINFO ] && \
	echo "" && \
	echo "GW_GTW_AUTH_RTE: " && \
	echo "--> $GW_GTW_AUTH_RTE" && \
	echo "";

	GW_GTW_AUTH_RTE_DATA="$(get_json_value $GW_GTW_AUTH_RTE 'data')"

	[ $ISINFO ] && \
	echo "" && \
	echo "GW_GTW_AUTH_RTE_DATA: " && \
	echo "--> $GW_GTW_AUTH_RTE_DATA" && \
	echo "";

	if [ "$(get_json_value $GW_GTW_AUTH_RTE_DATA 'auth_state')" == '2' ]; then
		# 2 is logged
		logcat "Good! you are authed!"

		[ ! $ISQUIT ] && \
		[ ! $ISDAEMON ] && \
		logcat "exit" && \
		exit 1
	else
		logcat "The device not authed!"
		[ $ISQUIT ] && \
		logcat "Error: you do not need to logout" && \
		exit 1
	fi

	# quit option
	if [ $ISQUIT ]; then
		logcat "Try to login out "

		GW_QUIT_RTE=$(gw_logout $GW_GTW)
		[ ! "$GW_GTW_AUTH_RTE" ] && \
		logcat "Failed to get the quit info, plz retry" "E" && \
		exit 1

		[ $ISINFO ] && \
		echo "" && \
		echo "GW_QUIT_RTE: " && \
		echo "--> $GW_QUIT_RTE" && \
		echo "";

		if [ $(get_json_value $GW_QUIT_RTE 'resultCode') -eq 0 ]; then
			logcat "sign out of account successfully"
		else
			logcat "Failed to sign out of account"
		fi
		exit 1
	fi

	# check the username and password
	while [ ! $GW_USER ]; do
		echo -n "Plz enter username: "
		read GW_USER
	done

	while [ ! $GW_PWD ]; do
		echo -n "Plz enter password: "
		read GW_PWD
	done

	# get login page
	GW_LOGIN_PAGE=$(gw_get_login_page $GW_GTW)
	[ ! "$GW_LOGIN_PAGE" ] && \
	logcat "Failed to get the login page, plz try again" "E" && \
	exit 1;

	[ $ISINFO ] && \
	echo "GW_LOGIN_PAGE: " && \
	echo "--> $GW_LOGIN_PAGE" && \
	echo "";

	# get auth url
	GW_AUTH_URL=$(gw_get_auth_url $GW_GTW)
	[ ! "$GW_AUTH_URL" ] && \
	logcat "Failed to get the auth url, plz try again" "E" && \
	exit 1;

	[ $ISINFO ] && \
	echo "GW_AUTH_URL: " && \
	echo "--> $GW_AUTH_URL" && \
	echo "";

	# get the giwfi login page port
	GW_PORT=$(str_str "$GW_AUTH_URL" "gw_port=" "&")

	# get some values from login page
	GW_SIGN=$(str_str "$GW_LOGIN_PAGE" 'name="sign" value="' '"')
	GW_PAGE_TIME=$(str_str "$GW_LOGIN_PAGE" 'name="page_time" value="' '"')

	[ $ISINFO ] && \
	echo "GW_SIGN: $GW_SIGN" && \
	echo "GW_PAGE_TIME: $GW_PAGE_TIME" && \
	echo "";

	# get sauth state json
	GW_AUTH_STATE_RTE=$(gw_get_auth_state $GW_GTW $GW_PORT)
	[ ! "$GW_AUTH_STATE_RTE" ] && \
	logcat "Failed to get the auth state, plz try again" "E" && \
	exit 1;

	GW_AUTH_STATE_RTE_DATA=$(get_json_value $GW_AUTH_STATE_RTE 'data')
	GW_ID=$(get_json_value "$GW_AUTH_STATE_RTE_DATA" 'gw_id' | sed 's/"//g')

	[ $ISINFO ] && \
	echo "GW_AUTH_STATE_RTE:" && \
	echo "--> $GW_AUTH_STATE_RTE" && \
	echo "" && \
	echo "GW_ID: $GW_ID" && \
	echo "";

	GW_APMAC=$(str_str "$GW_AUTH_URL" "apmac=" "&")
	GW_ADDRESS=$(str_str "$GW_AUTH_URL" "gw_address=" "&")
	GW_PORT=$(str_str "$GW_AUTH_URL" "gw_port=" "&")
	DEV_IP=$(str_str "$GW_AUTH_URL" "ip=" "&")
	DEV_MAC=$(str_str "$GW_AUTH_URL" "mac=" "&")
	STATION_SN=$(get_json_value $GW_AUTH_STATE_RTE_DATA 'station_sn' | sed 's/"//g')

	# login to get the auth token
	GW_LOGIN_DATA=$(echo """\
access_type=$(get_json_value $GW_AUTH_STATE_RTE_DATA 'access_type')\
&acsign=$(get_json_value $GW_AUTH_STATE_RTE_DATA 'sign')\
&btype=$GW_BTYPE\
&client_mac=$(url_encode $(get_json_value $GW_AUTH_STATE_RTE_DATA 'client_mac'))\
&contact_phone=$(get_json_value $GW_AUTH_STATE_RTE_DATA 'contact_phone')\
&devicemode=""\
&gw_address=$GW_ADDRESS\
&gw_id=$GW_ID\
&gw_port=$GW_PORT\
&lastaccessurl=""\
&logout_reason=$(get_json_value $GW_AUTH_STATE_RTE_DATA 'logout_reason')\
&mac=$(url_encode $DEV_MAC)\
&name=$GW_USER\
&online_time=$(get_json_value $GW_AUTH_STATE_RTE_DATA 'online_time')\
&page_time=$GW_PAGE_TIME\
&password=$GW_PWD\
&sign=$(url_encode $GW_SIGN)\
&station_cloud=$(get_json_value $GW_AUTH_STATE_RTE_DATA 'station_cloud')\
&station_sn=$STATION_SN\
&suggest_phone=$(get_json_value $GW_AUTH_STATE_RTE_DATA 'suggest_phone')\
&url=$(str_str "$GW_AUTH_URL" "url=" "&")\
&user_agent=""\
""" | sed 's/"//g')

	[ $ISINFO ] && \
	echo "GW_LOGIN_DATA: $GW_LOGIN_DATA" && \
	echo '';

	if [ $ISBIND ]; then
		# bind post need add some data
		GW_BIND_DATA=$GW_LOGIN_DATA+"&is_signed=2"

		GW_BIND_RTE=$(gw_rebindmac $GW_BIND_DATA)
		[ ! "$GW_BIND_RTE" ] && \
		logcat "Failed to bind(rebind) device, plz try again" "E" && \
		exit 1;

		[ $ISINFO ] && \
		echo "GW_BIND_RTE:" && \
		echo "--> $GW_BIND_RTE" && \
		echo "";

		GW_BIND_RTE_STATUS=$(get_json_value "$GW_BIND_RTE" 'status')
		GW_BIND_RTE_INFO=$(str_str "$GW_BIND_RTE" ',"info":"' '",')

		if [ "$GW_BIND_RTE_STATUS" -eq 1 ]; then
			logcat "Bind the device successfully"
			logcat "Please authenticate again!"
		else
			logcat "Failed to bind the device" "E"
			logcat "Info: $GW_BIND_RTE_INFO"
			exit 1;
		fi

	else
		logcat "Try to get the token with $GW_BTYPE type..."

		GW_LOGIN_RTE=$(gw_loginaction $GW_LOGIN_DATA)
		[ ! "$GW_LOGIN_RTE" ] && \
		logcat "Failed to get the loginaction, plz try again" "E" && \
		exit 1;

		[ $ISINFO ] && \
		echo "GW_LOGIN_RTE:" && \
		echo "--> $GW_LOGIN_RTE" && \
		echo "";

		GW_LOGIN_RTE_STATUS=$(get_json_value "$GW_LOGIN_RTE" 'status')
		GW_LOGIN_RTE_INFO=$(str_str "$GW_LOGIN_RTE" ',"info":"' '",')

		# check the login status
		if [ "$GW_LOGIN_RTE_STATUS" -eq 1 ]; then
			logcat "Get the auth token successfully"
			logcat "Try to auth with token..."

			# the last step
			GW_AUTH_RTE=$(gw_auth_token $GW_LOGIN_RTE_INFO)

			[ ! "$GW_AUTH_RTE" ] && \
			logcat "Failed to auth use the token, plz try again" "E" && \
			exit 1;

			# clear screen
			[ ! $ISINFO ] && \
			cls || clear;

			echo """\
--------------------------------------------
SSID:             $GW_ID
AP MAC:           $GW_APMAC
GateWay:          $GW_ADDRESS
Type:             $GW_BTYPE
IP:               $DEV_IP
MAC:              $DEV_MAC
Station SN:       $STATION_SN
Logged:           yes
--------------------------------------------\
"""
			logcat "Auth successfully"

			if [ $ISDAEMON ]; then
				local i=1
				while :; do
					sleep $HEART_BEAT
					logcat "Heartbeat: $i" && i=$((i+1))
					GW_AUTH_RTE=$(gw_auth_token $GW_LOGIN_RTE_INFO)
					[ ! "$GW_AUTH_RTE" ] && \
					break
				done
				# restart app
				main
			fi

		else
			logcat "Failed to get the auth token" "E"
			logcat "Info: $GW_LOGIN_RTE_INFO"
			exit 1
		fi

	fi
}

#start
(
	# init setting
	init

	# get the opts
	#eval set -- $(getopt -o g:u:p:t:ibqdvh --long gateway:,username:,password:,type:,info,bind,quit,daemon,version,help -- "$@")
	while getopts "g:u:p:t:qbdivh" option; do
	case $option in
		g)
			GW_GTW=$OPTARG
			;;
		u)
			GW_USER=$OPTARG
			;;
		p)
			GW_PWD=$OPTARG
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
		q)
			ISQUIT=1
			;;
		b)
			ISBIND=1
			;;
		d)
			ISDAEMON=1
			;;
		i)
			ISINFO=1
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
	esac
done

	# main func
	main
)
