package com.farmer.open9527.webview.bridge.bean.rp;

import java.io.Serializable;

public class WebVersionRp implements Serializable {

    private AppBean app;
    private JsSdkBean jssdk;

    public AppBean getApp() {
        return app;
    }

    public void setApp(AppBean app) {
        this.app = app;
    }

    public JsSdkBean getJssdk() {
        return jssdk;
    }

    public void setJssdk(JsSdkBean jssdk) {
        this.jssdk = jssdk;
    }

    /**
     * APP版本信息
     */
    public static class AppBean implements Serializable {
        private String name;
        private String version;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
    /**
     * JS版本信息
     */
    public static class JsSdkBean implements Serializable {
        private String name;
        private String version;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
