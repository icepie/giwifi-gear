package giwifi

import (
	"net/http"
	"fmt"
	"io/ioutil"
)

func GetGateway() {
	resp, err :=   http.Get("http://gwifi.com.cn")
	if err != nil {
        // handle error
	}
	defer resp.Body.Close()
	body, err := ioutil.ReadAll(resp.Body)
	if err != nil {
        // handle error
	}

	fmt.Println(string(body))
}
