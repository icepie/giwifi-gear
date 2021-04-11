#!/bin/bash
# giwifi-gear-pc bash cli tool 
# by icepie

function get_challge () {
    local make_pass=$(echo -n $1 | base64)
    local token_challge=$2

    local i
    local res

    for i in `seq ${#make_pass}`
    do
        res+=${make_pass:$i-1:1}
        res+=${token_challge:32-$i:1}
    done
    
    echo -n $res
}

test=$(get_challge yiyi6666 35f6aa491f6c695c0d77cdce56b94882)
if [[ "$test" = "e2W8l854a9TbY625NejcYd=c" ]] ; then
    echo 'nice!'
fi