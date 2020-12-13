# giwifi-gear (py ver)
A cli tool for login giwifi 

## env
Any OS with `python3`

## dep
- requests


> python3 -m pip install -r requirements.txt

## usage
``` 
$ python3 giwifi-gear.py

giwifi-gear
  A cli tool for login giwifi (multi-platform, fast, small)

usage:
  giwifi-gear.py [-h] [-g GATEWAY] [-u USERNAME] [-p PASSWORD] [-t TYPE] [-r] [-q] [-d] [-v] [-V]

optional arguments:
  -h, --help            show this help message and exit
  -g GATEWAY, --gateway GATEWAY
  -u USERNAME, --username USERNAME
  -p PASSWORD, --password PASSWORD
  -t TYPE, --type TYPE  auth type(use pc/pad/phone, and the default value is pc)
  -r, --rebind          bind rebind your devices
  -q, --quit            sign out of account authentication 
  -d, --daemon          running in the background guard (remove sharing restrictions)
  -v, --verbose         show some debug info
  -v, --version         show program's version number and exit

  example: 
    # bind your device with pad type
    giwifi-gear.py -g 172.21.1.1 -u 13000000001 -p mypassword -t pad -r

    # auth with daemon mode
    giwifi-gear.py -g 172.21.1.1 -u 13000000001 -p mypassword -d

    # quit auth
    giwifi-gear.py -g 172.21.1.1 -q

(c) 2020 icepie.dev@gmail.com
``` 
