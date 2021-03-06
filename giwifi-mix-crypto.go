package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"encoding/base64"
	"flag"
	"fmt"
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
	fmt.Printf(crypto(plain, key, iv, aes.BlockSize))
}

func crypto(plaintext string, key string, iv string, blockSize int) string {
	bKey := []byte(key)
	bIV := []byte(iv)
	bPlaintext := zeroPadding([]byte(plaintext), blockSize)
	block, _ := aes.NewCipher(bKey)
	ciphertext := make([]byte, len(bPlaintext))
	mode := cipher.NewCBCEncrypter(block, bIV)
	mode.CryptBlocks(ciphertext, bPlaintext)
	return base64.StdEncoding.EncodeToString(ciphertext)
}

func zeroPadding(ciphertext []byte, blockSize int) []byte {
	padding := blockSize - len(ciphertext)%blockSize
	padtext := bytes.Repeat([]byte{0}, padding) //用0去填充
	return append(ciphertext, padtext...)
}
