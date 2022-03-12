package com.farmer.open9527.webview.bridge;

public class DefaultHandler implements BridgeHandler{

	String TAG = "DefaultHandler";
	
	@Override
	public void handler(String data, Callback function) {
		if(function != null){
			function.onCallback("DefaultHandler response data");
		}
	}

}
