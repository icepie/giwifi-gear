package giwifi

import (
	"io/ioutil"
	"net/http"
	"net/url"
)

// GetGatewayAuthState Get the auth state from gateway
func GetGatewayAuthState(gtw *url.URL) (string, error) {

	client := &http.Client{}

	r, _ := http.NewRequest(http.MethodGet, gtw.String()+GatewayAuthState, nil)

	r.Header.Add("User-Agent", PCUA)

	resp, err := client.Do(r)
	if err != nil {
		return "", err
	}

	b, err := ioutil.ReadAll(resp.Body)
	if err != nil {
		return "", err
	}

	defer resp.Body.Close()

	return string(b), nil

}
