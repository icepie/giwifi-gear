#!/usr/bin/env python3
# _*_ coding:utf-8 _*_

from typing import Coroutine
import requests
import re
import json
import time
from urllib.parse import urlparse, parse_qs

class giwifi(object):
    """docstring for giwifi"""

    def __init__(self, phone=None, password=None):
        self.phone = phone
        self.password = password

    def get_gateway(self, val='host'):
        """get the gateway when connected but not authenticated"""
        try:
            req = requests.get('http://gwifi.com.cn/', timeout=5).text
            delayurl = re.search(r'delayURL\("(.*)"\);', req).group(1)
            #delayurl = "http://172.21.1.1:8062/redirect?oriUrl=http://www.baidu.com"

            gateway = re.search(r'(\w+):\/\/([^/:]+):(\d*)?([^# ]*)', delayurl)

            gateway_dict = {'protocol': gateway.group(1), 'host': gateway.group(2), 'port': gateway.group(
                3), 'path': gateway.group(4), 'core': gateway.group(2) + ":" + gateway.group(3)}

            return gateway_dict[val]

        except requests.exceptions.ConnectionError:
            logcat('Connection failed, please check if you are connected to giwifi.')
            return

        except requests.exceptions.Timeout:
            logcat('Connection timeout, may be out of the Internet range.')
            return

    def log_out(self):
        logcat("Loging out...")
        try:
            req = requests.get('http://172.21.1.1/getApp.htm?action=logout')
            logcat("The device has already logout")
        except:
            logcat("The device logout error, please check the connection.", "E")


def logcat(msg, level='I'):
    print('%s %s: %s' % (time.ctime().split(' ')[-2], level, msg))

if __name__ == '__main__':
    G = giwifi()
    logcat("Trying to get giwifi gateway...")
    try:
        gateway = G.get_gateway('core')
        logcat("-> " + gateway)
    except:
        logcat("get the gateway info error", "E")
    
    # G.log_out(requests.Session())
