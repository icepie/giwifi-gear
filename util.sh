#!/usr/bin/env bash


check_ip(){
    printf '%s' $1 | grep "^[0-9]\{1,3\}\.\([0-9]\{1,3\}\.\)\{2\}[0-9]\{1,3\}$" > /dev/null;
    if [ $? -ne 0 ]
    then
        #echo "IP地址必须全部为数字"
        return 1
    fi
    local ipaddr=$1
    local a=`echo $ipaddr|awk -F . '{print $1}'` #以"."分隔，取出每个列的值
    local b=`echo $ipaddr|awk -F . '{print $2}'`
    local c=`echo $ipaddr|awk -F . '{print $3}'`
    local d=`echo $ipaddr|awk -F . '{print $4}'`
    for num in $a $b $c $d
    do
    if [ $num -gt 255 ] || [ $num -lt 0 ] #每个数值必须在0-255之间
    then
        #echo $ipaddr "中，字段"$num"错误"
        return 1
    fi
    done
        #echo $ipaddr "地址合法"
    return 0
}


get_os_type(){

    local uname=$(uname 2> /dev/null)
    local os

    # Mac OS X
    if [ "$(uname)" = "Darwin" ]; then
        os="darwin"
    # GNU/Linux && 
    elif [ "$(uname)" = "Linux" ]; then
        os="linux"
        # Android
        if [ "$(getprop 2> /dev/null)" ];then
            os="android"
        fi
    # Windows
    elif [ "$(chcp.com 2> /dev/null)" ];then
        os="windows"
    else
        os=$(uname)
    fi
    printf '%s' "$os"

}

get_nic_gateway() {

    # get_nic_gateway <nic_name>

    local os="$(get_os_type)"
    local gateway

    if [ "$os" == 'windows' ];then
        gateway="$(chcp.com 437 > /dev/null && netsh interface ip show address "$1" 2> /dev/null | grep 'Default Gateway' | awk '{print $3}')"
    elif [ "$os" == 'linux' ] || [ "$os" == 'android' ] ;then
        gateway="$(ip route list match 0 table all scope global 2> /dev/null | grep 'proto' | grep "$1" | awk '{print $3}')"
    elif [ "$os" == 'darwin' ];then
        gateway="$(netstat -rn 2> /dev/null | grep 'default' | grep "$1" | awk '{print $2}')"
    fi

    if $(check_ip "$gateway");then
        printf '%s' "$gateway"
    fi

}

get_nic_ip() {

    # get_nic_ip <nic_name>

    local os="$(get_os_type)"
    local ip

    if [ "$os" == 'windows' ];then
        ip="$(chcp.com 437 > /dev/null && netsh interface ipv4 show ipaddress interface="$1" 2> /dev/null | head -2 | awk '{print $2}')"
    elif [ "$os" == 'linux' ] || [ "$os" == 'android' ] ;then
        ip="$(ip address show dev "$1" 2> /dev/null | grep 'inet ' | awk '{print $2}' | awk -F '/' '{print $1}')"
    elif [ "$os" == 'darwin' ];then
        ip="$(ifconfig "$1" 2> /dev/null | grep "inet " | grep -v 127.0.0.1 | cut -d\  -f2 | awk '{print $1}')"
    fi

    if $(check_ip "$ip");then
        printf '%s' "$ip"
    fi

}


OS_TYPE="$(get_os_type)"

echo "$OS_TYPE"

get_nic_ip '以太网'
echo
get_nic_gateway 'wlan0'
echo
get_nic_ip 'wlan0'
