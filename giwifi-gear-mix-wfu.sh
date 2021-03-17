#!/bin/bash
# giwifi-gear-mix bash cli tool for wfu
# by icepie

#############################################
## config
#############################################
# tool info
VERSION=0.1
GW_URL="210.44.64.60"


# auth setting
PC_UA="Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36"
PAD_UA="Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25"
HEART_BEAT=9

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


logcat() {
        #%Y-%m-%d
        local time=$(date "+%H:%M:%S")

        # check the log flag
        if [ "$2" ]; then
                local flag=$2
        else
                local flag='I'
        fi

        if [ "$1" ]; then
                echo "$time" "$flag": "$1"
        fi
}




#############################################
## giwifi api for wfu
#############################################


gw_get_login_page() {
        echo $(curl -s -L -A "$PAD_UA" "http://$1/gportal/web/login" | grep "name=")
}



GW_LOGIN_PAGE=$(gw_get_login_page $GW_URL)

GW_NAS_NAME=$(str_str "$GW_LOGIN_PAGE" 'name="nasName" value="' '"')
GW_NAS_IP=$(str_str "$GW_LOGIN_PAGE" 'name="nasIp" value="' '"')
GW_USER_IP=$(str_str "$GW_LOGIN_PAGE" 'name="userIp" value="' '"')
GW_USER_MAC=$(str_str "$GW_LOGIN_PAGE" 'id="userMac" value="' '"')
GW_SSID=$(str_str "$GW_LOGIN_PAGE" 'name="ssid" value="' '"')
GW_AP_MAC=$(str_str "$GW_LOGIN_PAGE" 'name="apMac" value="' '"')
GW_PID=$(str_str "$GW_LOGIN_PAGE" 'name="pid" value="' '"')
GW_SIGN=$(str_str "$GW_LOGIN_PAGE" 'name="sign" value="' '"')
GW_IV=$(str_str "$GW_LOGIN_PAGE" 'id="iv" value="' '"')


echo $GW_NAS_NAME
echo $GW_NAS_IP
echo $GW_USER_IP
echo $GW_USER_MAC
echo $GW_SSID
echo $GW_AP_MAC
echo $GW_PID
echo $GW_SIGN
echo $GW_IV