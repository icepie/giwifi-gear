# giwifi-gear (sh ver)

# env
Any OS with `unix shell` (bash or busybox, etc)

# dep
- curl

# Usage
```
$ ./giwifi-gear.sh
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
  -b, --bind          bind or rebind your devices
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

(c) 2020 icepie.dev@gmail.com
```