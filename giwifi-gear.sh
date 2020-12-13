#/bin/sh
# giwifi-gear bash cli tool
# by icepie

#############################################
## config
#############################################
VERSION=0.11
GW_NAME="1010101010101"
GW_PWD="password"
GW_PORT_DEF=8060
RD_URL_PORT_DEF=8062
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
	echo $(json_format "$(curl -s -A "$UA" "$1/getApp.htm?action=getAuthState&os=mac")")
}

gw_logout() {
	echo $(json_format "$(curl -s -A "$UA" "$1/getApp.htm?action=logout")")
}

gw_get_auth_url() {
	echo $(curl -s -I -A "$UA" "http://$1:8062/redirect?oriUrl=http://www.baidu.com" | grep "Location" | awk -F ": " '{print $2}')
}

gw_get_login_page() {
	echo $(curl -s -L -A "$UA" "http://$1:8062/redirect?oriUrl=http://www.baidu.com" | grep "name=")
}

gw_get_hotspot_group() {
	echo $(json_format "$(curl -s -A "$UA" "http:/$1:$2/wifidog/get_hotspot_group")")
}

gw_get_auth_state() {
	echo $(json_format "$(curl -s -A "$UA" "http://$1:$2/wifidog/get_auth_state")")
}

gw_loginaction() {
	# create random three-digit numbers
	local str=$(date +%S%M)
	local rannum=${str:1:3}

	echo $(json_format "$(
		curl -s \
		-A "$UA" \
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

}

gw_rebindmac() {
	# create random three-digit numbers
	local str=$(date +%S%M)
	local rannum=${str:1:3}

	echo $(json_format "$(
		curl -s \
		-A "$UA" \
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

}

gw_auth_token() {
	echo $(json_format "$(curl -s -A "$UA" "$1")")
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
  giwifi-gear.sh [-h] [-g GATEWAY] [-u USERNAME] [-p PASSWORD] [-t TYPE] [-r] [-q] [-d] [-v]

optional arguments:
  -h, --help            show this help message and exit
  -g GATEWAY, --gateway GATEWAY
  -u USERNAME, --username USERNAME
  -p PASSWORD, --password PASSWORD
  -t TYPE, --type TYPE  auth type(use pc/pad/phone, and the default value is pc)
  -b, --bind          bind or rebind your devices
  -q, --quit            sign out of account authentication 
  -d, --daemon          running in the background guard (remove sharing restrictions)
  -v, --version         show program's version number and exit

  example: 
    # bind your device with pad type
    giwifi-gear.sh -g 172.21.1.1 -u 13000000001 -p mypassword -t pad -r

    # auth with daemon mode
    giwifi-gear.sh -g 172.21.1.1 -u 13000000001 -p mypassword -d

    # quit auth
    giwifi-gear.sh -g 172.21.1.1 -q

(c) 2020 icepie.dev@gmail.com\
"
	# 短格式中，选项值为可选的选项，选项值只能紧接选项而不可使用任何符号将其他选项隔开；如-p80，不要写成性-p 80
	# 短格式中，选项值为必有的选项，选项值既可紧接选项也可以使用空格与选项隔开；如-i192.168.1.1，也可写成-i 192.168.1.1
	# 长格式中，选项值为可选的选项，选项值只能使用=号连接选项；如--port=80，不可写成性--port80或--port 80
	# 长格式中，选项值为必有的选项，选项值既可使用=号连接选项也可使用空格连接选项；如--ip=192.168.1.1，也可写成--ip 192.168.1.1
	# 为简便起见，建议凡是短格式都使用“选项+选项值”的形式（-p80），凡是长格式都使用“选项+=+选项值”的形式（--port=80）
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
			gateway="$2"
			shift
			;;
		-u | --username)
			username="$2"
			echo "username:    $username"
			shift
			;;
		-p | --password)
			password="$2"
			echo "password:    $password"
			shift
			;;
		-t | --type)
			type="$2"
			if [ $type = pc ]; then
				AUTH_UA=$PC_UA
			elif [ $type = pad ]; then
				AUTH_UA=$PAD_UA
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
	# 剩余所有未解析到的参数存在$@中，可通过遍历$@来获取
	#for param in "$@"
	#do
	#  echo "Parameter #$count: $param"
	#done

	# check the conflicting parameters
	if ([ $ISBIND ] && [ $ISQUIT ]) || ([ $ISBIND ] && [ $ISDAEMON ]) || ([ $ISQUIT ] && [ $ISDAEMON ]); then
		echo "Error: don't use bind, quit and daemon at same time!"
		exit 1
	fi

	# check the necessary parameters
	if [ ! $gateway ]; then
		echo -n "Plz enter gateway: "
		read gateway
	fi

	# quit option
	if [ $ISQUIT ]; then
		echo "quiting"
		exit 1
	fi

	if [ ! $username ]; then
		echo -n "Plz enter username: "
		read username
	fi

	if [ ! $password ]; then
		echo -n "Plz enter password: "
		read password
	fi

	echo "gateway: $gateway"
	echo "username: $username"
	echo "password: $password"

}

# 如果只注册短格式可以如下这样子
# set -- $(getopt i:p::h "$@")
# 如果要注册长格式需要如下这样子
# -o注册短格式选项
# --long注册长格式选项
# 选项后接一个冒号表示其后为其参数值，选项后接两个冒号表示其后可以有也可以没有选项值，选项后没有冒号表示其后不是其参数值

#start
(
	init
	eval set -- $(getopt -o g:u:p:t:bqdvh --long gateway:,username:,password:,type:,bind,quit,daemon,version,help -- "$@")
	# 由于是在main函数中才实现参数处理，所以需要使用$@将所有参数传到main函数

	main $@
)
