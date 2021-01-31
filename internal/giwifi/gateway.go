package giwifi

import (
	"errors"
	"io/ioutil"
	"net/http"
	"net/url"
	"regexp"
	"strings"
)

// GetGatewayByDelay Get the gateway of giwifi by delayURL
func GetGatewayByDelay() (*url.URL, error) {

	resp, err := http.Get(TESTURL)
	if err != nil {
		var u *url.URL
		return u, err
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		var u *url.URL
		return u, err
	}

	/*
		body_test := `
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

	if strings.Contains(string(body), "it works!") {
		var u *url.URL
		return u, errors.New("You are authed or no connected to GiWiFi")
	}

	reg := regexp.MustCompile(`delayURL\("(.*)"\);`)
	result := reg.FindAllStringSubmatch(string(body), -1)
	if result == nil {
		var u *url.URL
		return u, errors.New("Fail to get the gateway of GiWiFi by delayURL")
	}

	gtw, err := url.Parse(result[0][1])
	if err != nil {
		var u *url.URL
		return u, err
	}

	return gtw, nil
}
