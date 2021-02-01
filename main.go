package main

import (
	"flag"
	"fmt"
	"log"
	"net/url"
	"os"
	"runtime"

	"github.com/icepie/giwifi-gear-go/internal/giwifi"
)

const (
	// Version of giwifi-gear-go
	Version = "0.1.1"
)

var (
	username, password, gateway, atype              string
	ishelp, isbind, isquit, isdaemon, isinfo, isver bool
	gtw                                             *url.URL
	err                                             error
)

// build a gtw url
func buildGTW(host string) (*url.URL, error) {
	port := giwifi.GatewayPort

	u := fmt.Sprintf("http://%s:%d", host, port)

	return url.Parse(u)
}

func initFlag() {
	flag.BoolVar(&ishelp, "h", false, "show this help message and exit")
	flag.StringVar(&gateway, "g", "", "set the gateway of GiWiFi")
	flag.StringVar(&username, "u", "", "set the username")
	flag.StringVar(&password, "p", "", "set the password")
	flag.StringVar(&atype, "t", "pc", "auth type (use pc/pad/phone, and the default value is pc)")
	flag.BoolVar(&isinfo, "i", false, "print the debug info")
	flag.BoolVar(&isbind, "b", false, "bind or rebind your device")
	flag.BoolVar(&isquit, "q", false, "sign out of account authentication")
	flag.BoolVar(&isdaemon, "d", false, "running in the daemon mode (remove sharing restrictions)")
	flag.BoolVar(&isver, "v", false, "show version and exit")

	flag.Parse()

	// check the help avg
	if ishelp {
		fmt.Fprintf(os.Stderr, `
giwifi-gear
	A cli tool for login giwifi (multi-platform, fast, small)
usage:
	giwifi-gear-go [-h] [-g <GATEWAY>] [-u <USERNAME>] [-p <PASSWORD>] [-t <TYPE>] [-i] [-q] [-b] [-d] [-v]

options:
		`)
		flag.PrintDefaults()
		os.Exit(0)
	}

	// check the ver avg
	if isver {
		fmt.Printf("giwifi-gear-go version %v\n", Version)
		os.Exit(0)
	}

	// check the bool type avgs
	if (isbind && isquit) || (isquit && isdaemon) || (isdaemon && isbind) {
		fmt.Println("Error: don't use bind, quit and daemon at same time!")
		os.Exit(1)
	}

	// check the auth type avg
	if !(atype == "pc" || atype == "pad" || atype == "phone") {
		fmt.Println("Error: plz use the true value(pc/pad/phone)!")
		os.Exit(1)
	} else if atype == "phone" {
		fmt.Println("Error: phone type is not be supported now!")
		os.Exit(1)
	}

	// check the gateway avg
	var isauto = false
	if gateway == "" {
		gtw, err = giwifi.GetGatewayByDelay() //try to get gateway
		if err != nil {
			log.Println(err)
			fmt.Printf("Plz enter gateway: ")
			fmt.Scanln(&gateway)
		} else {
			isauto = true
		}

	}
	if !isauto {
		gtw, err = buildGTW(gateway)
		if err != nil {
			log.Println(err)
			fmt.Println("Please enter the right host")
			os.Exit(1)
		}
	}

	// check the username avg
	if username == "" {
		fmt.Printf("Plz enter username: ")
		fmt.Scanln(&username)
	}

	// check the password avg
	if password == "" {
		fmt.Printf("Plz enter password: ")
		fmt.Scanln(&password)
	}

}

func getSystemInfo() {
	SysType := runtime.GOOS
	SysArch := runtime.GOARCH
	fmt.Println("OS: " + SysType)
	fmt.Println("ARCH: " + SysArch)
}

func main() {
	initFlag()

	fmt.Printf("user：%v\npassword：%v\ngateway：%v\ntype：%v\n",
		username, password, gtw, atype)

	b, err := giwifi.GetGatewayAuthState(gtw)
	if err == nil {
		println(b)
	}

	getSystemInfo()
}
