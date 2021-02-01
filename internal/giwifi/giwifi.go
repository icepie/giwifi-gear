package giwifi

const (
	// TestURL for test the connet
	TestURL = "http://test.gwifi.com.cn"

	// PCUA User-Agent of PC
	PCUA = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36"

	// PADUA User-Agent of PAD
	PADUA = "Mozilla/5.0 (iPad; CPU OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25"

	// GatewayPort is the default port of gateway
	GatewayPort = 8062

	// GatewayAuthState for get the auth state from gateway
	/*
		os type from index.html
		if (isiOS) url += "&os=ios";
		if (isAndroid) url += "&os=android";
		if (isWinPC) url += "&os=windows";
		if (isMac) url += "&os=mac";
		if (isLinux || isUbuntuExplorer()) url += "&os=linux";
	*/
	GatewayAuthState = "/getApp.htm?action=getAuthState&os=mac"

	// AuthState for get the auth state from wifidog
	AuthState = "/wifidog/get_auth_state"
)
