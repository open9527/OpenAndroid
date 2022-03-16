package com.farmer.open9527.webview.bridge

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Looper
import android.os.SystemClock
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebSettings
import java.lang.Exception
import java.net.URLEncoder
import java.util.ArrayList


open class X5WebView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : WebView(context, attrs), IWebView {

    companion object {
        const val TAG = "X5WebView"
        const val USER_AGENT =
            "Mozilla/5.0 (Linux; Android 8.1.0; Android SDK built for x86 Build/OSM1.180201.007; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/61.0.3163.98 Mobile Safari; "

    }

    private var responseCallbacks: MutableMap<String, Callback> = hashMapOf()
    private var messageHandlers: MutableMap<String, BridgeHandler> = hashMapOf()
    private var defaultHandler: BridgeHandler = DefaultHandler()
    private var startupMessage: MutableList<Message>? = ArrayList<Message>()

    private var uniqueId: Long = 0


    init {
        initWebSettings(this)
//        webChromeClient
        webViewClient = X5WebViewClient(this)
    }


    override fun send(data: String) {
        send(data, null)
    }

    override fun send(data: String, responseCallback: Callback?) {
        doSend(null, data, responseCallback)
    }

    /**
     * call javascript registered handler
     * 调用javascript处理程序注册
     *
     * @param handlerName handlerName
     * @param data        data
     * @param callBack    CallBackFunction
     */
    override fun callHandler(handlerName: String?, data: String, callBack: Callback?) {
        doSend(handlerName, data, callBack)
    }

    /**
     * register handler,so that javascript can call it
     * 注册处理程序,以便javascript调用它
     *
     * @param handlerName handlerName
     * @param handler     BridgeHandler
     */
    override fun registerHandler(handlerName: String, handler: BridgeHandler?) {
        if (handler != null) {
            // 添加至 Map<String, BridgeHandler>
            messageHandlers[handlerName] = handler
        }
    }


    open fun getStartupMessage(): MutableList<Message>? {
        return startupMessage
    }

    fun setStartupMessage(startupMessage: MutableList<Message>?) {
        this.startupMessage = startupMessage
    }


    /**
     * 保存message到消息队列
     *
     * @param handlerName      handlerName
     * @param data             data
     * @param responseCallback CallBackFunction
     */
    private fun doSend(handlerName: String?, data: String, responseCallback: Callback?) {
        val m = Message()
        if (!TextUtils.isEmpty(data)) {
            m.data = data
        }
        if (responseCallback != null) {
            val callbackStr: String = java.lang.String.format(
                Utils.CALLBACK_ID_FORMAT,
                (++uniqueId).toString() + (Utils.UNDERLINE_STR + SystemClock.currentThreadTimeMillis())
            )
            responseCallbacks[callbackStr] = responseCallback
            m.callbackId = callbackStr
        }
        if (!TextUtils.isEmpty(handlerName)) {
            m.handlerName = handlerName
        }
        queueMessage(m)
    }

    /**
     * 获取到CallBackFunction data执行调用并且从数据集移除
     */
    fun handlerReturnData(url: String) {
        val functionName: String? = Utils.getFunctionFromReturnUrl(url)
        val function = responseCallbacks[functionName]
        val data: String? = Utils.getDataFromReturnUrl(url)
        if (function != null) {
            function.onCallback(data)
            responseCallbacks.remove(functionName)
        }
    }


