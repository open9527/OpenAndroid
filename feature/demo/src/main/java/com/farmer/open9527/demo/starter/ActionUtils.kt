package com.farmer.open9527.demo.starter

import android.content.Intent
import android.text.TextUtils
import com.farmer.open9527.demo.R
import com.farmer.open9527.demo.activity_result.TestResultApiActivity
import com.farmer.open9527.demo.api.JsonApiUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.net.URI
import java.util.HashMap
import android.content.ComponentName
import com.blankj.utilcode.util.*
import com.farmer.open9527.demo.load_image.TestImageLoadActivity
import com.farmer.open9527.demo.night.TestDayNightActivity


object ActionUtils {
    private const val TAG = "ActionUtils"
    const val ACTION_BUNDLE_NAME = "ACTION_BUNDLE_DATA"

    private var mActionList: List<ActionVo>? = null

    private fun getStarterManager(): StarterManager {
        val starterManager: StarterManager = StarterManager.instance()!!
        if (!starterManager.ready()) {
            starterManager.register(TestResultApiActivity.Starter())
            starterManager.register(TestImageLoadActivity.Starter())
            starterManager.register(TestDayNightActivity.Starter())
        }
        return starterManager
    }


    fun jump(actionUrl: String?) {
        LogUtils.i(TAG, "actionUrl:$actionUrl")
        if (TextUtils.isEmpty(actionUrl)) {
            return
        }

        try {
            val uri = URI(actionUrl)
            val serviceName = uri.host

            val starter = getStarterManager().find(serviceName) ?: return

            val queryParams: Map<String, String> = parseQuery(uri.rawQuery)
            if (!starter.validate(serviceName, queryParams)) {
                return
            }
            starter.startActivity(serviceName, queryParams)

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun jumpJson(actionUrl: String?) {
        LogUtils.i(TAG, "actionUrl:$actionUrl")
        if (TextUtils.isEmpty(actionUrl)) {
            return
        }
        try {
            val uri = URI(actionUrl)
            val actionHost = uri.host
            val actionVo = findActionUrl(actionHost)
            if (actionVo != null) {
                if (actionVo.authorize == true) {
                    LogUtils.i(TAG, "需要判断执行登陆...")
                    ToastUtils.showLong("需要授权验证")
                    return
                }

                val queryParams: Map<String, String> = parseQuery(uri.rawQuery)
                startActivity(actionVo.classPath, GsonUtils.toJson(queryParams))
            }
            LogUtils.i(TAG, "actionVo:" + actionVo?.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    private fun parseQuery(query: String?): Map<String, String> {
        val map: MutableMap<String, String> = HashMap()
        query?.run {
            val fields = split("&").toTypedArray()
            for (field in fields) {
                val pair = field.split("=").toTypedArray()
                if (pair.size > 1 && !TextUtils.isEmpty(pair[0])) {
                    map[pair[0]] = toDecode(pair[1])
                }
            }
        }
        return map
    }

    fun startActivity(cls: Class<*>) {
        val intent = Intent(Utils.getApp(), cls)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        Utils.getApp().startActivity(intent)
    }

    private fun startActivity(cls: String?, json: String?) {
        val intent = Intent()
        val componentName = ComponentName(AppUtils.getAppPackageName(), cls!!)
        intent.component = componentName
        intent.putExtra(ACTION_BUNDLE_NAME, json)
        Utils.getApp().startActivity(intent)
    }

    private fun toEncode(url: String): String {
        return EncodeUtils.urlEncode(url, "UTF-8")
    }

    private fun toDecode(url: String): String {
        return EncodeUtils.urlDecode(url, "UTF-8")
    }

    private fun getRawFileList(): List<ActionVo> {
        val adapterSetting: Map<Type, Any> = JsonApiUtils.defaultAdapterSetting()
        val gson: Gson = JsonApiUtils.buildGson(adapterSetting)
        return gson.fromJson(
            ResourceUtils.readRaw2String(R.raw.action),
            object : TypeToken<List<ActionVo>>() {}.type
        )
    }

    private fun findActionUrl(url: String?): ActionVo? {
        if (mActionList == null) {
            mActionList = getRawFileList()
        }
        mActionList?.forEach {
            val serviceNameList: List<String>? = it.serviceNames
            serviceNameList?.forEachIndexed { index, serviceName ->
                if (serviceName == url) {
                    it.serviceNameIndex = index
                    return it
                }
            }
        }
        return null
    }

}