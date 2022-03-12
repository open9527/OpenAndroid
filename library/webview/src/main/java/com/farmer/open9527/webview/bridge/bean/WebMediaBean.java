package com.farmer.open9527.webview.bridge.bean;

import java.util.List;

public class WebMediaBean extends WebBaseBean {

    private List<ImagesBean> images;
    private ImagesBean image;
    private String sourceUrl;
    private boolean autoProcess;// 扫描结果自动处理，还是返回扫描结果

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public boolean isAutoProcess() {
        return autoProcess;
    }

    public void setAutoProcess(boolean autoProcess) {
        this.autoProcess = autoProcess;
    }

    public ImagesBean getImage() {
        return image;
    }

    public void setImage(ImagesBean image) {
        this.image = image;
    }

    public static class ImagesBean extends WebBaseBean {
        private String sourceUrl;

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }

    }


}
