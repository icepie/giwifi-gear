package main

import (
	"fmt"
	"log"
	"net/url"
	"runtime"

	"github.com/icepie/giwifi-gear-go/internal/giwifi"
)

func getSystemInfo() {
	SysType := runtime.GOOS
	SysArch := runtime.GOARCH
	fmt.Println("OS: " + SysType)
	fmt.Println("ARCH: " + SysArch)
}

func inputGTW() (*url.URL, error) {
	port := giwifi.GatewayPort

	var host string
	fmt.Scanln(&host)

	u := fmt.Sprintf("http://%s:%d", host, port)

	return url.Parse(u)
}

func main() {
	getSystemInfo()
	gtw, err := giwifi.GetGatewayByDelay() //try to get gateway
	if err != nil {
		log.Println(err)
		fmt.Printf("Please enter the gateway manually: ")
		//manually enter gateway
		gtw, err = inputGTW()
		if err != nil {
			log.Println(err)
			fmt.Printf("Please enter the right host")
		}
	}

	fmt.Println(gtw)
}
