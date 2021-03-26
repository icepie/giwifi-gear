
#!/usr/bin/env python
# -*- coding:utf-8 -*-

from sys import version
import requests
import time
import json
import argparse
from urllib.parse import urlparse, parse_qs

CONFIG = {
    'gateway': '172.21.1.5',
    'username': '13000000000',
    'password': '12345678',
    'type': 'mac',  # mac or win
    'version': '1.1.6.2',  # mac: 1.1.6.2 , win: 1.1.4.2
    'model': 'mac11.2', # 随意
    'service_type': '1',  # (1 or 2) 不能用就挨个试试
    'sta_nic_type': '2',  # (1 or 2) 1为无线模式2为有线模式
    'app_uuid': 'bad82d414cb15452938cfe605c7faf02' # 随意
}

SCRIPT_VERSION = "test"

WIN_HEADERS = {
    'User-Agent': 'Asynchronous WinHTTP/1.0 GiWiFiAssist/1.1.4.2',
    'Cookie': 'PHPSESSID=00mhpv7g9vebbvpkhiqauv3ai3'
}

MAC_HEADERS = {
    'User-Agent': 'GiWiFi/1.1.6.2 (Mac OS X Version 11.2.3 (Build 20D91))',
    'Cookie': 'PHPSESSID=00mhpv7g9vebbvpkhiqauv3ai3'
}

PC_HEADERS = MAC_HEADERS

if CONFIG['type'] == 'win':
    PC_HEADERS = WIN_HEADERS


PARSER = argparse.ArgumentParser(formatter_class=argparse.RawDescriptionHelpFormatter,
                                 description='giwifi-gear',
                                 epilog='(c) 2021 icepie.dev@gmail.com')
# PARSER.add_argument('-g', '--gateway', type=str, help='网关IP')
# PARSER.add_argument('-u', '--username', type=str, help='用户名')
# PARSER.add_argument('-p', '--password', type=str, help='密码')
# PARSER.add_argument('-t', '--type', type=str, help='设备类型(win/mac)')
# # PARSER.add_argument('-b', '--bind', action='store_true', help='换绑/绑定')
PARSER.add_argument('-q', '--quit', action='store_true', help='登出')
PARSER.add_argument('-d', '--daemon', action='store_true', help='在后台守护运行(去除共享限制)')
# PARSER.add_argument('-i', '--info', action='store_true', help='额外输出一些技术性信息')
PARSER.add_argument('-v', '--version', action='version',
                    version='giwifi-gear {}'.format(SCRIPT_VERSION))

CLI = PARSER.parse_args()

# if not CONFIG['gateway']:
#     CONFIG['gateway'] = input('请输入网关地址(%s):' %
#                             (CLI.gateway))

# if not CONFIG.quit:
#     if not CONFIG.username:
#         CONFIG.username = input('请输入上网账号:')

#     if not CONFIG.password:
#         CONFIG.password = getpass('请输入账号密码:')



def logcat(msg, level='I'):
    print('%s %s: %s' % (time.ctime().split(' ')[-2], level, msg))


def MakeTempPass(TempPass):
    Res = ""
    Charlist = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456"
    Temp = 0
    for i in range(0, 4):
        Temp = (TempPass >> ((3 - i) * 6)) & 0x3F
        Res += Charlist[Temp]
    return Res


def calPass(Pass):
    Res = ""
    temps = 0
    # print(len(Pass))
    for i in range(1, len(Pass)+1):
        temps = temps ^ ord(Pass[i - 1])
        # print(i,MakeTempPass(temps))
        if i % 3 == 0 or i == len(Pass):
            # print(i,MakeTempPass(temps))
            Res += MakeTempPass(temps)
            # print(Res)
        temps = temps << 8
    return Res


def GetChallge(MakePass, TokenChallge):
    res = ""
    for i in range(0, len(MakePass)):
        res += MakePass[i]
        res += TokenChallge[31 - i]
    a = list(res)
    a[-2] = "="
    res = ''.join(a)

    return res

# 250330d7a095cbb16528a56dfdaf99b2

# print(GetChallge(calPass(CONFIG['password']),"35f6aa491f6c695c0d77cdce56b94882")) #e4W3lb53a4TbYe24N3j8Y5=6ONFIG['password'],"1d8f25e3c05c62c7bf8365834eb43b34"))

# print('true: \n','e2W8l854a9TbY625NejcYd=c')

