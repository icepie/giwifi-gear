#!/usr/bin/env python3
# _*_ coding:utf-8 _*_

import requests
import re
import json
import time
from urllib.parse import urlparse, parse_qs

class giwifi(object):
    """docstring for giwifi"""
    def __init__(self,phone=None,password=None):
        self.phone = phone
        self.password = password

    def get_gateway(self):
        """get the gateway when connected but not authenticated"""
        logcat("Get giwifi gateway...")
        req = requests.get('http://gwifi.com.cn/', timeout=5).text
        try:
            delayurl = re.search(r'delayURL\("(.*)"\);',req).group(1)
            #delayurl = "http://172.21.1.1:8062/redirect?oriUrl=http://www.baidu.com"

            gateway = re.search(r'(\w+):\/\/([^/:]+):(\d*)?([^# ]*)', delayurl)

            #gateway_protocol = gateway.group(1)
            gateway_host = gateway.group(2)
            gateway_port = gateway.group(3)
            #gateway_path = gateway.group(4)

            gateway_dict = {'host': gateway_host, "port": gateway_port}

            return gateway_dict
            
        except:
            logcat("Logout error","E")

    def log_out(self):
        logcat("Loging out...")
        try:
            req = requests.get('http://172.21.1.1/getApp.htm?action=logout')
            logcat("The device has already logout")
        except:
            logcat("The device logout error, please check the connection.","E")

def logcat(msg, level='I'):
    print('%s %s: %s' % (time.ctime().split(' ')[-2], level, msg))

if __name__ == '__main__':
    G = giwifi()
    G.get_gateway()
    #G.log_out(requests.Session())