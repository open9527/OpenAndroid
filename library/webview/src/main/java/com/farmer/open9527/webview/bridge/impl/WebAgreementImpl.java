package com.farmer.open9527.webview.bridge.impl;

import android.text.TextUtils;
import android.util.Log;

import com.farmer.open9527.webview.bridge.BridgeHandler;
import com.farmer.open9527.webview.bridge.Callback;
import com.farmer.open9527.webview.bridge.IWebView;
import com.farmer.open9527.webview.bridge.bean.H5heightBean;
import com.farmer.open9527.webview.bridge.bean.WebBaseBean;
import com.farmer.open9527.webview.bridge.bean.WebBusinessBean;
import com.farmer.open9527.webview.bridge.bean.WebConfigShareBean;
import com.farmer.open9527.webview.bridge.bean.WebConfigUIBean;
import com.farmer.open9527.webview.bridge.bean.WebMediaBean;
import com.farmer.open9527.webview.bridge.bean.WebNewPayBean;
import com.farmer.open9527.webview.bridge.bean.WebOpenAlbumBean;
import com.farmer.open9527.webview.bridge.bean.WebOpenBean;
import com.farmer.open9527.webview.bridge.bean.WebOpenVideoBean;
import com.farmer.open9527.webview.bridge.bean.WebPayBean;
import com.farmer.open9527.webview.bridge.bean.WebPhotoBean;
import com.farmer.open9527.webview.bridge.bean.WebPicEditBean;
import com.farmer.open9527.webview.bridge.bean.WebSchemaBean;
import com.farmer.open9527.webview.bridge.bean.WebShearPlateTextBean;
import com.farmer.open9527.webview.bridge.bean.WebSubscriptionBean;
import com.farmer.open9527.webview.bridge.bean.rp.WebThemeRp;
import com.google.gson.Gson;


import java.util.HashMap;
import java.util.Map;

public class WebAgreementImpl implements IWebAgreement {

    private IWebView iWebView;

    public WebAgreementImpl(IWebView iWebView) {
        this.iWebView = iWebView;
    }

    public static WebAgreementImpl newInstance(IWebView iWebView) {
        return new WebAgreementImpl(iWebView);
    }

