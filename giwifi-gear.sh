#/bin/sh
# giwifi-gear bash cli tool
# by icepie

#############################################
## config
#############################################
# tool info
VERSION=0.11

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

#############################################
## url handle
#############################################
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

#############################################
## json handle
#############################################
get_json_value() {
	awk -v json="$1" -v key="$2" -v defaultValue="$3" 'BEGIN{
        foundKeyCount = 0
        while (length(json) > 0) {
            # pos = index(json, "\""key"\""); ## 这行更快一些，但是如果有value是字符串，且刚好与要查找的key相同，会被误认为是key而导致值获取错误
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
	echo $(json_format "$(curl -s -A "$AUTH_UA" "$1")")
}

#############################################
## cli related
#############################################
init() {
	# check dep
	if ! [ -x "$(command -v curl)" ]; then
		echo 'Error: curl is not installed.' >&2
		exit 1
	fi

	# set default ua
	AUTH_UA=$PC_UA
    GW_BTYPE="pc"
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
  giwifi-gear.sh [-h] [-g GATEWAY] [-u USERNAME] [-p PASSWORD] [-t TYPE] [-i] [-q] [-b] [-d] [-v]

optional arguments:
  -h, --help            show this help message and exit
  -g GATEWAY, --gateway GATEWAY
  -u USERNAME, --username USERNAME
  -p PASSWORD, --password PASSWORD
  -t TYPE, --type TYPE  auth type(use pc/pad/phone, and the default value is pc)
  -i, --info            print the debug info
  -b, --bind            bind or rebind your devices
  -q, --quit            sign out of account authentication 
  -d, --daemon          running in the background guard (remove sharing restrictions)
  -v, --version         show program's version number and exit

  example: 
    # bind your device with pad type
    ./giwifi-gear.sh -g 172.21.1.1 -u 13000000001 -p mypassword -t pad -r

    # auth with daemon mode
    ./giwifi-gear.sh -g 172.21.1.1 -u 13000000001 -p mypassword -d

    # quit auth
    ./giwifi-gear.sh -g 172.21.1.1 -q

(c) 2020 icepie.dev@gmail.com\
"
}

main() {
	# # check param
	# if [ $# -le 0 ]; then
	#   usage
	#   exit
	# fi

	#
	while true; do
		case "$1" in
		-g | --gateway)
			GW_GTW="$2"
			shift
			;;
		-u | --username)
			GW_USER="$2"
			shift
			;;
		-p | --password)
			GW_PWD="$2"
			shift
			;;
		-t | --type)
			type="$2"
			# if [ $type = pc ]; then
			# 	AUTH_UA=$PC_UA
            #   GW_BTYPE="pc"
			# elif ...
            if [ $type = pad ]; then
				AUTH_UA=$PAD_UA
                GW_BTYPE="pad"
			elif [ $type = phone ]; then
				echo "Error: phone type is not be supported now!"
				exit
			else
				echo "Error: plz use the true value(pc/pad/phone)!"
				exit
			fi
			echo "type:    $type"
			echo "UA:      $AUTH_UA"
			shift
			;;
		-i | --info)
			ISINFO=1
			;;
		-b | --bind)
			ISBIND=1
			;;
		-q | --quit)
			ISQUIT=1
			;;
		-d | --daemon)
			ISDAEMON=1
			;;
		-v | --version)
			ver
			exit 1
			;;
		-h | --help)
			usage
			exit
			;;
		--)
			shift
			break
			;;
		*)
			echo "$1 is not option"
			;;
		esac
		shift
	done

	# check the conflicting parameters
	if ([ $ISBIND ] && [ $ISQUIT ]) || ([ $ISBIND ] && [ $ISDAEMON ]) || ([ $ISQUIT ] && [ $ISDAEMON ]); then
		echo "Error: don't use bind, quit and daemon at same time!"
		exit 1
	fi

	# check the necessary parameters
	if [ ! $GW_GTW ]; then
		echo -n "Plz enter gateway: "
		read GW_GTW
	fi

    # gtw auth auth
    GW_GTW_AUTH=$(gw_get_gtw_auth $GW_GTW)
	if [ $ISINFO ]; then
        echo "GW_GTW_AUTH: "
        echo '-->' $GW_GTW_AUTH
        echo ''
	fi

	# quit option
	if [ $ISQUIT ]; then
		GW_QUIT_RTE=$(gw_logout $GW_GTW)
        echo $GW_QUIT_RTE
		exit 1
	fi

	if [ ! $GW_USER ]; then
		echo -n "Plz enter username: "
		read GW_USER
	fi

	if [ ! $GW_PWD ]; then
		echo -n "Plz enter password: "
		read GW_PWD
	fi

	echo "gateway: $GW_GTW"
	echo "username: $GW_USER"
	echo "password: $GW_PWD"

    # get login page
    GW_LOGIN_PAGE=$(gw_get_login_page $GW_GTW)
    if [ $ISINFO ]; then
        echo $GW_LOGIN_PAGE

        echo GW_LOGIN_PAGE:
        echo '-->' $GW_LOGIN_PAGE
        echo ''
        echo ''
    fi

    # get auth url
    GW_AUTH_URL=$(gw_get_auth_url $GW_GTW)
    if [ $ISINFO ]; then
        echo GW_AUTH_URL:
        echo '-->' $GW_AUTH_URL
        echo ''
    fi

    # get the giwfi login page port
    GW_PORT=$(str_str "$GW_AUTH_URL" "gw_port=" "&")

    # get some values from login page
    GW_SIGN=$(str_str "$GW_LOGIN_PAGE" 'name="sign" value="' '"')
    GW_PAGE_TIME=$(str_str "$GW_LOGIN_PAGE" 'name="page_time" value="' '"')
    if [ $ISINFO ]; then
        echo GW_SIGN: $GW_SIGN
        echo GW_PAGE_TIME: $GW_PAGE_TIME
        echo ''
    fi

    # get sauth state json
    GW_AUTH_STATE=$(gw_get_auth_state $GW_GTW $GW_PORT)
    GW_AUTH_STATE_DATA=$(get_json_value $GW_AUTH_STATE 'data')
    GW_ID=$(get_json_value $GW_AUTH_STATE_DATA 'gw_id')
    if [ $ISINFO ]; then
        echo GW_AUTH_STATE:
        echo '-->' $GW_AUTH_STATE
        echo ''
        echo GW_ID: $GW_ID
        echo ''
    fi

    # login to get the auth token
    GW_LOGIN_DATA=$(echo """\
access_type=$(get_json_value $GW_AUTH_STATE_DATA 'access_type')\
&acsign=$(get_json_value $GW_AUTH_STATE_DATA 'sign')\
&btype=$GW_BTYPE\
&client_mac=$(url_encode $(get_json_value $GW_AUTH_STATE_DATA 'client_mac'))\
&contact_phone=$(get_json_value $GW_AUTH_STATE_DATA 'contact_phone')\
&devicemode=""\
&gw_address=$(str_str "$GW_AUTH_URL" "gw_address=" "&")\
&gw_id=$(str_str "$GW_AUTH_URL" "gw_id=" "&")\
&gw_port=$(str_str "$GW_AUTH_URL" "gw_port=" "&")\
&lastaccessurl=""\
&logout_reason=$(get_json_value $GW_AUTH_STATE_DATA 'logout_reason')\
&mac=$(url_encode $(str_str "$GW_AUTH_URL" "mac=" "&"))\
&name=$GW_USER\
&online_time=$(get_json_value $GW_AUTH_STATE_DATA 'online_time')\
&page_time=$GW_PAGE_TIME\
&password=$GW_PWD\
&sign=$(url_encode $GW_SIGN)\
&station_cloud=$(get_json_value $GW_AUTH_STATE_DATA 'station_cloud')\
&station_sn=$(get_json_value $GW_AUTH_STATE_DATA 'station_sn')\
&suggest_phone=$(get_json_value $GW_AUTH_STATE_DATA 'suggest_phone')\
&url=$(str_str "$GW_AUTH_URL" "url=" "&")\
&user_agent=""\
"""  | sed 's/"//g' )

    if [ $ISINFO ]; then
        echo GW_LOGIN_DATA: $GW_LOGIN_DATA
        echo ''
    fi

    if [ $ISBIND ]; then
        GW_BIND_DATA=$GW_LOGIN_DATA+"&is_signed=2"
        echo $GW_BIND_DATA
        GW_BIND_RTE=$(gw_rebindmac $GW_BIND_DATA)
        echo $GW_BIND_RTE
    else
        GW_LOGIN_RTE=$(gw_loginaction $GW_LOGIN_DATA)
        echo $GW_LOGIN_RTE
    fi
    
}

#start
(
	init
	eval set -- $(getopt -o g:u:p:t:ibqdvh --long gateway:,username:,password:,type:,info,bind,quit,daemon,version,help -- "$@")
	main $@
)
