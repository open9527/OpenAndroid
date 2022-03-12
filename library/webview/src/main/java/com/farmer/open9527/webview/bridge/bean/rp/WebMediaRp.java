package com.farmer.open9527.webview.bridge.bean.rp;


import com.farmer.open9527.webview.bridge.bean.WebBaseBean;

import java.util.ArrayList;

public class WebMediaRp extends WebBaseBean {

    private ArrayList images;
    public ArrayList getImages() {
        return images;
    }

    public void setImages(ArrayList images) {
        this.images = images;
    }

    public static class ImagesBean extends WebBaseBean {
        private String file;  //相册中的图片文件名

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }
    }

    public static class ScanImagBean extends WebBaseBean {
       private String  contentType; //数据类型，image：图片; QRCode: 二维码；
        private String   content; //二维码中存储的内容

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }


}

