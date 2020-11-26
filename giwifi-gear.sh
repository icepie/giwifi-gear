#!/usr/bin/env bash
# giwifi-gear bash cli tool
# by icepie

#############################################
## My Config
#############################################
GW_PORT_DEF='8060'
RD_URL_PORT_DEF=8062
PC_UA="User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36"
PAD_UA="User-Agent: Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25"

#############################################
## General Functions
#############################################

function logcat()
{

	local time=$(date "+%Y-%m-%d %H:%M:%S")

	# check the log flag
	if [ "$2" ];then
		local flag=$2
	else
		local flag='I'
	fi

	if [ "$1" ];then
		echo "$time" "$flag": "$1"
	fi

}

function get_json_value()
{
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

#QUERY_STRING='foo=hello&bar=there&baz=freddy'

get_query_string() {
  local q="$QUERY_STRING"
  local re1='^(\w+=\w+)&?'
  local re2='^(\w+)=(\w+)$'
  while [[ $q =~ $re1 ]]; do
    q=${q##*${BASH_REMATCH[0]}}
    [[ ${BASH_REMATCH[1]} =~ $re2 ]] && eval "$1+=([${BASH_REMATCH[1]}]=${BASH_REMATCH[2]})"
  done
}
# declare -A params
# get_query_string params

# for k in "${!params[@]}"
# do
# 	v="${params[$k]}"
# 	echo "$k: $v"
# done

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
## GiWiFi API Functions
#############################################

function gw_get_auth()
{
    # $1 is $GW_GTW_ADDR
	echo $(json_format "$(curl -s "$1/getApp.htm?action=getAuthState&os=mac")") 
    # os type from index.html
	# if (isiOS) url += "&os=ios";
	# if (isAndroid) url += "&os=android";
	# if (isWinPC) url += "&os=windows";
	# if (isMac) url += "&os=mac";
	# if (isLinux || isUbuntuExplorer()) url += "&os=linux";

}

function gw_get_hotspot_group()
{
    # $1 is $GW_GTW_ADDR and $2 is $RD_URL_PORT
	echo $(json_format "$(curl -s "http:/$1:$2/wifidog/get_hotspot_group")") 
}

function gw_get_api_url()
{
    # $1 is $GW_GTW_ADDR and $2 is $RD_URL_PORT_DEF
    echo $(curl -s -I "http:/$1:$2/redirect?oriUrl=http://www.baidu.com" | grep "Location" | awk -F ": " '{print $2}')
}

function gw_get_auth_state()
{
    #$1 is $GW_HOST and $2 is $GW_PORT
    echo $(curl -s -I "http://$1:$2/wifidog/get_auth_state")
}

#############################################
## Part Functions
#############################################

FUNC_WELCOME(){
    echo '''
  ___ ___      ___ ___ _    ___ ___   _   ___ 
 / __(_) \    / (_) __(_)  / __| __| /_\ | _ \
| (_ | |\ \/\/ /| | _|| | | (_ | _| / _ \|   /
 \___|_| \_/\_/ |_|_| |_|  \___|___/_/ \_\_|_\
                                                                         
    '''                                                   

}

## This is a init part for set some env
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


# Get the gateway of GiWiFi...
## if can not get the redirect url, will check the nic(s) gateway...
FUNC_GET_GTW()
{
    logcat "Try to fetch gateway from redirect url"
    ### try to get the gateway by redirect url
    TEST_URL=$(curl -s 'http://test.gwifi.com.cn/')
    # if not redirect url, it will return:
    # <html><body>it works!</body></html> <!--<script> window.top.location.href="shop/" </script>-->
    if [[ $TEST_URL =~ "it works!" ]];then
        logcat "Fail to get the redirect url" "E"
    elif [[ $TEST_URL =~ "delayURL" ]]; then
    
        RD_URL=$(echo $TEST_URL | grep -oP '(?<=delayURL\(")(.*)(?="\))')

        # LOGIN_PAGE=$(curl -s -I "$RD_URL" | grep 'Location' | awk -F': ' '{print $2}')

        # echo $LOGIN_PAGE

        if [[ $RD_URL =~ $URI_REGEX ]]; then
            RD_URL_HOST=${BASH_REMATCH[7]}
            RD_URL_PORT=${BASH_REMATCH[9]}
        fi

    else
        echo "Error!"
    fi
    
    ## if fail to get the gateway by redirect url method
    if [[ -z $RD_URL_HOST ]]
    then
        logcat "Try to fetch gateway from NIC(s)"
        ## try to get gateway from nic(s)
        if [[ $OS_TYPE = "linux" ]]; then
            NIC_GTW=$(ip neigh | grep -v "br-lan" | grep -v "router" | awk '{print $1}')
        elif [[ $OS_TYPE = "drawin" ]]; then
            NIC_GTW=$(route get default | grep 'gateway' | awk '{print $2}')
        elif [[ $OS_TYPE = "win" ]]; then
            NIC_GTW=$((chcp.com 437 && ipconfig /all) | grep 'Default Gateway' | awk -F': ' '{print $2}')
        fi


        if [[ -z $NIC_GTW ]]
        then
            logcat "Failed to get Gateway, plz connect to the network !!" "E"
        else
            # to auth the nic(s) gateway
            NIC_GTW_TMP=($NIC_GTW)
            NIC_GTW_COUNT=${#NIC_GTW_TMP[@]} 

            # traversing the nic(s) gateway to auth
            for i in $NIC_GTW
            do
                echo $NIC_GTW
                echo $i
                AUTH_TMP=$(gw_get_auth $i)
                echo $AUTH_TMP
                if [[ $AUTH_TMP == *resultCode* ]]
                then
                    NET_GTW=$i
                    GW_TEST+=$i
                    ## check the auth state
                    RES_CODE=$(get_json_value $(get_json_value  $AUTH_TMP data ) 'auth_state')
                    if [[ $RES_CODE == 2 ]]
                    then
                        GW_TEST+="(authed) "
                    else
                        GW_TEST+=" "
                    fi
                    #NET_CARD=$(ip n  | grep "$i" | awk '{print $3}')
                fi
            done

            # check the available nic(s)
            if [[ -z $GW_TEST ]]
            then
                logcat "Failed to get Gateway, plz connect to the GiWiFi !!" "E"
            else
                GW_TEST_TMP=($GW_TEST)
                GW_TEST_COUNT=${#GW_TEST_TMP[@]}

                # set the last gateway
                if [ $GW_TEST_COUNT -gt 1 ];then
                    local ctmp=1

                    echo "NOTE: $GW_TEST_COUNT gateways available"

                    for i in $GW_TEST
                    do
                        echo "- $ctmp: $i"
                        ((ctmp+=1))
                    done

                    read -p "Please input an integer for select a gateway: " s_num
                    while [ 1 ];
                    do
                        if [[ "$s_num" =~ ^[1-9]+$ ]] && [[ $s_num  -ge 1 && $s_num -le $GW_TEST_COUNT ]]; then
                            GW_GTW_ADDR=$(echo "${GW_TEST_TMP[(($s_num-1))]}" | sed s'/(authed)//')
                            echo "using $GW_GTW_ADDR as gateway"
                            break
                        else
                            echo "input error!"
                        fi
                    done
                else
                    GW_GTW_ADDR=$(echo $(echo "$GW_TEST") | sed s'/(authed)//')
                fi
            fi
        fi

    else
        # if you use the redirect url method, the gateway auth will be skipped.
        GW_GTW_ADDR=$RD_URL_HOST
        GW_HOST=$RD_URL_HOST
        GW_RU_PORT=$RD_URL_PORT #redirect url port
    fi

}


FUNC_GET_AUTH()
{
    #echo "$GW_GTW_ADDR"
    if [ ! $GW_GTW_ADDR ]
    then
        logcat "Invalid gateway !" "E"
        exit
    fi

    logcat "Checking the auth state..."

    GW_AUTH_INFO=$(gw_get_auth $GW_GTW_ADDR)
    GW_AUTH_RT_STATE=$(get_json_value $(get_json_value $GW_AUTH_INFO data ) 'auth_state')

    if [[ $GW_AUTH_RT_STATE == 2 ]]
    then
        logcat "You're already authed."
    else
        logcat "You're not authed."
    fi

    logcat "Getting the API URL..."

    # set $GW_RD_URL_PORT
    if [[ $RD_URL_PORT ]]
    then
        GW_RD_URL_PORT=$RD_URL_PORT
    else
        GW_RD_URL_PORT=$RD_URL_PORT_DEF
    fi

    GW_API_URL=$(gw_get_api_url "$GW_GTW_ADDR" "$GW_RD_URL_PORT")

    if [[ $GW_API_URL =~ $URI_REGEX ]]; then
        GW_API_URL_QUERY=$(urldecode "${BASH_REMATCH[13]}")
    fi

    QUERY_STRING=$GW_API_URL_QUERY
    {
    # gw_address=172.21.1.1&gw_port=8060&gw_id=GWIFI-luoyangligong1&ip=172.21.167.114&mac=AC:FD:CE:07:3B:EA&url=http://www.baidu.com&apmac=c4:00:ad:a4:a3:e2&ssid=
    #parse query string
        declare -a pairs
        IFS='&' read -ra pairs <<<"$QUERY_STRING"

        declare -A gw_query
        for pair in "${pairs[@]}"; do
            IFS='=' read -r key value <<<"$pair"
            gw_query["$key"]="$value"
        done

    }
    
    GW_ADDRESS=${gw_query[gw_address]}
    echo $GW_ADDRESS
    GW_PORT=${gw_query[gw_port]}

    GW_HOTSPOT=$(gw_get_hotspot_group $GW_ADDRESS $GW_PORT)
    echo $GW_HOTSPOT
    GW_HOTSPOT_GROUP_ID=$(get_json_value $(get_json_value $GW_HOTSPOT data ) 'hotspot_group_id')
    GW_HOTSPOT_GROUP_NAME=$(get_json_value $(get_json_value $GW_HOTSPOT data ) 'hotspot_group_name')
    GW_HOTSPOT_GROUP_TYPE=$(get_json_value $(get_json_value $GW_HOTSPOT data ) 'hotspot_group_type')

    logcat "Getting the hotspot group info..."

    echo "--------------------------------------------"
    echo "            HOTSPOT GROUP INFO              "
    echo "--------------------------------------------
Group ID:                  $GW_HOTSPOT_GROUP_ID
Group Name:                $GW_HOTSPOT_GROUP_NAME
Group Type:                $GW_HOTSPOT_GROUP_TYPE
--------------------------------------------"

    GW_AUTH_STATE_JSON=$(gw_get_auth_state $GW_ADDRESS $GW_PORT)
    echo $GW_AUTH_STATE_JSON
    ## get
    GW_LOGIN_PAGE=$(curl -s -H "$PC_UA" -s "$GW_API_URL")
    #PAGE_TIME=$(echo -e $GW_API_URL_RT | grep page_time)
    GW_PAGE_TIME=$( echo $(echo $GW_LOGIN_PAGE | grep -oP '(?<=name="page_time" value=")[0-9a-zA-Z%]+') | awk '{ print $1 }')
    GW_SIGN=$(echo $(echo $GW_LOGIN_PAGE | grep -oP '(?<=name="sign" value=")[0-9a-zA-Z%]+') | awk '{ print $1 }')
    echo $GW_PAGE_TIME
    echo $GW_SIGN



}

# MAIN FUNC
(
    FUNC_WELCOME

    FUNC_INIT
    # Print info`
    (
        echo "--------------------------------------------"
        echo "                 OS INFO"
        echo "--------------------------------------------
Arch:                      $(uname -m)
Platform:                  $OS_TYPE
Kernel Type:               $DEVICE_OS
--------------------------------------------"
    )

    FUNC_GET_GTW
    #echo $GW_GTW_ADDR
    # GW_HOST=$RD_URL_HOST
    # GW_PORT=$RD_URL_PORT

    FUNC_GET_AUTH


)2>&2 | tee -a ./connect.log
