package giwifi

import (
	"io/ioutil"
	"net/http"
	"regexp"
	"errors"
)

type Urld struct {
	Protocol string
	Host     string
	Port     string
	Path     string
}

func url_handel(url string) (Urld, error) {
	reg := regexp.MustCompile(`(\w+):\/\/([^/:]+):(\d*)?([^# ]*)`)
	result := reg.FindAllStringSubmatch(url, -1)

	if result == nil {
		return Urld{}, errors.New("解析错误")
	
	}

	var urldeal Urld

	urldeal.Protocol = result[0][1]
	urldeal.Host = result[0][2]
	urldeal.Port = result[0][3]
	urldeal.Path = result[0][4]

	return urldeal, nil
}

func GetGateway() (Urld, error) {

	resp, err := http.Get("http://gwifi.com.cn")
	if err != nil {
		return Urld{}, errors.New("连接失败, 请检查是否连接上GiWiFi")
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		return Urld{}, errors.New("连接超时，可能已超出上网区间")
	}

	/*
	   	body_test = `
	   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	   <html>
	   <head>
	   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	   <title>Loading ...</title>
	   <script type="text/javascript">
	           delayURL("http://172.21.1.2:8062/redirect?oriUrl=http://www.baidu.com");
	           function delayURL(url) {
	                           window.top.location.href = url;
	           }
	   </script>
	   </head>
	   <body>
	           <div></div>
	   </body>
	   </html
	   	`
	*/

	reg := regexp.MustCompile(`delayURL\("(.*)"\);`)
	if reg == nil {
		return Urld{} ,errors.New("连接失败, 请检查是否连接上GiWiFi")
	}
	//提取关键信息
	result := reg.FindAllStringSubmatch(string(body), -1)

	if result == nil {
		return Urld{}, errors.New("自动获取网关错误")
	}

	gtw, err := url_handel(result[0][1])
	if err != nil {
		return Urld{}, err
	}
	//fmt.Println("delayURL: ", result[0][1])

	//http://172.21.1.2:8062/redirect?oriUrl=http://www.baidu.com
	return gtw, nil
}
