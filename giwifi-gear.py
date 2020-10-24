import requests
import re
import json
import time
from urllib.parse import urlparse, parse_qs

def logcat(msg, level='I'):
    print('%s %s: %s' % (time.ctime().split(' ')[-2], level, msg))

def init_gateway():
    try:
        req = requests.get('http://gwifi.com.cn/', timeout=5).text
        return req
    except requests.exceptions.ConnectionError:
        logcat('Connection failed, please check if you are connected to giwifi.', "E")
        return
    except requests.exceptions.Timeout:
        logcat('Connection timeout, may be out of the Internet range.', "E")
        return

def get_gateway(req):
    """get the gateway when connected but not authenticated"""
    try:
        delayurl = re.search(r'delayURL\("(.*)"\);', req).group(1)

        gateway = re.search(r'(\w+):\/\/([^/:]+):(\d*)?([^# ]*)', delayurl)

        gtw = {'protocol': gateway.group(1), 'host': gateway.group(2), 'port': gateway.group(
            3), 'path': gateway.group(4),'url': delayurl}

        return gtw
    except:
        logcat("get the gateway info error", "E")

def log_out(gtw):
    """log out the giwifi"""
    try:
        req = requests.get('http://%s/getApp.htm?action=logout' % gtw['host'] )
        logcat("The device has already logout")
    except:
        logcat("The device logout error, please check the connection.", "E")



if __name__ == '__main__':
    logcat("Trying to get giwifi gateway...")
    gtw = get_gateway(init_gateway())
    logcat("-> " + gtw['url'])