def logout(authParmas):
    try:
        params = {
            'ip': authParmas['ip'],
            'mac': authParmas['mac'],
        }
        
        resp = json.loads(requests.get(
            'http://%s:%s/wifidog/userlogout' % (authParmas['gw_address'], authParmas['gw_port']), params=params, timeout=5).text)
        
    except requests.exceptions.Timeout:
        logcat('连接超时，可能已超出上网区间', "E")
        return

    if resp['resultCode'] == 0:
        logcat('下线成功')
    else:
        logcat('下线失败')

def getAuthState(authParmas):
    try:
        params = {
            'ip': authParmas['gw_address'],
            'mac': authParmas['mac'],
        }

        resp = json.loads(requests.get('http://%s:%s/wifidog/get_auth_state' % (
            authParmas['gw_address'], authParmas['gw_port']), params=params, timeout=5).text)
        #resp.encoding = "utf-8"
    except KeyError:
        logcat('所需参数不存在', "E")
        return False

    except requests.exceptions.Timeout:
        logcat('连接超时，可能已超出上网区间', "E")
        return False

    if resp['resultCode'] == 0:
        return json.loads(resp['data'])
    else:
        return False


def authIdentity():

    params = {
        'name': CONFIG['username'],
        'version': CONFIG['version']
    }

    resp = requests.get(
        'http://login.gwifi.com.cn/cmps/admin.php/ppi/authIdentity', headers=PC_HEADERS, params=params, timeout=5)

    # print(curlify.to_curl(resp.request, compressed=True))

    #MAC_HEADERS['Cookie'] = "PHPSESSID=" + resp.cookies.get_dict()['PHPSESSID']

    logcat(MAC_HEADERS)

    resp = json.loads(resp.text)

    if resp['resultCode'] == 0:
        return json.loads(resp['data'])
    else:
        return False

# ap_mac=&app_uuid=bad82d414cb15452938cfe605c7faf02&challege=e1Wblb5aa3TbY32cN4jeY4%3D9&gw_address=172.21.1.7&gw_id=GWIFI-luoyangligong6&ip=172.21.120.73&mac=d4%3A61%3A9d%3A03%3Ad4%3A68&name=18437926072&service_type=1&sta_model=mac11.2&sta_nic_type=1&sta_type=pc&version=1.1.6.2


def login(authParmas, authState):
    ai = authIdentity()
    if ai != False:
        print(ai['challege_id'])
        data = {
            'ap_mac': authState['client_mac'],
            'app_uuid': CONFIG['app_uuid'],
            'challege': GetChallge(calPass(CONFIG['password']), ai['challege_id']),
            'gw_address': authParmas['gw_address'],
            'gw_id': authParmas['gw_id'],
            'ip': authParmas['ip'],
            'mac': authParmas['mac'],
            'name': CONFIG['username'],
            'service_type': CONFIG['service_type'],
            'sta_model': CONFIG['model'],
            'sta_nic_type': CONFIG['sta_nic_type'],
            'sta_type': 'pc',
            'version': CONFIG['version'],
        }

        print(data)

        logcat(MAC_HEADERS)
        resp = requests.post(
            'http://login.gwifi.com.cn/cmps/admin.php/ppi/authChallege', data=data, headers=PC_HEADERS, timeout=5)
        # print(curlify.to_curl(resp.request, compressed=True))
        resp.encoding = "utf-8"

        result = json.loads(resp.text)

        print(result)

        authurl = json.loads(result['data'])['redirect_url']

        print(authurl)

        requests.get(authurl)

        if CLI.daemon:
            iota = 0
            while True:
                time.sleep(15)
                requests.get(authurl)
                iota+=1
                logcat('检测心跳 %s' % iota)



    # resp = json.loads(requests.post(
    #     'http://login.gwifi.com.cn/cmps/admin.php/ppi/authIdentity', params=params, timeout=5).text)
    # print(resp)
    #config['challege_id'] = json.loads(resp['data'])['challege_id']


authUrl = requests.get('http://%s:8062/redirect' %
                       (CONFIG['gateway']), headers=MAC_HEADERS, timeout=5).url

authParmas = {k: v[0]
              for k, v in parse_qs(urlparse(authUrl).query).items()}

if CLI.quit:
        logout(authParmas)
        exit()

authState = getAuthState(authParmas)

logcat(authState)

login(authParmas, authState)
