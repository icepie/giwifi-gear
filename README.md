# giwifi-gear (sh ver)

A unix shell cli tool for login giwifi

## env

Any OS with `unix builtin command` (bash or busybox, etc)

## dep

- curl
- openssl (optional)

# usage

```
$ ./giwifi-gear.sh -h
giwifi-gear.sh
  A cli tool for login giwifi by cloud auth mode (multi-platform, fast, small)
usage:
  giwifi-gear-cloud.sh [-h] [-g <GATEWAY>] [-u <USERNAME>] [-p <PASSWORD>] [-t <TYPE>] [-T <TOKEN>] [-i <IFACE>] [-e <EXTRA_IFACE>] [-q] [-b] [-d] [-l] [-v]
optional arguments:
  -h                    show this help message and exit
  -g <GATEWAY>          set the gateway
  -u <USERNAME>         set the username
  -p <PASSWORD>         set the password
  -i <IFACE>            set the interface by name or ip
  -e <EXTRA_IFACE>      set the extra interface (-e vwan1 -e vwan2)
  -t <TYPE>             auth type(pc/pad/staff for web auth, android/ios/windows/mac/apad/ipad for app auth, token for directly auth by token  (default value is pc)
  -T <TOKEN>            set the token(need to use -t token)
  -b                    bind or rebind your device
  -q                    sign out of account authentication
  -d                    running in the daemon mode (remove sharing restrictions)
  -l                    print the log info
  -v                    show the tool version and exit
example:
  # bind your device with pad type
  ./giwifi-gear.sh -g 172.21.1.1 -u 13000000001 -p mypassword -t pad -b
  # auth with daemon mode
  ./giwifi-gear.sh -g 172.21.1.1 -u 13000000001 -p mypassword -d
  # quit auth
  ./giwifi-gear.sh -g 172.21.1.1 -q
(c) 2021 icepie.dev@gmail.com
```
