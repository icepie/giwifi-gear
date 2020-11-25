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

function GW_GET_AUTH()
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

#############################################
## Part Functions
#############################################

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
                AUTH_TMP=$(GW_GET_AUTH $i)
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
                logcat "Failed to get Gateway, plz connect to the network !!" "E"
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

                    while [ 1 ];
                    do
                        read -p "Please input an integer for select a gateway: " s_num
                        if [[ "$s_num" =~ ^[1-9]+$ ]] && [[ $s_num  -ge 1 && $s_num -le $GW_TEST_COUNT ]]; then
                            GW_GTW_ADDR=$(echo "${GW_TEST_TMP[(($s_num-1))]}" | sed s'/(authed)//')
                            echo "using $GW_GTW_ADDR as gateway"
                            break
                        else
                            echo "input error!"
                        fi
                    done
                else
                    GW_GTW_ADDR=$GW_TEST
                fi
            fi
        fi

    else
        # if you use the redirect url method, the gateway auth will be skipped.  
        GW_GTW_ADDR=$RD_URL_HOST
        GW_HOST=$RD_URL_HOST
        GW_PORT=$RD_URL_PORT
    fi

}


# MAIN FUNC
(

    FUNC_INIT
    echo $DEVICE_OS

    FUNC_GET_GTW
    echo $GW_GTW_ADDR

    GW_GET_AUTH $GW_GTW_ADDR
)