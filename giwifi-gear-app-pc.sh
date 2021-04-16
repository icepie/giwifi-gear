#!/usr/bin/env bash
# giwifi-gear-pc bash cli tool 
# by icepie

#############################################
## url handle
#############################################

function url_encode() {
	# urlencode <string>

	local length="${#1}"
	for i in $(seq $length); do
		local c="${1:$((i-1)):1}"
		case $c in
		[a-zA-Z0-9.~_-]) printf '%s' "$c" ;;
		*) printf '%%%02X' "'$c" ;;
		esac
	done

}

function url_decode() {
	# urldecode <string>

	local url_encoded="${1//+/ }"
	printf '%b' "${url_encoded//%/\\x}"

}

#############################################
## string handle
#############################################

function str_str() {
    #str_str <string> <string> <string>

    local str
    str="${1#*${2}}"
    str="${str%%$3*}"
    printf "$str"

}

function is_str_c_str() {
    #is_str_c_str <string> <string>

    [ "${1#*$2*}" = "$1" ] && return 1
    return 0

}

function str2hex() {
    #is_str_c_str <string>

    local length="${#1}"
    for i in $(seq $length); do
        printf '%x' "'${1:$i-1:1}"
    done

}

#############################################
## giwifi api
#############################################

function get_challge() {
    #get_challge <password> <token>

    local make_pass="$(printf $1 | base64)"
    local token_challge="$2"
    local length="${#make_pass}"
    for i in $(seq $length); do
        printf "${make_pass:$i-1:1}${token_challge:32-$i:1}"
    done

}

function crypto_encode() {
    #crypto_encode <plain> <key> <iv>
    
    # aes-128-cbc PKCS5Padding (Original data is ZeroPadding, but it is universal on decryption)
    # default key: 1234567887654321
    printf '%s' "$1" | openssl enc -e -aes-128-cbc -K $(str2hex "$2") -iv $(str2hex "$3") -nosalt | base64

}

function get_encrypt() {
    #get_encrypt <plain> <key>
    
    # aes-128-ecb PKCS5Padding
    # default key: 5447c08b53e8dac4
    # don't need iv
    printf '%s' "$1" | openssl enc -e -aes-128-ecb -K $(str2hex "$2") -nosalt | base64

}

# for pc app
test=$(get_challge yiyi6666 35f6aa491f6c695c0d77cdce56b94882)
echo $test
if [ "$test" = "e2W8l854a9TbY625NejcYd=c" ] ; then
    echo 'nice!'
fi

# for web of special and guest
crypto_encode 'nasName=WFXY_NE40E-X8_GateWay_01&nasIp=&userIp=100.65.3.95&userMac=&ssid=&apMac=&pid=20&vlan=&sign=LFiSeFHUow0deKzCi5JxC4Ac2gVY1FVrwTtiKCOhQo1UfReGDe3pBFPrw1VFn73h3Z022q3BnOOH1y38R2buDQ%253D%253D&iv=dca6402ddedb2f56&name=20110005&password=123456' 1234567887654321 dca6402ddedb2f56

# for phone app
get_encrypt '184*****6072' '5447c08b53e8dac4'



