package giwifi

import (
	"fmt"
	"io/ioutil"
	"net/http"
	"regexp"
)

type Urld struct {
	protocol string
	host     string
	port     string
	path     string
}

func url_handel(url string) Urld {
	reg := regexp.MustCompile(`(\w+):\/\/([^/:]+):(\d*)?([^# ]*)`)
	result := reg.FindAllStringSubmatch(url, -1)

	if result == nil {

		fmt.Println("自动获取网关错误")

	}

	var urldeal Urld

	urldeal.protocol = result[0][1]
	urldeal.host = result[0][2]
	urldeal.port = result[0][3]
	urldeal.path = result[0][4]

	return urldeal
}

func GetGateway() *Urld {

	resp, err := http.Get("http://gwifi.com.cn")
	if err != nil {
		fmt.Println("连接失败, 请检查是否连接上GiWiFi")
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		fmt.Println("连接超时，可能已超出上网区间")
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
		fmt.Println("连接失败, 请检查是否连接上GiWiFi")
	}
	//提取关键信息
	result := reg.FindAllStringSubmatch(string(body), -1)
	if result == nil {

		fmt.Println("自动获取网关错误")

	}

	gtw := url_handel(result[0][1])
	//fmt.Println("delayURL: ", result[0][1])

	//http://172.21.1.2:8062/redirect?oriUrl=http://www.baidu.com
	return &gtw
}
