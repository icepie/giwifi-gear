function json_format(){
	local json=$1
	# Del escape character and format text
	echo $(echo -e $1) | sed "s@\\\\@@g"
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


NET_GTW=$(ip n  | grep -v "br-lan" | grep -v "route" | awk '{print $1}')
echo $NET_GTW


FUNC_TEST_2()
{

    if [ $# -gt 1 ];then
        local ctmp=1

        echo "$#个网关可用"

        for i in $*
        do
            echo "- $ctmp: $i"
            ((ctmp+=1))
        done

        while [ 1 ];
        do
            read s_num
            if [[ $s_num  -ge 1 && $s_num -le $# ]]; then
                echo "using ${!s_num} as gateway"
                break
            else
                echo "input error!"
            fi
        done
    fi
    
}

FUN_TEST()
{
	for i in $*
	do
		AUTH_TMP=$(GET_AUTH $i)
		if [[ $AUTH_TMP == *resultCode* ]]
		then
			NET_GTW=$i
			NET_CARD=$(ip n  | grep "$i" | awk '{print $3}')
			GW_TEST+=$i" "
		fi
	done

    echo $GW_TEST
    FUNC_TEST_2 $GW_TEST
}


FUN_TEST $NET_GTW
