package com.farmer.open9527.webview.bridge.bean;

public class WebSubscriptionBean extends WebBaseBean {
    public String id;
    public String contentType = "subscription";   //固定为 subscription
    public String title;     //标题
    public String subtitle;    //副标题
    public String summary;  //简介
    public String shareUrl;   //网页访问地址
    public CountBean count;
    public TitleImageBean titleImage;

    //统计数据
    public static class CountBean {
        public int fansCount = 0;       //粉丝数
        public int likeCount = 0;       //获赞数
        public int postCount = 0;       //发布数
        public String follow = "follow";   //当前用户是否关注。follow: 关注；unfollow：未关注；
        public boolean like = false;     //当前用户是否点赞
    }

    //标题图
    public static class TitleImageBean {
        public String sourceUrl;       //标题图地址
        public String thumbUrl;   //标题图缩略图地址
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
