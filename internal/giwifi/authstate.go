package giwifi

import (
	"errors"
	"io/ioutil"
	"net/http"
	"net/url"
	"time"
)

// GetGatewayAuthState Get the auth state from gateway
func GetGatewayAuthState(gtw *url.URL) (string, error) {

	client := &http.Client{Timeout: 5 * time.Second}

	r, err := http.NewRequest(http.MethodGet, "http://"+gtw.Hostname()+GatewayAuthState, nil)

	if err != nil {
		return "", err
	}

	r.Header.Add("User-Agent", UA)

	resp, err := client.Do(r)
	if err != nil {
		return "", err
	}

	if resp.StatusCode != 200 {
		return "", errors.New("status code error")
	}

	b, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		return "", err
	}

	defer resp.Body.Close()

	return string(b), nil

}
