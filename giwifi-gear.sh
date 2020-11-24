#!/usr/bin/env bash
# giwifi-gear bash cli tool
# by icepie

#############################################
## My Config
#############################################
GW_IP='172.21.1.1'
GW_PORT='8062'


#############################################
## General Functions
#############################################

function logcat()
{

	local time=$(date "+%Y-%m-%d %H:%M:%S")

	# check the log flag
	if [ ! -n $2 ];then
		local flag=$2
	else
		local flag='I'
	fi

	if [ "$1" ];then
		echo "$flag" "$time": "$1"
	fi

}

function get_json_value()
{
  local json=$1
  local key=$2

  if [[ -z "$3" ]]; then
    local num=1
  else
    local num=$3
  fi

  local value=$(echo "${json}" | awk -F"[,:}]" '{for(i=1;i<=NF;i++){if($i~/'${key}'\042/){print $(i+1)}}}' | tr -d '"' | sed -n ${num}p)

  echo ${value}
}

function json_format(){
	local json=$1
	# Del escape character and format text
	echo $(echo -e $1) | sed "s@\\\\@@g"
}


urlencode() {
    # urlencode <string>

    old_lc_collate=$LC_COLLATE
    LC_COLLATE=C

    local length="${#1}"
    for (( i = 0; i < length; i++ )); do
        local c="${1:$i:1}"
        case $c in
            [a-zA-Z0-9.~_-]) printf '%s' "$c" ;;
            *) printf '%%%02X' "'$c" ;;
        esac
    done

    LC_COLLATE=$old_lc_collate
}

urldecode() {
    # urldecode <string>

    local url_encoded="${1//+/ }"
    printf '%b' "${url_encoded//%/\\x}"
}

# Following regex is based on https://tools.ietf.org/html/rfc3986#appendix-B with
# additional sub-expressions to split authority into userinfo, host and port
#
readonly URI_REGEX='^(([^:/?#]+):)?(//((([^:/?#]+)@)?([^:/?#]+)(:([0-9]+))?))?(/([^?#]*))(\?([^#]*))?(#(.*))?'
#                    ↑↑            ↑  ↑↑↑            ↑         ↑ ↑            ↑ ↑        ↑  ↑        ↑ ↑
#                    |2 scheme     |  ||6 userinfo   7 host    | 9 port       | 11 rpath |  13 query | 15 fragment
#                    1 scheme:     |  |5 userinfo@             8 :…           10 path    12 ?…       14 #…
#                                  |  4 authority
#                                  3 //…


# if [[ "http://172.21.1.2:8062/redirect\?oriUrl\=http://www.baidu.com" =~ $URI_REGEX ]]; then
#         echo proto=${BASH_REMATCH[2]}
#         echo host=${BASH_REMATCH[7]}
#         echo port=${BASH_REMATCH[9]}
#         echo path=${BASH_REMATCH[10]}
#         echo query=${BASH_REMATCH[13]}
# fi

#############################################
## Special Functions
#############################################


FUNC_INIT(){

    ## set the os type
    # Mac OS X
    if [ "$(uname)" = "Darwin" ]; then
        DEVICE_OS=$(uname -o)
        OS_TYPE="darwin"
    #  GNU/Linux && Android
    elif [ "$(uname)" = "Linux" ]; then
        DEVICE_OS=$(uname -o)
        OS_TYPE="linux"
    # Windows
    elif [ -x "$(command -v chcp.com)" ];then
        DEVICE_OS=$(uname -s)
        OS_TYPE="win"
    else
        DEVICE_OS=$(uname)
        OS_TYPE="null"
    fi

    ## null os
    if [ $OS_TYPE = "null" ];then
        echo "Ur OS not be supported: $DEVICE_OS"
        exit
    fi
}

FUNC_GET_GW()
{
    ### try to get the gateway by redirect url
    TEST_URL=$(curl -s 'http://test.gwifi.com.cn/')
    # if not redirect url, it will return:
    # <html><body>it works!</body></html> <!--<script> window.top.location.href="shop/" </script>-->
    if [[ $TEST_URL =~ "it works!" ]];then
        echo "No Redirect URL"
    elif [[ $TEST_URL =~ "delayURL" ]]; then

        echo $TEST_URL
        RD_URL=$(echo $TEST_URL | grep -oP '(?<=delayURL\(")(.*)(?="\))')

        LOGIN_PAGE

        if [[ $RD_URL =~ $URI_REGEX ]]; then
            RD_URL_HOST=${BASH_REMATCH[7]}
            RD_URL_PORT=${BASH_REMATCH[9]}
        fi
        echo $RD_URL_HOST $RD_URL_PORT

    else
        echo "Error!"
    fi
    
    ## try to get gateway from nic(s)
    if [[ $OS_TYPE = "linux" ]]; then
        echo "nice"
    fi


}

function GET_AUTH()
{

	AUTH_STATE=$(json_format "$(curl -s "$1/getApp.htm?action=getAuthState&os=mac")") 
	# os type from index.html
	# if (isiOS) url += "&os=ios";
	# if (isAndroid) url += "&os=android";
	# if (isWinPC) url += "&os=windows";
	# if (isMac) url += "&os=mac";
	# if (isLinux || isUbuntuExplorer()) url += "&os=linux";

	echo $AUTH_STATE
}


# MAIN FUNC
(

    FUNC_INIT
    echo $DEVICE_OS

    FUNC_GET_GW

    GET_AUTH $AUTH_STATE
)