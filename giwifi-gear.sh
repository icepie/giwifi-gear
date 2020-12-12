#/bin/sh
VERSION=0.10

ver(){
    echo "\
giwifi-gear version $VERSION\
    "
}

usage(){
  echo "\
giwifi-gear
  A cli tool for login giwifi (multi-platform, fast, small)

usage:
  giwifi-gear.sh [-h] [-g GATEWAY] [-u USERNAME] [-p PASSWORD] [-t TYPE] [-r] [-q] [-d] [-v] [-V]

optional arguments:
  -h, --help            show this help message and exit
  -g GATEWAY, --gateway GATEWAY
  -u USERNAME, --username USERNAME
  -p PASSWORD, --password PASSWORD
  -t TYPE, --type TYPE  auth type(use pc/pad/phone, and the default value is pc)
  -r, --rebind          bind rebind your devices
  -q, --quit            sign out of account authentication 
  -d, --daemon          running in the background guard (remove sharing restrictions)
  -v, --version         show program's version number and exit

  example: 
    # bind your device with pad type
    giwifi-gear.sh -g 172.21.1.1 -u 13000000001 -p mypassword -t pad -r

    # auth with daemon mode
    giwifi-gear.sh -g 172.21.1.1 -u 13000000001 -p mypassword -d

    # quit auth
    giwifi-gear.sh -g 172.21.1.1 -q

(c) 2020 icepie.dev@gmail.com\
"
# 短格式中，选项值为可选的选项，选项值只能紧接选项而不可使用任何符号将其他选项隔开；如-p80，不要写成性-p 80
# 短格式中，选项值为必有的选项，选项值既可紧接选项也可以使用空格与选项隔开；如-i192.168.1.1，也可写成-i 192.168.1.1
# 长格式中，选项值为可选的选项，选项值只能使用=号连接选项；如--port=80，不可写成性--port80或--port 80
# 长格式中，选项值为必有的选项，选项值既可使用=号连接选项也可使用空格连接选项；如--ip=192.168.1.1，也可写成--ip 192.168.1.1
# 为简便起见，建议凡是短格式都使用“选项+选项值”的形式（-p80），凡是长格式都使用“选项+=+选项值”的形式（--port=80）
}

main(){
# check
if [ $# -le 1 ]; then
    usage
    # 打印usage之后直接用exit退出程序
    exit
fi

while true
do
  case "$1" in
  -g|--gateway)
      gateway="$2"
      echo "gateway:    $gateway" 
      shift
      ;;
  -u|--username)
      username="$2"
      echo "username:    $username"
      shift
      ;;
  -p|--password)
      password="$2"
      echo "password:    $password"
      shift
      ;;
  -t|--type)
      type="$2"
      echo "type:    $type"
      shift
      ;;
  -r|--rebind)
      usage
      # 打印usage之后直接用exit退出程序
      exit
      ;;
  -q|--quit)
      usage
      # 打印usage之后直接用exit退出程序
      exit
      ;;
  -d|--daemon)
      usage
      # 打印usage之后直接用exit退出程序
      exit
      ;;
  -v|--version)
      ver
      # 打印ver之后直接用exit退出程序
      exit
      ;;
  -h|--help)
      usage
      # 打印usage之后直接用exit退出程序
      exit
      ;;
  --)
    shift
    break
    ;;
  *)
    echo "$1 is not option" 
    ;;
  esac
  shift
done
# 剩余所有未解析到的参数存在$@中，可通过遍历$@来获取
#for param in "$@"
#do 
#  echo "Parameter #$count: $param"
#done
}

# 如果只注册短格式可以如下这样子
# set -- $(getopt i:p::h "$@")
# 如果要注册长格式需要如下这样子
# -o注册短格式选项
# --long注册长格式选项
# 选项后接一个冒号表示其后为其参数值，选项后接两个冒号表示其后可以有也可以没有选项值，选项后没有冒号表示其后不是其参数值
set -- $(getopt -o g:u:p:t:rdvh --long gateway:,username:,password:,type:,rebind,daemon,version,help -- "$@")
# 由于是在main函数中才实现参数处理，所以需要使用$@将所有参数传到main函数
main $@