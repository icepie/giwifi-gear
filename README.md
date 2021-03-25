# giwifi-gear-raw

致: `GiWiFi`迫害下的难民们

这里会列举一些分析过程中收集到的 `api`

即使不维护这个项目了, 也希望能帮到以后有需要的人

## 通用

### 常用变量定义

```js
gw_gateway // 为网关给你分配的 ip
gw_port  // 通常为8060
```

### get_auth_state

> 用于查看认证信息

```http
GET http://$gw_gateway:$gw_port/wifidog/get_auth_state
```

#### 返回

> 重要: 其中 `auth_state` 为  `2` 时为已认证
> 
> 其他都是用于构造登陆换绑定请求所需参数

```json
{"resultCode":0,"data":"{\"auth_state\":1,\"gw_id\":\"GWIFI-luoyangligong2\",\"access_type\":\"1\",\"authStaType\":\"0\",\"station_sn\":\"c400ada4a472\",\"client_ip\":\"172.21.33.6\",\"client_mac\":\"AC:FD:CE:07:3B:EA\",\"online_time\":3534,\"logout_reason\":8,\"contact_phone\":\"400-038-5858\",\"suggest_phone\":\"400-038-5858\",\"station_cloud\":\"login.gwifi.com.cn\",\"orgId\":\"930\",\"timestamp\":\"1616693920\",\"sign\":\"05E3B5FB8CF9644FC89CB49E7D9DA41D\"}"}%
```

### getApp.htm

> 用于查看认证信息以及获取客户端下载地址
> 
> `action` 参数貌似只有 `getAuthState`
>
> `os` 参数可以为 `mac` `android` `windows` `ios` 等... 也可为空

```http
GET http://$gw_gateway/getApp.htm?action=getAuthState&os=mac
```

#### 返回

```json
{"resultCode":0,"data":"{\"auth_state\":1,\"gw_id\":\"GWIFI-luoyangligong2\",\"access_type\":\"1\",\"authStaType\":\"0\",\"station_sn\":\"c400ada4a472\",\"client_ip\":\"172.21.33.6\",\"client_mac\":\"AC:FD:CE:07:3B:EA\",\"online_time\":3534,\"logout_reason\":8,\"contact_phone\":\"400-038-5858\",\"suggest_phone\":\"400-038-5858\",\"station_cloud\":\"login.gwifi.com.cn\",\"orgId\":\"930\",\"timestamp\":\"1616693920\",\"sign\":\"05E3B5FB8CF9644FC89CB49E7D9DA41D\"}"}
```

### auth

> 用于认证的最后一步
> 
> `token` 参数为必要，据测试各种api最后获取的token都使用这个api进行最后认证
>
> `info` 参数可不填，app认证会生成

```http
GET http://$gw_gateway:$gw_port/wifidog/auth?token=1ca889b9f4e04ea3fb0c0d3800be36c6740e7434&info=MiTfgi0wMgzc5MjYwNzIsLDIwMjEwMzI2MDM1NTUy
```
#### 返回

返回自动跳转的html页面

## WEB


## APP



### Phone

#### queryLatest.bin

> 用于检查客户端更新和特殊解决方案
>
> osType 从1～4分别为 android ios windows mac

```http
POST http://mobileapi.gwifi.com.cn:8090/cdnserver/appVersion/

Content-Type text/html;charset=UTF-8

BODY
{
	"data": "{\n  \"sn\" : \"c400adad400e\",\n  \"osType\" : 2\n}",
	"gatewayId": "GWIFI-luoyangligong6",
	"mac": "d4:61:9d:03:d4:68",
	"token": "",
	"version": "1.1.6.2"
}

```

##### 返回

```json
{
	"data": "{\"localDownloadUrl\":\"http://down.gwifi.com.cn/gbapp/20210108/GiWiFi-1.1.6.2.dmg\",\"versionFileSize\":3325963,\"versionContent\":\"【1】修复绑定mac 问题\\r\\n【2】解决部分已知BUG\",\"versionDeployTime\":null,\"md5\":\"7c045479a5ce3b7780bf7276f59f9937\",\"versionCode\":401010602,\"downloadUrl\":\"http://down2hs.gwifi.com.cn/gbapp/20210108/GiWiFi-1.1.6.2.dmg\",\"versionName\":\"1.1.6.2\",\"isMust\":true}",
	"resultCode": 0
}
```

### PC

> 几种平台的ua示例

```http
User-Agent	GiWiFi/1.1.6.2 (Mac OS X Version 11.2.3 (Build 20D91))
```

```http
User-Agent	Asynchronous WinHTTP/1.0 GiWiFiAssist/1.1.4.2
```

##### 

