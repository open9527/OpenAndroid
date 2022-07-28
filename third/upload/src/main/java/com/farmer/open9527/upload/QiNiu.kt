package com.farmer.open9527.upload

import com.qiniu.android.common.FixedZone
import com.qiniu.android.storage.*
import org.json.JSONObject
import java.io.File
import java.util.*


object QiNiu {
    private const val TAG = "QiNiu"

    private const val CONNECT_TIMEOUT = 90
    private const val RESPONSE_TIMEOUT = 90
    private const val CONCURRENT_TASK_COUNT = 3
    private const val FIXED_ZONE = "up-qos.storage.shmedia.tech"
    private var cancelUpload = false


    fun upload(data: String, key: String, token: String, dirPath: String, mimeType: String) {
        QiNiuLogCatUtil.setLogCatInfo(true, "(debug模式正式上线请关闭)")
        val uploadManager = getUploadManager(createRecorder(dirPath), createKeyGenerator())
        uploadManager.put(
            data, key, token, { key, info, response ->
                if (info.isOK) {
                    val data: JSONObject
                    var materialId = ""
                    var mimeType = ""
                    if (response.has("data")) {
                        data = response.getJSONObject("data")
                        if (data.has("materialId")) {
                            materialId = data.getString("materialId")
                        }
                        if (data.has("mimeType")) {
                            mimeType = data.getString("mimeType").lowercase(Locale.getDefault())
                        }
                    }
                    QiNiuLogCatUtil.i(TAG, "key:$key materialId:$materialId  mimeType:$mimeType")
                }
            }, createUploadOptions(mimeType)
        )
    }


    private fun getUploadManager(recorder: Recorder, keyGen: KeyGenerator): UploadManager {
        return UploadManager(createConfiguration(recorder, keyGen))
    }


    private fun createUploadOptions(mimeType: String): UploadOptions {
        return UploadOptions(null, mimeType, false, createUpProgressHandler(), createUpCancellationSignal())
    }

    private fun createUpProgressHandler(): UpProgressHandler {
        return UpProgressHandler { key, percent ->
            val progress = (percent * 100).toInt()
            QiNiuLogCatUtil.i(TAG, "key:$key  percent:$percent  progress:$progress")
        }
    }

    private fun createUpCancellationSignal(): UpCancellationSignal {
        return UpCancellationSignal {
            getCancelUpload()
        }
    }


    private fun createConfiguration(recorder: Recorder, keyGen: KeyGenerator): Configuration {
        return Configuration.Builder()
            .connectTimeout(CONNECT_TIMEOUT) // 链接超时。默认90秒
            .useHttps(true) // 是否使用https上传域名
            .resumeUploadVersion(Configuration.RESUME_UPLOAD_VERSION_V2) // 选择分片上传版本
            .useConcurrentResumeUpload(true) // 使用并发上传，使用并发上传时，除最后一块大小不定外，其余每个块大小固定为4M，
            .concurrentTaskCount(CONCURRENT_TASK_COUNT) // 并发上传线程数量为3
            .responseTimeout(RESPONSE_TIMEOUT) // 服务器响应超时。默认90秒
            .recorder(recorder) // recorder分片上传时，已上传片记录器。默认null
            .recorder(recorder, keyGen) // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
//            .zone(FixedZone.zone0) // 设置区域，不指定会自动选择。指定不同区域的上传域名、备用域名、备用IP。
            .zone(FixedZone(arrayOf(FIXED_ZONE)))
            .build()
    }


    //断点记录文件保存的文件夹位置 eg: String dirPath = Utils.getApp().getExternalCacheDir() + "/QiniuAndroid";
    private fun createRecorder(dirPath: String): Recorder {
        return FileRecorder(dirPath)
    }

    private fun createKeyGenerator(): KeyGenerator {
        return object : KeyGenerator {
            override fun gen(key: String, file: File): String {
                return key + "_._" + StringBuffer(file.absolutePath).reverse()
            }

            override fun gen(key: String?, sourceId: String?): String {
                return key + "_._" + sourceId
            }
        }
    }


    private fun getCancelUpload(): Boolean {
        return cancelUpload
    }

    fun setCancelUpload(cancelUpload: Boolean) {
        this.cancelUpload = cancelUpload
    }
}