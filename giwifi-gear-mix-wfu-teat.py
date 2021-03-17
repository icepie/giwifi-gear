# This is a sample Python script.

# Press Shift+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
from typing import Dict, Any, Union

import requests
# from Crypto.Cipher import AES
from urllib.parse import quote_from_bytes
import re
import time
import base64
import random
import json
import os
import subprocess

HEADERS = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko',
    'accept-encoding': 'gzip, deflate, br',
    'accept-language': 'zh-CN,zh-TW;q=0.8,zh;q=0.6,en;q=0.4,ja;q=0.2',
    'cache-control': 'max-age=0'
}

XML = {
    'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
    'Accept': 'application/json, text/javascript, */*; q=0.01',
    'X-Requested-With': 'XMLHttpRequest',
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko',
    'accept-encoding': 'gzip, deflate, br',
    'accept-language': 'zh-CN,zh-TW;q=0.8,zh;q=0.6,en;q=0.4,ja;q=0.2',
    'cache-control': 'max-age=0'
}

name = '20110005'
password = '123456'
url = '210.44.64.60'

def main():
    try:
        loginPage = requests.get('http://' + url + '/gportal/web/login', headers=HEADERS, timeout=5).text

        iv = re.search(r'id="iv" value="(.*?)"', loginPage).group(1)
        data = {
            'nasName': re.search(r'name="nasName" value="(.*?)"', loginPage).group(1),
            # 'nasIp': re.search(r'name="nasIP" value="(.*?)"', loginPage).group(1),
            'nasIp': '',
            'userIp': re.search(r'name="userIp" value="(.*?)"', loginPage).group(1),
            # 'userMac': re.search(r'id="userMac" value="(.*?)"', loginPage).group(1),
            # 'ssid': re.search(r'name="ssid" value="(.*?)"', loginPage).group(1),
            # 'apMac': re.search(r'name="apMac" value="(.*?)"', loginPage).group(1),
            'userMac': '',
            'ssid': '',
            'apMac': '',
            'pid': re.search(r'name="pid" value="(.*?)"', loginPage).group(1),
            # 'vlan': re.search(r'name="vlan" value="(.*?)"', loginPage).group(1),
            'vlan': '',
            'sign': re.search(r'name="sign" value="(.*?)"', loginPage).group(1),
            'iv': iv,
            'name': name,
            'password': password
        }

        accessState = '0'#ping()

        if not accessState:
            return

        if accessState == '0':
            data = 'data=' + str(crypto(serialize(**data), iv)) + '&iv=' + str(iv)
            response = login(data)
            if response['status'] == 1 and ping() == '1':
                logcat(response['info'])
            else:
                logcat(response['info'])
        else:
             logcat('网络已连接！')

    except requests.exceptions.ConnectionError:
        logcat('连接失败！')
        return

    except requests.exceptions.Timeout:
        logcat('连接超时！')
        return


def login(data):
    try:
        response = json.loads(
            requests.post('http://' + url + '/gportal/web/authLogin?round=' + str(random.randint(1, 999)),
                          data=data, headers=XML, timeout=5).text)
        return response

    except requests.exceptions.ConnectionError:
        logcat('连接失败！')

    except requests.exceptions.Timeout:
        logcat('连接超时！')


# def add_to_16(text):
    # 如果text不足16位的倍数就用空格补足为16位
    # if len(text.encode('utf-8')) % 16:
    #     add = 16 - (len(text.encode('utf-8')) % 16)
    # else:
    #     add = 0
    # text = text + ('\0' * add)
    # return text.encode('utf-8')


def crypto(data, iv):
    # AES加密 使用CBC模式 输出结果base64编码+url编码
    # key = '1234567887654321'.encode('utf-8')
    # mode = AES.MODE_CBC
    iv = str(iv)
    # cryptos = AES.new(key, mode, iv)
    # print(text)
    # text = add_to_16(text)
    # cipher_text = cryptos.encrypt(text)
    data = '/opt/giwifi-mix-crypto-linux-mipsle -p \''+ str(data) + '\' -i \'' + iv + '\''
    data = adb_shell(str(data))
    return quote_from_bytes(data)


def adb_shell(cmd):
    # 执行cmd命令，如果成功，返回(0, 'xxx')；如果失败，返回(1, 'xxx')
    res = subprocess.Popen(cmd, shell=True, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.PIPE) # 使用管道
    result = res.stdout.read()  # 获取输出结果
    res.wait()  # 等待命令执行完成
    res.stdout.close() # 关闭标准输出
    return result


def serialize(**data):
    # 模拟Jquery表单序列化serialize()
    # 函数传参字典前**，列表前*，其他不用
    string = ""
    lenth = len(data)
    i = 0
    for k, v in data.items():
        i += 1
        if i == lenth:
            string = string + str(k) + '=' + str(v)
        else:
            string = string + str(k) + '=' + str(v) + '&'

    return string


def ping():
    # 测试网络连通性
    # try:
    #     res = json.loads(requests.get('http://210.44.64.60/gportal/web/queryAuthState', headers=HEADERS, timeout=5).text)
    #
    #     if res['status'] == 1:
    #         return '1'
    #     else:
    #         return '0'
    #
    # except requests.exceptions.ConnectionError:
    #     logcat('连接失败')
    #     return
    #
    # except requests.exceptions.Timeout:
    #     logcat('连接超时')
    #     return
    result = os.system(u'ping -w 3 www.baidu.com')
    if result == 0:
        return '1'
    else:
        return '0'


def logcat(msg, level='I'):
    print('%s %s: %s' % (time.ctime().split(' ')[-2], level, msg))


main()
