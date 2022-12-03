# giwifi-gear (sh ver)

A UNIX shell command line interpreter tool for login giwifi

## env

Any OS with `UNIX command interpreter` (GNU bash, zsh, other shells)

## dep

- [curl](https://curl.se/)

- [openssl](https://www.openssl.org/) (optional, for auth with app type)

- [gawk](https://www.gnu.org/software/gawk/) (optional, for windows and mac user)

## usage

```
$ ./giwifi-gear.sh -h
giwifi-gear.sh
  A cli tool for login giwifi by cloud auth mode (multi-platform, fast, small)
usage:
  giwifi-gear.sh [-h] [-g <GATEWAY>] [-u <USERNAME>] [-p <PASSWORD>] [-t <TYPE>] [-T <TOKEN>] [-i <IFACE>] [-e <EXTRA_IFACE>] [-I] [-B] [-R] [-q] [-b] [-d] [-l] [-v]
optional arguments:
  -h                    show this help message and exit
  -g <GATEWAY>          set the gateway
  -u <USERNAME>         set the username
  -p <PASSWORD>         set the password
  -i <IFACE>            set the interface by name or ip
  -e <EXTRA_IFACE>      set the extra interface (-e vwan1 -e vwan2)
  -t <TYPE>             auth type (pc/pad/staff for web auth, android/ios/windows/mac/apad/ipad for app auth, token for directly auth by token, and default value is pc)
  -T <TOKEN>            set the token (need to use -t token)
  -b                    bind or rebind your device
  -q                    sign out of account authentication
  -I                    show more user info and host group info
  -B                    get the rebind mac counts
  -R					get the online records (raw data, within 6 months?)
  -d                    running in the daemon mode (remove sharing restrictions)
  -l                    print the log info
  -v                    show the tool version and exit
(c) 2020-2022 icepie.dev@gmail.com
```

## example

bind your device with the default pc type

```bash
$ giwifi-gear.sh -g 172.21.1.1 -u 13000000001 -p mypassword -b # -t pc
```

auth with mac auth type and daemon mode

```bash
$ giwifi-gear.sh -g 172.21.1.1 -u 13000000001 -p mypassword -t mac -d
```

auth with ipad auth type and specified interface

```bash
$ giwifi-gear.sh -u 13000000001 -p mypassword -t ipad -i wlan0 # will automatically detect the gateway
```

quit auth

```bash
$ giwifi-gear.sh -g 172.21.1.1 -q
```

and more...
