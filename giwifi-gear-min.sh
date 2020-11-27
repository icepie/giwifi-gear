#!/usr/bin/env sh

GW_GTW=172.21.1.2
GW_USER=
GW_PWD=

urlencode() {
    # urlencode <string>

    local length="${#1}"
    for i in `seq ${#1}`; do
        local c="${1:$i:1}"
        case $c in
            [a-zA-Z0-9.~_-]) printf '%s' "$c" ;;
            *) printf '%%%02X' "'$c" ;;
        esac
    done

}

urldecode() {
    # urldecode <string>

    local url_encoded="${1//+/ }"
    printf '%b' "${url_encoded//%/\\x}"

}

echo $GW_GTW
echo $GW_USER
