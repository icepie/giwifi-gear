#!/bin/bash
# giwifi-gear bash cli tool
# by icepie

# if [ "uname" == 'Darwin' ]
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

function json_format(){
	local json=$1
	# Del escape character and format text
	echo $(echo -e $1) | sed "s@\\\\@@g"
}

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
	#### linux
	NET_CARD=$(ip n | grep -v "br-lan" | grep -v "route" | awk '{print $3}') 
	#lladdr
	NET_GTW=$(ip n  | grep -v "br-lan" | grep -v "route" | awk '{print $1}')

	#### mac
	#route get default | grep 'interface' | awk '{print $2}'
	#route get default | grep 'gateway' | awk '{print $2}'
}


function OUTPUT_SYS_INFO()
{
	echo "OS: $DEVICE_OS"
	echo "MODE:" $RUNMODE
	echo "NETCARD:" $NET_CARD
	echo "GATEWAY:" $NET_GTW
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


function NIC_GTW_TEST()
{
	GTW_NUM=$#
	if [ $GTW_NUM -gt 1 ];then
		#echo $*
		for i in $*
		do
			AUTH_TMP=$(GET_AUTH $i)
			if [[ $AUTH_TMP == *resultCode* ]]
			then
				NET_GTW=$i
				NET_CARD=$(ip n  | grep "$i" | awk '{print $3}')
				break
			fi
		done
	else
		echo 'test2'
	fi
}

# MAIN FUNCTION
(

	CHECK_DEP

	# must need curl or wget...
	if [ ! -n $RUNMODE ];then
		echo 'dependency insatiability...'
		exit
	fi

	GET_NET_INFO

	# if not nic detected
	if [ ! -n "$NET_CARD" ];then
		echo 'no NIC(s) detected...'
		echo 'Please connect to GiWiFi!'
		exit
	fi

	# if NIC(s) detected...
	NIC_GTW_TEST $NET_GTW

	echo '--------------------------' 
	OUTPUT_SYS_INFO

	echo '--------------------------'

	echo $(GET_AUTH $NET_GTW)

)2>&2 | tee -a ./build.log
