package com.farmer.open9527.webview.bridge.bean;


import com.farmer.open9527.webview.bridge.Callback;

import java.io.Serializable;

public class WebBaseBean implements Serializable {
    private String UUID;
    private Callback callback;
    public WebBaseBean( ) {

    }
    public WebBaseBean(Callback callback) {
        this.callback = callback;
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
}
