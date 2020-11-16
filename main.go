package main

import (
	"github.com/icepie/giwifi-gear/giwifi"
	"fmt"
	"runtime"
)

func get_system_info(){
	SysType := runtime.GOOS
	SysArch := runtime.GOARCH
	fmt.Println("OS: " + SysType)
	fmt.Println("ARCH: " + SysArch)
}

func gtw_input() giwifi.Urld{
	gtw := giwifi.Urld{}
	gtw.Port = "8062"
	fmt.Printf("请输入手动输入IPV4网关地址: ")
	fmt.Scanln(&gtw.Host)
	return gtw
}

func main() {
	get_system_info()
	gtw, err:= giwifi.GetGateway() //try to get the gateway ip
	if err != nil{
		fmt.Printf("%v\n",err)
		gtw = gtw_input()
	}
	fmt.Println(gtw.Host)
}
