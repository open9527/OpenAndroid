package com.farmer.open9527.webview.bridge.bean;

import java.util.List;

/**
 * 内置页面接口
 */
public class WebOpenAlbumBean extends WebBaseBean {

    private int currentIndex; //当前打开第几个图片
    private List<ImagesBean> images;  //图片资源列表

    public class ImagesBean extends WebBaseBean {
        public String sourceUrl;       //原图路径
        public String thumbUrl;   //缩略图路径

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }

        public String getThumbUrl() {
            return thumbUrl;
        }

        public void setThumbUrl(String thumbUrl) {
            this.thumbUrl = thumbUrl;
        }
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }
}

