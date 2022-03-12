package com.farmer.open9527.webview.bridge

import java.lang.StringBuilder

object Utils {
    private const val RMT_SCHEMA = "rmt://"
    const val RMT_SCHEMA_QUEUE_MESSAGE = RMT_SCHEMA + "__QUEUE_MESSAGE__/"
    const val RMT_RETURN_DATA = RMT_SCHEMA + "return/" //格式为   yy://return/{function}/returncontent

    const val UNDERLINE_STR = "_"

    private const val RMT_FETCH_QUEUE = RMT_RETURN_DATA + "_fetchQueue/"
    private const val EMPTY_STR = ""
    private const val SPLIT_MARK = "/"

    const val CALLBACK_ID_FORMAT = "JAVA_CB_%s"
    const val JS_HANDLE_MESSAGE_FROM_JAVA =
        "javascript:WebViewJavascriptBridge._handleMessageFromNative('%s');"
    const val JS_FETCH_QUEUE_FROM_JAVA = "javascript:WebViewJavascriptBridge._fetchQueue();"

    const val JAVASCRIPT_STR = "javascript:"


    // 例子 javascript:WebViewJavascriptBridge._fetchQueue(); --> _fetchQueue
    fun parseFunctionName(jsUrl: String): String {
        return jsUrl.replace("javascript:WebViewJavascriptBridge.", "").replace("\\(.*\\);".toRegex(), "")
    }

    // 获取到传递信息的body值
    // url = yy://return/_fetchQueue/[{"responseId":"JAVA_CB_2_3957","responseData":"Javascript Says Right back aka!"}]
    fun getDataFromReturnUrl(url: String): String? {
        if (url.startsWith(RMT_FETCH_QUEUE)) {
            // return = [{"responseId":"JAVA_CB_2_3957","responseData":"Javascript Says Right back aka!"}]
            return url.replace(
                RMT_FETCH_QUEUE,
                EMPTY_STR
            )
        }

        // temp = _fetchQueue/[{"responseId":"JAVA_CB_2_3957","responseData":"Javascript Says Right back aka!"}]
        val temp: String = url.replace(
            RMT_RETURN_DATA,
            EMPTY_STR
        )
        val functionAndData: Array<String> =
            temp.split(SPLIT_MARK).toTypedArray()
        if (functionAndData.size >= 2) {
            val sb = StringBuilder()
            for (i in 1 until functionAndData.size) {
                sb.append(functionAndData[i])
            }
            // return = [{"responseId":"JAVA_CB_2_3957","responseData":"Javascript Says Right back aka!"}]
            return sb.toString()
        }
        return null
    }

    // 获取到传递信息的方法
    // url = yy://return/_fetchQueue/[{"responseId":"JAVA_CB_1_360","responseData":"Javascript Says Right back aka!"}]
   fun getFunctionFromReturnUrl(url: String): String? {
        // temp = _fetchQueue/[{"responseId":"JAVA_CB_1_360","responseData":"Javascript Says Right back aka!"}]
        val temp: String = url.replace(
            RMT_RETURN_DATA,
            EMPTY_STR
        )
        val functionAndData: Array<String> =
            temp.split(SPLIT_MARK).toTypedArray()
        return if (functionAndData.isNotEmpty()) {
            // functionAndData[0] = _fetchQueue
            functionAndData[0]
        } else null
    }
}