#!/usr/bin/env python3
# _*_ coding:utf-8 _*_

import requests
import re
import json
from urllib.parse import urlparse, parse_qs

class giwifi(object):
    """docstring for giwifi"""
    def __init__(self,phone=None,password=None):
        self.phone = phone
        self.password = password

    def check_auth(self,session):
        req = requests.get('http://gwifi.com.cn/', timeout=5).text
        delayurl = re.search(r'delayURL\("(.*)"\);',req).group(1)
        #print(req)
        print(delayurl)
        pass

    def login_out(self,session):
        req = requests.get('http://172.21.1.1/getApp.htm?action=logout')
        print(req.text)

if __name__ == '__main__':
    G = giwifi()
    G.check_auth(requests.Session())
    #G.login_out(requests.Session())