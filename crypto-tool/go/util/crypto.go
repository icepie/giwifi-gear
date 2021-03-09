package util

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"encoding/base64"
)

// Crypto for special GiWiFi version
func Crypto(plaintext string, key string, iv string, blockSize int) (string, error) {
	bKey := []byte(key)
	bIV := []byte(iv)
	bPlaintext := zeroPadding([]byte(plaintext), blockSize)
	block, err := aes.NewCipher(bKey)
	if err != nil {
		return "", err
	}
	ciphertext := make([]byte, len(bPlaintext))
	mode := cipher.NewCBCEncrypter(block, bIV)
	mode.CryptBlocks(ciphertext, bPlaintext)
	return base64.StdEncoding.EncodeToString(ciphertext), nil
}

func zeroPadding(ciphertext []byte, blockSize int) []byte {
	padding := blockSize - len(ciphertext)%blockSize
	padtext := bytes.Repeat([]byte{0}, padding)
	return append(ciphertext, padtext...)
}