    /**
     * 刷新消息队列
     */
    fun flushMessageQueue() {
        if (Thread.currentThread() === Looper.getMainLooper().thread) {
            loadUrl(Utils.JS_FETCH_QUEUE_FROM_JAVA, Callback { data ->
                // deserializeMessage 反序列化消息
                var list: List<Message>? = null
                list = try {
                    Message.toArrayList(data)
                } catch (e: Exception) {
                    e.printStackTrace()
                    return@Callback
                }
                if (list == null || list.isEmpty()) {
                    return@Callback
                }
                for (i in list.indices) {
                    val m = list[i]
                    val responseId = m.responseId
                    // 是否是response  CallBackFunction
                    if (!TextUtils.isEmpty(responseId)) {
                        val function = responseCallbacks[responseId]
                        val responseData = m.responseData
                        function!!.onCallback(responseData)
                        responseCallbacks.remove(responseId)
                    } else {
                        var responseFunction: Callback? = null
                        // if had callbackId 如果有回调Id
                        val callbackId = m.callbackId
                        if (!TextUtils.isEmpty(callbackId)) {
                            responseFunction = Callback { data ->
                                val responseMsg = Message()
                                responseMsg.responseId = callbackId
                                responseMsg.responseData = data
                                queueMessage(responseMsg)
                            }
                        } else {
                            responseFunction = Callback {
                                // do nothing
                            }
                        }
                        // BridgeHandler执行
                        var handler: BridgeHandler? = if (!TextUtils.isEmpty(m.handlerName)) {
                            messageHandlers[m.handlerName]
                        } else {
                            defaultHandler
                        }
                        handler?.handler(m.data, responseFunction)
                    }
                }
            })
        }
    }


    private fun loadUrl(jsUrl: String, returnCallback: Callback?) {
        this.loadUrl(jsUrl)
        Log.i(TAG, "loadUrl-->$jsUrl")
        // 添加至 Map<String, CallBackFunction>
        responseCallbacks[Utils.parseFunctionName(jsUrl)] = returnCallback!!
    }

    private fun queueMessage(m: Message) {
        if (startupMessage != null) {
            startupMessage?.add(m)
        } else {
            dispatchMessage(m)
        }
    }

    /**
     * 分发message 必须在主线程才分发成功
     *
     * @param m Message
     */
    fun dispatchMessage(m: Message) {
        var messageJson = m.toJson()
        //escape special characters for json string  为json字符串转义特殊字符
        messageJson = messageJson.replace("(\\\\)([^utrn])".toRegex(), "\\\\\\\\$1$2")
        messageJson = messageJson.replace("(?<=[^\\\\])(\")".toRegex(), "\\\\\"")
        messageJson = messageJson.replace("(?<=[^\\\\])(\')".toRegex(), "\\\\\'")
        messageJson = messageJson.replace("%7B".toRegex(), URLEncoder.encode("%7B"))
        messageJson = messageJson.replace("%7D".toRegex(), URLEncoder.encode("%7D"))
        messageJson = messageJson.replace("%22".toRegex(), URLEncoder.encode("%22"))
        val javascriptCommand: String =
            java.lang.String.format(Utils.JS_HANDLE_MESSAGE_FROM_JAVA, messageJson)
        // 必须要找主线程才会将数据传递出去 --- 划重点
        if (Thread.currentThread() === Looper.getMainLooper().thread) {
            Log.i(TAG, "loadUrl-->$javascriptCommand")
            this.loadUrl(javascriptCommand)
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebSettings(webView: X5WebView) {
        setWebContentsDebuggingEnabled(true)
        val settings: WebSettings = webView.settings
        // 允许文件访问
        settings.allowFileAccess = true
        // 允许网页定位
        settings.setGeolocationEnabled(true)
        // 允许保存密码
        //settings.setSavePassword(true);
        // 开启 JavaScript
        settings.javaScriptEnabled = true
        // 允许网页弹对话框
        settings.javaScriptCanOpenWindowsAutomatically = true
        // 加快网页加载完成的速度，等页面完成再加载图片
//        settings.loadsImagesAutomatically = true
        // 本地 DOM 存储（解决加载某些网页出现白板现象）
        settings.domStorageEnabled = true

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            settings.mixedContentMode = MIXED_CONTENT_ALWAYS_ALLOW
//        }
        // 不显示滚动条
        webView.isVerticalScrollBarEnabled = false
        webView.isHorizontalScrollBarEnabled = false

        //支持屏幕缩放
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        //不显示缩放按钮
        settings.displayZoomControls = false

        //设置编码格式
        settings.defaultTextEncodingName = "utf-8"
    }


    open fun buildUserAgent(suffix: String): String? {
        return USER_AGENT + suffix
    }

}