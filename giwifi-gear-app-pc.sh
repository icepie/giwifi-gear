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

#############################################
## giwifi api
#############################################

function get_challge () {
    #get_challge <string> <string>

    local make_pass="$(printf $1 | base64)"
    local token_challge="$2"
    local length="${#make_pass}"
    for i in $(seq $length); do
        printf "${make_pass:$i-1:1}${token_challge:32-$i:1}"
    done

}

test=$(get_challge yiyi6666 35f6aa491f6c695c0d77cdce56b94882)
echo $test
if [ "$test" = "e2W8l854a9TbY625NejcYd=c" ] ; then
    echo 'nice!'
fi

# source_str=123456

# key=$(openssl rand -base64 32 | md5)
# iv=$(openssl rand -base64 32 | md5)
 
# encrypt_str=$(printf "${source_str}" | openssl enc -e -aes-128-cbc -a -K 1234567887654321 -iv 113c200ac7e02851 -nosalt)
# echo ${encrypt_str}
 
# # decrypt_str=$(echo "${encrypt_str}" | openssl enc -e -aes-256-cbc -a -K ${key} -iv ${iv} -nosalt -d)
# # echo ${decrypt_str}


