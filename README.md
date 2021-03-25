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


## WEB


## APP

### Phone

### PC
