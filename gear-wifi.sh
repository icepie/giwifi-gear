#!/usr/bin/bash
# giwifi-gear bash cli tool
# by icepie

DEVICE_OS=$(uname -o)


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

#get_json_value '0' resultCode


#############################################
## Special Functions
#############################################

## the func for checking dependency and set the run mode
function CHECK_DEP()
{

	echo 'Checking dependency...'

	if ! [ -x "$(command -v curl)" ]; then
		echo '- curl is not installed.' >&2
	else
		echo '- curl has be installed!'
		RUNMODE='curl'
		return 0
	fi

	if ! [ -x "$(command -v wget)" ]; then
	  echo '- wget is not installed.' >&2
		exit 1
	else
		echo '- wget has be installed!'
		RUNMODE='wget'
		return 0
	fi
}


function GET_NET_INFO()
{
	NET_CARD=$(ip n | grep "REACHABLE" | grep -v "br-lan" | grep -v "route" | awk '{print $3}')
	NET_GTW=$(ip n | grep "REACHABLE" | grep -v "br-lan" | grep -v "route" | awk '{print $1}')
}

function OUTPUT_SYS_INFO()
{
	echo "OS: $DEVICE_OS"
	echo "MODE:" $RUNMODE
	echo "NETCARD:" $NET_CARD
	echo "GATEWAY:" $NET_GTW
}

# MAIN FUNCTION
(
CHECK_DEP

# must need curl or wget...
if [ ! -n $RUNMODE ];then
	echo 'dependency insatiability...' 'E'
	exit
fi

GET_NET_INFO

echo '--------------------------' 
OUTPUT_SYS_INFO

)2>&1	 | tee -a ./build.log