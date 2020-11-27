#!/usr/bin/env sh

# config
GW_GTW=172.21.1.2
GW_USER=
GW_PWD=

PC_UA="Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36"


# url
url_encode() {
    # urlencode <string>

    local length="${#1}"
    for i in $(seq $length); do
        local c="${1:$i-1:1}"
        case $c in
            [a-zA-Z0-9.~_-]) printf '%s' "$c" ;;
            *) printf '%%%02X' "'$c" ;;
        esac
    done

}


url_decode() {
    # urldecode <string>

    local url_encoded="${1//+/ }"
    printf '%b' "${url_encoded//%/\\x}"

}

parse_url() {
    local query1 query2 path1 path2

    # extract the protocol
    proto="$(echo $1 | grep :// | sed -e's,^\(.*://\).*,\1,g')"

    if [[ ! -z $proto ]] ; then
            # remove the protocol
            url="$(echo ${1/$proto/})"

            # extract the user (if any)
            login="$(echo $url | grep @ | cut -d@ -f1)"

            # extract the host
            host="$(echo ${url/$login@/} | cut -d/ -f1)"

            # by request - try to extract the port
            port="$(echo $host | sed -e 's,^.*:,:,g' -e 's,.*:\([0-9]*\).*,\1,g' -e 's,[^0-9],,g')"

            # extract the uri (if any)
            resource="/$(echo $url | grep / | cut -d/ -f2-)"
    else
            url=""
            login=""
            host=""
            port=""
            resource=$1
    fi

    # extract the path (if any)
    path1="$(echo $resource | grep ? | cut -d? -f1 )"
    path2="$(echo $resource | grep \# | cut -d# -f1 )"
    path=$path1
    if [[ -z $path ]] ; then path=$path2 ; fi
    if [[ -z $path ]] ; then path=$resource ; fi

    # extract the query (if any)
    query1="$(echo $resource | grep ? | cut -d? -f2-)"
    query2="$(echo $query1 | grep \# | cut -d\# -f1 )"
    query=$query2
    if [[ -z $query ]] ; then query=$query1 ; fi

    # extract the fragment (if any)
    fragment="$(echo $resource | grep \# | cut -d\# -f2 )"

    # echo "url: $url"
    # echo "   proto: $proto"
    # echo "   login: $login"
    # echo "    host: $host"
    # echo "    port: $port"
    # echo "resource: $resource"
    # echo "    path: $path"
    # echo "   query: $query"
    # echo "fragment: $fragment"
    echo $query
}

# str
str_str() {
    #str_str <string> "str" "str"

    local str
    str="${1#*${2}}"
    str="${str%%$3*}"
    echo -n "$str"
}

# giwifi api
gw_get_auth_url()
{
    GTW=$1
    echo $(curl -s -I -A "$PC_UA" "http://$1:8062/redirect?oriUrl=http://www.baidu.com" | grep "Location" | awk -F ": " '{print $2}')
}

gw_get_login_page()
{
    GTW=$1
    echo $(curl -s -L -A "$PC_UA" "http://$1:8062/redirect?oriUrl=http://www.baidu.com" | grep "name=")
}

# main
(
    echo $GW_GTW
    echo $GW_USER

    GW_ATUH_URL=$(gw_get_auth_url $GW_GTW)
    echo GW_ATUH_URL:
    echo '-->' $GW_ATUH_URL
    echo ''
    QUERY_STRING=$(parse_url $GW_ATUH_URL)
    echo QUERY_STRING:
    echo '-->' $QUERY_STRING
    
    echo ''
    echo $(str_str "$QUERY_STRING" "gw_port=" "&" )

    GW_LOGIN_PAGE=$(gw_get_login_page $GW_GTW)

    GW_SIGN=$(url_encode $(str_str "$GW_LOGIN_PAGE" 'name="sign" value="' '"' ))
    GW_PAGE_TIME=$(str_str "$GW_LOGIN_PAGE" 'name="page_time" value="' '"' )

    echo $GW_SIGN
    echo $GW_PAGE_TIME

)