    @Override
    public void getVersion(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "getVersion");
    }

    @Override
    public void getToken(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "getToken");
    }

    @Override
    public void getAccount(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "getAccount");
    }

    @Override
    public void configUI(IResponseCallback<WebConfigUIBean> callback) {
        create(WebConfigUIBean.class, callback, "configUI");
    }

    @Override
    public void configShare(IResponseCallback<WebConfigShareBean> callback) {
        create(WebConfigShareBean.class, callback, "configShare");
    }

    @Override
    public void getNetworkType(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "getNetworkType");
    }

    @Override
    public void getLocation(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "getLocation");
    }

    @Override
    public void getClipboardText(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "getClipboardText");
    }

    @Override
    public void setClipboardText(IResponseCallback<WebShearPlateTextBean> callback) {
        create(WebShearPlateTextBean.class, callback, "setClipboardText");
    }

    @Override
    public void showHeader(IResponseCallback<WebConfigUIBean.HeaderBean> callback) {
        create(WebConfigUIBean.HeaderBean.class, callback, "showHeader");
    }

    @Override
    public void hideHeader(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "hideHeader");
    }

    @Override
    public void showShortcuts(IResponseCallback<WebConfigUIBean.ShortcutsBean> callback) {
        create(WebConfigUIBean.ShortcutsBean.class, callback, "showShortcuts");
    }

    @Override
    public void hideShortcuts(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "hideShortcuts");
    }

    @Override
    public void showPanels(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "showPanels");
    }

    @Override
    public void hidePanels(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "hidePanels");
    }

    @Override
    public void showComments(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "showComments");
    }

    @Override
    public void hideComments(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "hideComments");
    }

    @Override
    public void showFooter(IResponseCallback<WebConfigUIBean.FooterBean> callback) {
        create(WebConfigUIBean.FooterBean.class, callback, "showFooter");
    }

    @Override
    public void hideFooter(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "hideFooter");
    }

    @Override
    public void showTick(IResponseCallback<WebConfigUIBean.TickBean> callback) {
        create(WebConfigUIBean.TickBean.class, callback, "showTick");
    }

    @Override
    public void hideTick(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "hideTick");
    }

    @Override
    public void activeFontSize(IResponseCallback<WebConfigUIBean.ThemeBean.FontSizeBean> callback) {
        create(WebConfigUIBean.ThemeBean.FontSizeBean.class, callback, "activeFontSize");
    }

    @Override
    public void getActiveFontSize(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "getActiveFontSize");
    }

    @Override
    public void activeColorTheme(IResponseCallback<WebConfigUIBean.ThemeBean.ColorBean> callback) {
        create(WebConfigUIBean.ThemeBean.ColorBean.class, callback, "activeColorTheme");
    }

    @Override
    public void getActiveColorTheme(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "getActiveColorTheme");
    }

    @Override
    public void getBusiness(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "getBusiness");
    }

    @Override
    public void updateBusiness(IResponseCallback<WebBusinessBean> callback) {
        create(WebBusinessBean.class, callback, "updateBusiness");
    }

    @Override
    public void openAction(IResponseCallback<WebOpenBean> callback) {
        create(WebOpenBean.class, callback, "openAction");
    }

    @Override
    public void openLogin(IResponseCallback<WebBaseBean> callback) {
        create(WebBaseBean.class, callback, "openLogin");
    }

    @Override
    public void openAlbum(IResponseCallback<WebOpenAlbumBean> callback) {
        create(WebOpenAlbumBean.class, callback, "openAlbum");
    }

    @Override
    public void openComment(IResponseCallback<WebOpenBean.CommentBean> callback) {
        create(WebOpenBean.CommentBean.class, callback, "openComment");
    }

    @Override
    public void openReport(IResponseCallback<WebOpenBean.ReportBean> callback) {
        create(WebOpenBean.ReportBean.class, callback, "openReport");
    }

    @Override
    public void openMoreBox(IResponseCallback<WebConfigUIBean.MoreBoxBean> callback) {
        create(WebConfigUIBean.MoreBoxBean.class, callback, "openMoreBox");
    }

    @Override
    public void openVideoPlayer(IResponseCallback<WebOpenVideoBean> callback) {
        create(WebOpenVideoBean.class, callback, "openVideoPlayer");
    }

    @Override
    public void downloadImage(IResponseCallback<WebMediaBean> callback) {
        create(WebMediaBean.class, callback, "downloadImage");
    }

    @Override
    public void scanImage(IResponseCallback<WebMediaBean> callback) {
        create(WebMediaBean.class, callback, "scanImage");
    }

    @Override
    public void getSubscription(IResponseCallback<WebSubscriptionBean> callback) {
        create(WebSubscriptionBean.class, callback, "getSubscription");
    }

    @Override
    public void followSubscription(IResponseCallback<WebSubscriptionBean> callback) {
        create(WebSubscriptionBean.class, callback, "followSubscription");
    }

    @Override
    public void unfollowSubscription(IResponseCallback<WebSubscriptionBean> callback) {
        create(WebSubscriptionBean.class, callback, "unfollowSubscription");
    }

    @Override
    public void updateWebViewHeight(IResponseCallback<H5heightBean> callback) {
        create(H5heightBean.class, callback, "updateWebViewHeight");
    }

    @Override
    public void showPicEdit(IResponseCallback<WebPicEditBean> callback) {
        create(WebPicEditBean.class, callback, "showPicEdit");
    }

    @Override
    public void getPhoneAndVideo(IResponseCallback<WebPhotoBean> callback) {
        create(WebPhotoBean.class, callback, "getPhoneAndVideo");
    }

    @Override
    public void uploadControlVideoOrPic(IResponseCallback<WebPhotoBean> callback) {
        create(WebPhotoBean.class, callback, "uploadControlVideoOrPic");
    }

    @Override
    public void gopay(IResponseCallback<WebPayBean> callback) {
        create(WebPayBean.class, callback, "gopay");
    }

    @Override
    public void openPay(IResponseCallback<WebNewPayBean> callback) {
        create(WebNewPayBean.class, callback, "openPay");
    }

    @Override
    public void validateSchema(IResponseCallback<WebSchemaBean> callback) {
        create(WebSchemaBean.class, callback, "validateSchema");
    }


    @Override
    public void sendReadyEvent() {
        send("ready", null);
    }

    @Override
    public void sendVoiceClickEvent() {
        send("voiceClick", null);
    }

    @Override
    public void sendLongTouchEvent(int x, int y) {
        Map<String, Integer> params = new HashMap();
        params.put("x", x);
        params.put("y", y);
        send("longTouch", new Gson().toJson(params));
    }

    @Override
    public void sendThemeChangeEvent(WebThemeRp webThemeRp) {
        send("themeChange", new Gson().toJson(webThemeRp));
    }

    @Override
    public void onActivityResume(String key) {
        Map<String, String> params = new HashMap();
        send("key", new Gson().toJson(params));
    }

    private void send(String handlerName, String data) {
        iWebView.callHandler(handlerName, data, null);
    }

    public <T extends WebBaseBean> void create(Class<T> cl, IResponseCallback<T> responseCallback, String handlerName) {
        Log.i("WebAgreementImpl", "handlerName-->" + handlerName);
        iWebView.registerHandler(handlerName, new BridgeHandler() {
            @Override
            public void handler(String data, Callback callback) {
                Log.i("WebAgreementImpl", "handlerName-->" + handlerName);
                Log.i("WebAgreementImpl", "data-->" + data);
                T t = null;
                try {
                    t = cl.newInstance();
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }

                if (!TextUtils.isEmpty(data)) {
                    try {
                        Gson gson = new Gson();
                        t = gson.fromJson(data, cl);
                    } catch (Exception e) {
                        Log.i("WebAgreementImpl", "Exception-->" + e.getMessage());
                    }
                }

                if (t != null) {
                    t.setCallback(callback);
                }

                responseCallback.onCallback(t);
            }
        });
    }

}
