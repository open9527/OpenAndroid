package com.farmer.open9527.common.application

import android.app.Application
import android.util.Log
import com.farmer.open9527.base.BaseApplication
import com.farmer.open9527.common.R
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.smtt.export.external.TbsCoreSettings
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.TbsListener

/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
abstract class CommonApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        initSdk(this)
    }

    companion object {
        private const val TAG = "CommonApplication"

        fun initSdk(application: Application) {
            initSmartRefresh()
            initTBS(application)
        }


        private fun initSmartRefresh() {
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.setPrimaryColorsId(android.R.color.transparent, R.color.common_text_color)
                ClassicsHeader(context)
            }

            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                layout.setPrimaryColorsId(android.R.color.transparent, R.color.common_text_color)
                ClassicsFooter(context).setDrawableSize(20f)
            }
        }

        private fun initTBS(application: Application) {
            // 首次初始化冷启动优化 在调用TBS初始化、创建WebView之前进行如下配置
            val map = HashMap<String, Any>()
            map[TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER] = true
            map[TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE] = true
            QbSdk.initTbsSettings(map)

            /* 设置允许移动网络下进行内核下载。默认不下载，会导致部分一直用移动网络的用户无法使用x5内核 */
            QbSdk.setDownloadWithoutWifi(true)

            /* SDK内核初始化周期回调，包括 下载、安装、加载 */
            QbSdk.setTbsListener(object : TbsListener {
                /**
                 * @param stateCode 110: 表示当前服务器认为该环境下不需要下载；200下载成功
                 */
                override fun onDownloadFinish(stateCode: Int) {
                    when (stateCode) {
                        110 -> {
                            Log.i(TAG, "onDownloadFinish :表示当前服务器认为该环境下不需要下载")
                        }
                        200 -> {
                            Log.i(TAG, "onDownloadFinish :下载成功")
                        }
                        else -> {
                            Log.i(TAG, "onDownloadFinish :stateCode= $stateCode")
                        }
                    }
                }

                /**
                 * @param i 200、232安装成功
                 */
                override fun onInstallFinish(i: Int) {
                    Log.i(TAG, "onInstallFinish: 安装成功" + (200 == i || 232 == i))
                }

                /**
                 * 首次安装应用，会触发内核下载，此时会有内核下载的进度回调。
                 * @param progress 0 - 100
                 */
                override fun onDownloadProgress(progress: Int) {
                    Log.i(TAG, "onDownloadProgress: $progress")
                }
            })

            /* 此过程包括X5内核的下载、预初始化，接入方不需要接管处理x5的初始化流程，希望无感接入 */
            QbSdk.initX5Environment(
                application,
                object : QbSdk.PreInitCallback {
                    override fun onCoreInitFinished() {
                        // 内核初始化完成，可能为系统内核，也可能为系统内核
                    }

                    /**
                     * 预初始化结束
                     * 由于X5内核体积较大，需要依赖wifi网络下发，所以当内核不存在的时候，默认会回调false，此时将会使用系统内核代替
                     * 内核下发请求发起有24小时间隔，卸载重装、调整系统时间24小时后都可重置
                     * @param isX5 是否使用X5内核
                     */
                    override fun onViewInitFinished(isX5: Boolean) {
                        Log.i(TAG, "onViewInitFinished :isX5=$isX5")
                    }
                })
        }

    }
}