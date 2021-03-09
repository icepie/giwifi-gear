# giwifi-mix-crypto

A cli tool to implement a special giwifi version of the encryption algorithm

## Usage

```bash
Usage of ./giwifi-mix-crypto:
  -h    print this help menu
  -i string
        iv (initialization vector)
  -k string
        the key (default "1234567887654321")
  -t string
        the text to be encrypted
```

## Example

```bash
$ ./giwifi-mix-crypto -t 123456 -i 113c200ac7e02851
ZeaJN5aOTuDSydwM5xBI2w==
```
