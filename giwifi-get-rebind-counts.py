#!/usr/bin/env python
# -*- coding:utf-8 -*-

import requests
from requests.sessions import default_headers

def getRebindCounts(account:str,password:str):

    XML = {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko',
    }

    data = {
        "account": account,
        "password": password
    }

    resp = requests.post(
        'http://login.gwifi.com.cn/cmps/admin.php/Help/getRebindCounts', data=data, headers=XML, timeout=5)


    resp.encoding = "utf-8"

    return resp.text.encode("utf-8").decode("unicode_escape")

print(getRebindCounts("18400000021","100860"))