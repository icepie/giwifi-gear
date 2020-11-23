#!/usr/bin/env bash
# giwifi-gear bash cli tool
# by icepie

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
        echo Ur OS not be supported: $DEVICE_OS
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
        echo "no redirect url"
    elif [[ $TEST_URL =~ "delayURL" ]]; then
        echo $TEST_URL
        RD_URL=$(echo $TEST_URL | grep -oP '(?<=delayURL\(")(.*)(?="\))')
        echo $RD_URL
    else
        echo "error!"
    fi

}



# MAIN FUNC
(
    FUNC_INIT
    echo $DEVICE_OS

    FUNC_GET_GW

)