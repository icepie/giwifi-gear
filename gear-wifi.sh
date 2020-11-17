#!/usr/bin/bash
# giwifi-gear bash cli tool
# by icepie


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

#get_json_value '{"SetWifiAp": {"on": 1, "password": "88888888", "hotspot_name": "imx8_ap"}}' hotspot_name



function SYS_INFO()
{
	echo $(uname)
	echo $(uname -a)
}



## the func for checking dependency and set the run mode
function CHECK_DEP()
{

	echo 'Checking dependency...'

	if ! [ -x "$(command -v curl)" ]; then
	  echo 'curl is not installed.' >&2
		exit 1
	else
		echo 'curl has be installed!'
		RUNMODE='curl'
		return 0
	fi

	if ! [ -x "$(command -v wget)" ]; then
	  echo 'wget is not installed.' >&2
		exit 1
	else
		echo 'wget has be installed!'
		RUNMODE='wget'
		return 0
	fi
}


#main
SYS_INFO
CHECK_DEP
echo 'run mode: $RUNMODE'
