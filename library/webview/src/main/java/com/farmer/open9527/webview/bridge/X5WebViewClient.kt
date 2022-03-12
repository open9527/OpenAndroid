package com.farmer.open9527.webview.bridge

import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import java.io.UnsupportedEncodingException
import java.net.URLDecoder

open class X5WebViewClient(val webView: X5WebView) : WebViewClient() {

    companion object {
        const val TAG = "X5WebViewClient"
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
        var url = url
        Log.i(TAG, "shouldOverrideUrlLoading url=$url")
        try {
            url = URLDecoder.decode(url, "UTF-8")
            Log.i(TAG, "shouldOverrideUrlLoading decode url =$url")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        if (url.startsWith(Utils.RMT_RETURN_DATA)) {
            // 如果是返回数据
            webView.handlerReturnData(url)
        } else if (url.startsWith(Utils.RMT_SCHEMA_QUEUE_MESSAGE)) {
            webView.flushMessageQueue()
        } else if (url.startsWith("http://") || url.startsWith("https://")) {
            return super.shouldOverrideUrlLoading(view, url)
        } else if (url.startsWith("rmt")) {
            getScheme(url)
            return true
        }
        return true
    }


    override fun shouldOverrideUrlLoading(view: WebView?, webResourceRequest: WebResourceRequest): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            shouldOverrideUrlLoading(view, webResourceRequest.url.toString())
        } else {
            super.shouldOverrideUrlLoading(view, webResourceRequest)
        }
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        val messageList = webView.getStartupMessage()
        if (messageList != null && messageList.isNotEmpty()) {
            for (m in webView.getStartupMessage()!!) {
                webView.dispatchMessage(m)
            }
            webView.setStartupMessage(null)
        }
    }

    open fun getScheme(scheme: String) {

    }
}