package com.farmer.open9527.webview.bridge.bean.rp;

import java.io.Serializable;

public class WebSubscriptionRp implements Serializable {
    public String contentType;  //固定为 subscription
    public String title;            //标题
    public String subtitle;    //副标题
    public String summary;                   //简介
    public String shareUrl;    //网页访问地址
    //标题图
    public static class TitleImage implements Serializable {
        public String sourceUrl;       //标题图地址
        public String thumbUrl; //标题图缩略图地址
    }

    //统计数据
    public static class CountBean implements Serializable {
        public String follow; //当前用户是否关注。follow: 关注；unfollow：未关注；
        public String fansCount;       //粉丝数
        public String likeCount;    //获赞数
        public String postCount;     //发布数
        public boolean like;      //当前用户是否点赞
    }

}


