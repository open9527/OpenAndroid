package com.farmer.open9527.webview.bridge.bean;



import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class WebNewPayBean extends WebBaseBean {

    public static final String TYPE_WX = "wx";
    public static final String TYPE_ALIPAY = "alipay";
    //支付宝支付
    private AlipayBean alipay;
    //微信支付
    private WxpayBean wxpay;
    //银联支付
    private YinlianPay yinlianPay;

    public AlipayBean getAlipay() {
        return alipay;
    }

    public void setAlipay(AlipayBean alipay) {
        this.alipay = alipay;
    }

    public WxpayBean getWxpay() {
        return wxpay;
    }

    public void setWxpay(WxpayBean wxpay) {
        this.wxpay = wxpay;
    }

    public YinlianPay getYinlianPay() {
        return yinlianPay;
    }

    public void setYinlianPay(YinlianPay yinlianPay) {
        this.yinlianPay = yinlianPay;
    }

    public JSONObject getJsonObject() {
        JSONObject object = new JSONObject();
        try {
            object.put("appId", wxpay.getAppId());
            object.put("timestamp", wxpay.getTimestamp());
            object.put("partnerid", wxpay.getPartnerId());
            object.put("prepayid", wxpay.getPrepayId());
            object.put("noncestr", wxpay.getNonceStr());
            object.put("packageValue", wxpay.getPackageX());
            object.put("sign", wxpay.getSign());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    public static class WxpayBean implements Serializable {
        /**
         * partnerId : 1581238181
         * prepayId : wx191646001393654f660236317116780000
         * package : Sign=WXPay
         * timestamp : 1634633160
         * nonceStr : 6626746152
         * sign : 7CA08E684EDC32B610325C3505C7815F
         */
        private String appId;
        private String partnerId;
        private String prepayId;
        @SerializedName("package")
        private String packageX;
        private String timestamp;
        private String nonceStr;
        private String sign;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getPartnerId() {
            return partnerId;
        }

        public void setPartnerId(String partnerId) {
            this.partnerId = partnerId;
        }

        public String getPrepayId() {
            return prepayId;
        }

        public void setPrepayId(String prepayId) {
            this.prepayId = prepayId;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
    public static class AlipayBean implements Serializable {

        private String order;

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }
    }
    public static class PayStateBean implements Serializable {

        // "0"：订单支付成功；"1"：订单支付失败；"2"：用户取消。
        private String status;
        private String info;
        private String token;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
    public static class YinlianPay implements Serializable {

        private String appPayRequest;
        private String payType;

        public String getAppPayRequest() {
            return appPayRequest;
        }

        public void setAppPayRequest(String appPayRequest) {
            this.appPayRequest = appPayRequest;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }
    }
}
