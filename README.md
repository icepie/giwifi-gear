# giwifi-gear（py branch)
A cli tool for login giwifi 

# ENV
Python3 and Any OS

# Dep
requests
> python3 -m pip install -r requirements.txt

# Usage
``` bash

usage: giwifi-gear.py [-h] [-g GATEWAY] [-u USERNAME] [-p PASSWORD] [-t TYPE] [-r] [-q] [-d] [-v] [-V]

GiWiFi GEAR TOOL

optional arguments:
  -h, --help            show this help message and exit
  -g GATEWAY, --gateway GATEWAY
                        网关IP
  -u USERNAME, --username USERNAME
                        用户名
  -p PASSWORD, --password PASSWORD
                        密码
  -t TYPE, --type TYPE  设备类型(pc/pad/phone)
  -r, --rebind          登出
  -q, --quit            登出
  -d, --daemon          在后台守护运行
  -v, --verbose         额外输出一些技术性信息
  -V, --version         show program's version number and exit

(c) 2020 icepie.dev@gmail.com
``` 