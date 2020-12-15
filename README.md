# giwifi-gear (py ver)
A cli tool for login giwifi 

## env
Any OS with `python3`

## dep
- [requests](https://github.com/psf/requests)

```
python3 -m pip install -r requirements.txt
```

## usage
``` 
$ python3 giwifi-gear.py

usage: giwifi-gear.py [-h] [-g GATEWAY] [-u USERNAME] [-p PASSWORD] [-t TYPE] [-b] [-q] [-d] [-i] [-v]

giwifi-gear

optional arguments:
  -h, --help            show this help message and exit
  -g GATEWAY, --gateway GATEWAY
                        网关IP
  -u USERNAME, --username USERNAME
                        用户名
  -p PASSWORD, --password PASSWORD
                        密码
  -t TYPE, --type TYPE  设备类型(pc/pad/phone)
  -b, --bind            换绑/绑定
  -q, --quit            登出
  -d, --daemon          在后台守护运行(去除共享限制)
  -i, --info            额外输出一些技术性信息
  -v, --version         show program's version number and exit

(c) 2020 icepie.dev@gmail.com
``` 

## example: 
bind your device with pad type

```
python3 giwifi-gear.py -g 172.21.1.1 -u 13000000001 -p mypassword -t pad -b
```

auth with daemon mode

```
python3 giwifi-gear.py -g 172.21.1.1 -u 13000000001 -p mypassword -d
```

quit auth

```
python3 giwifi-gear.py -g 172.21.1.1 -q
```

