package main

import (
	"crypto/aes"
	"flag"
	"fmt"
	"giwifi-mix/util"
	"os"
)

var (
	key, iv, plain string
	ishelp         bool
)

func initFlag() {
	flag.StringVar(&plain, "p", "", "the plaintext")
	flag.StringVar(&iv, "i", "", "iv (initialization vector)")
	flag.StringVar(&key, "k", "1234567887654321", "the key")

	flag.Parse()

	// check the username avg
	if plain == "" || iv == "" {
		fmt.Printf("error")
		os.Exit(1)
	}
}

func main() {
	initFlag()
	fmt.Printf(util.Crypto(plain, key, iv, aes.BlockSize))
}
