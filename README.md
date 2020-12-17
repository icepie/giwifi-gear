# giwifi-gear (sh ver)
A unix shell cli tool for login giwifi

# env
Any OS with `unix builtin command` (bash or busybox, etc)

# dep
- curl

if you runing in the sh or ash, plase try to use the **giwifi-gear-min.sh**, maybe you need to handly edit the file.

# usage
```
$ ./giwifi-gear.sh
giwifi-gear
  A cli tool for login giwifi (multi-platform, fast, small)

usage:
  giwifi-gear.sh [-h] [-g GATEWAY] [-u USERNAME] [-p PASSWORD] [-t TYPE] [-i] [-q] [-b] [-d] [-v]

optional arguments:
  -h                    show this help message and exit
  -g <GATEWAY>          set the gateway
  -u <USERNAME>         set the username
  -p <PASSWORD>         set the password
  -t <TYPE>             auth type(use pc/pad/phone, and the default value is pc)
  -i                    print the debug info
  -b                    bind or rebind your devices
  -q                    sign out of account authentication
  -d                    running in the daemon mode (remove sharing restrictions)
  -v                    show the tool version info and exit

example:
  # bind your device with pad type
  ./giwifi-gear.sh -g 172.21.1.1 -u 13000000001 -p mypassword -t pad -b

  # auth with daemon mode
  ./giwifi-gear.sh -g 172.21.1.1 -u 13000000001 -p mypassword -d

  # quit auth
  ./giwifi-gear.sh -g 172.21.1.1 -q

(c) 2020 icepie.dev@gmail.com
```
