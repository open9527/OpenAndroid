package com.farmer.open9527.webview.bridge.bean;

/**
 * 内置页面接口
 */
public class WebOpenVideoBean extends WebBaseBean{
    public boolean autoStart;    //是否自动播放,
    public String timestamp="0"; //开始时间戳：毫秒
    private VideoBean video;

    //打开"全屏视频播放"页面
    public static class VideoBean extends WebBaseBean {
        private String sourceUrl;   //视频地址

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }
    }

    public boolean isAutoStart() {
        return autoStart;
    }

    public void setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public VideoBean getVideo() {
        return video;
    }

    public void setVideo(VideoBean video) {
        this.video = video;
    }
}

