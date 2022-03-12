package com.farmer.open9527.webview.bridge.bean.rp;

import java.io.Serializable;

/**
 *  当前登录用户信息
 */
public class WebUserInfoRp implements Serializable {

    private String token; //会话token
    private AccountBean account;
    private String customId;

    public static class AccountBean implements Serializable {

        private String id; //用户id
        private String nickname; //昵称
        private AvatarBean avatar; //昵称

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public AvatarBean getAvatar() {
            return avatar;
        }

        public void setAvatar(AvatarBean avatar) {
            this.avatar = avatar;
        }
    }
    public static  class AvatarBean implements Serializable{
        private String sourceUrl ;//头像原图地址
        private String thumbUrl ;//头像缩略图地址

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


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AccountBean getAccount() {
        return account;
    }

    public void setAccount(AccountBean account) {
        this.account = account;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }
}
