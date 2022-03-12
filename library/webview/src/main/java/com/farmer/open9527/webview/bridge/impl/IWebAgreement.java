package com.farmer.open9527.webview.bridge.impl;


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

public interface IWebAgreement {


    /* **********************************1.基础接口*********************************************** */

    /**
     * 添加JS-SDK版本
     * 1.APP版本
     * 2.JS-SDK版本
     */
    void getVersion(IResponseCallback<WebBaseBean> callback);

    /**
     * 添加当前会话 Token
     */
    void getToken( IResponseCallback<WebBaseBean> callback);

    /**
     * 添加当前登录用户信息
     */
    void getAccount( IResponseCallback<WebBaseBean> callback);


    /* **********************************2.设置"接口*********************************************** */

    /**
     * "设置UI元素
     */
    void configUI( IResponseCallback<WebConfigUIBean> callback);

    /***
     * 设置分享数据
     */
    void configShare( IResponseCallback<WebConfigShareBean> callback);

    /* **********************************3.设备环境接口*********************************************** */

    /**
     * 添加网络环境
     */
    void getNetworkType( IResponseCallback<WebBaseBean> callback);

    /**
     * 添加地理位置
     */
    void getLocation( IResponseCallback<WebBaseBean> callback);

    /**
     * 添加剪切板文本
     */
    void getClipboardText( IResponseCallback<WebBaseBean> callback);

    /**
     * 获取剪切板文本
     */
    void setClipboardText( IResponseCallback<WebShearPlateTextBean> callback);

    /* **********************************4.UI接口*********************************************** */

    /**
     * 显示标题条
     */
    void showHeader( IResponseCallback<WebConfigUIBean.HeaderBean> callback);

    /**
     * 隐藏标题条
     */
    void hideHeader( IResponseCallback<WebBaseBean> callback);

    /**
     * 显示快捷操作区
     */
    void showShortcuts( IResponseCallback<WebConfigUIBean.ShortcutsBean> callback);

    /**
     * 隐藏快捷操作区
     */
    void hideShortcuts( IResponseCallback<WebBaseBean> callback);

    /**
     * 显示动态功能块
     */
    void showPanels( IResponseCallback<WebBaseBean> callback);

    /**
     * 隐藏动态功能块
     */
    void hidePanels( IResponseCallback<WebBaseBean> callback);

    /**
     * 显示评论区
     */
    void showComments( IResponseCallback<WebBaseBean> callback);

    /**
     * 隐藏评论区
     */
    void hideComments( IResponseCallback<WebBaseBean> callback);

    /**
     * 显示底边栏
     */
    void showFooter( IResponseCallback<WebConfigUIBean.FooterBean> callback);

    /**
     * 隐藏底边栏
     */
    void hideFooter( IResponseCallback<WebBaseBean> callback);

    /**
     * 显示已读计时器
     */
    void showTick( IResponseCallback<WebConfigUIBean.TickBean> callback);

    /**
     * 激活页面风格菜单中的字体
     */
    void activeFontSize( IResponseCallback<WebConfigUIBean.ThemeBean.FontSizeBean> callback);

    /**
     * 获取当前激活的字体
     */
    void getActiveFontSize( IResponseCallback<WebBaseBean> callback);

    /**
     * 激活页面风格菜单中的配色方案
     */
    void activeColorTheme( IResponseCallback<WebConfigUIBean.ThemeBean.ColorBean> callback);

    /**
     * 获取当前激活的配色方案
     */
    void getActiveColorTheme( IResponseCallback<WebBaseBean> callback);

    /**
     * 隐藏已读计时器
     */
    void hideTick( IResponseCallback<WebBaseBean> callback);

    /**
     * 设置WEB视图高度
     */
    void updateWebViewHeight( IResponseCallback<H5heightBean> callback);

    /* **********************************6.业务内容接口************************************************/

    /**
     * 获取业务内容设置
     */
    void getBusiness( IResponseCallback<WebBaseBean> callback);

    /**
     * 更新业务内容设置
     */
    void updateBusiness( IResponseCallback<WebBusinessBean> callback);

    /* **********************************7.业务内容接口************************************************/

    /**
     * 打开"通用跳转链接"
     */
    void openAction( IResponseCallback<WebOpenBean> callback);

    /**
     * 打开"登录"页面
     */
    void openLogin( IResponseCallback<WebBaseBean> callback);

    /**
     * 打开"预览图片"页面
     */
    void openAlbum( IResponseCallback<WebOpenAlbumBean> callback);

    /**
     * 打开"评论"页面
     */
    void openComment( IResponseCallback<WebOpenBean.CommentBean> callback);

    /**
     * 打开"举报"页面
     */
    void openReport( IResponseCallback<WebOpenBean.ReportBean> callback);


    /**
     * 打开"更多"页面
     */
    void openMoreBox( IResponseCallback<WebConfigUIBean.MoreBoxBean> callback);

    /**
     * 打开"全屏视频播放"页面
     */
    void openVideoPlayer( IResponseCallback<WebOpenVideoBean> callback);

    /* **********************************8.内置操作接口*********************************************** */

    /**
     * 下载图片到相册
     */
    void downloadImage( IResponseCallback<WebMediaBean> callback);


    /**
     * 扫描图片（识别二维码）
     */
    void scanImage( IResponseCallback<WebMediaBean> callback);

    /* **********************************9.订阅号接口*********************************************** */

    /**
     * 获取订阅号信息
     */
    void getSubscription( IResponseCallback<WebSubscriptionBean> callback);

    /**
     * 关注订阅号
     */
    void followSubscription( IResponseCallback<WebSubscriptionBean> callback);

    /**
     * 取消关注订阅号
     */
    void unfollowSubscription( IResponseCallback<WebSubscriptionBean> callback);

    /**
     * 图片保存,识别
     */

    void showPicEdit( IResponseCallback<WebPicEditBean> callback);

    /**
     * 上传图片
     */
    void getPhoneAndVideo( IResponseCallback<WebPhotoBean> callback);


    void uploadControlVideoOrPic( IResponseCallback<WebPhotoBean> callback);
    /**
     * 支付
     */
    void gopay( IResponseCallback<WebPayBean> callback);

    /**
     * 新支付
     */
    void openPay( IResponseCallback<WebNewPayBean> callback);

    /**
     * 检查schema是否可用
     */
    void validateSchema( IResponseCallback<WebSchemaBean> callback);

    /* **********************************9.通知事件接口*********************************************** */

    /**
     * //系统就绪后会执行ready方法
     * //所有接口调用都必须在ready获得结果之后
     */
    void sendReadyEvent();

    /**
     * 发送播放语音事件
     */
    void sendVoiceClickEvent();

    /**
     * 发送长按屏幕事件
     *
     * @param x //横坐标，单位：像素
     * @param y //纵坐标，单位：像素
     */
    void sendLongTouchEvent(int x, int y);

    /**
     * 变更页面风格事
     */
    void sendThemeChangeEvent(WebThemeRp webThemeRp);


    /**
     *通知事件
     */
    void onActivityResume(String key);
}
