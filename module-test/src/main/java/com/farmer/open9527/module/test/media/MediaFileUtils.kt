package com.farmer.open9527.module.test.media

import android.content.ContentValues
import android.content.Context
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.UriUtils
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

/**
 *@author   open_9527
 *Create at 2021/10/21
 *
 * 多媒体工具类
 **/

object MediaFileUtils {


    /**
     * 创建图片地址uri,用于保存拍照后的照片 Android 10以后使用这种方法
     * @return 图片的uri
     */
    fun createImageUri(context: Context): Uri? {
        val contentValues = ContentValues()
        contentValues.put(
            MediaStore.Images.Media.DISPLAY_NAME,
            "IMG-" + System.currentTimeMillis().toString()
        )
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")

        // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
        return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            context.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
        } else {
            context.contentResolver.insert(
                MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                contentValues
            )
        }
    }

    /**
     * 创建视频地址uri,用于保存拍摄的视频 Android 10以后使用这种方法
     * @return 视频的uri
     */
    fun createVideoUri(context: Context): Uri? {
        val contentValues = ContentValues()
        contentValues.put(
            MediaStore.Images.Media.DISPLAY_NAME,
            "VID-" + System.currentTimeMillis().toString()
        )

        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "video/mp4")

        // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
        return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            context.contentResolver.insert(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
        } else {
            context.contentResolver.insert(
                MediaStore.Video.Media.INTERNAL_CONTENT_URI,
                contentValues
            )
        }
    }

    /**
     * 更新图片到相册
     *
     * @param context
     * @param filePath
     */
    fun scanFile(context: Context?, filePath: String?) {
        if (TextUtils.isEmpty(filePath)) {
            return
        }
        MediaScannerConnection.scanFile(context, arrayOf(filePath), arrayOf("image/jpeg"))
        { s, uri -> LogUtils.i("updateToAlbum-->", UriUtils.uri2File(uri).absolutePath) }

    }


    private val QUERY_URI = MediaStore.Files.getContentUri("external")
    private const val ORDER_BY_DESC = MediaStore.Files.FileColumns.DATE_MODIFIED + " DESC"
    private const val ORDER_BY_ASC = MediaStore.Files.FileColumns.DATE_MODIFIED + " ASC"
    private const val NOT_GIF = "!='image/gif'"
    private const val DURATION = "duration"

    /**
     * 获取图片or视频
     */
    private val SELECTION_ALL_ARGS = arrayOf(
        MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE.toString(),
        MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO.toString()
    )

    /**
     * 获取图片
     */
    private val SELECTION_IMAGE = arrayOf(
        MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE.toString()
    )


    /**
     * 获取视频
     */
    private val SELECTION_VIDEO = arrayOf(
        MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO.toString()
    )

    /**
     * 视频和图片
     */
    private const val SELECTION_ALL = (
            MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
                    + " AND " + MediaStore.MediaColumns.SIZE + ">0"
                    + " OR " + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
                    + " AND " + "1000")

    /**
     * 图片
     */
    private const val SELECTION = (MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
            + " AND " + MediaStore.MediaColumns.SIZE + ">0")

    /**
     * 图片(不包含gif)
     */
    private const val SELECTION_NOT_GIF = (MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
            + " AND " + MediaStore.MediaColumns.SIZE + ">0"
            + " AND " + MediaStore.MediaColumns.MIME_TYPE + NOT_GIF)


    /**
     * 获取所有系统资源
     *
     * @param time_condition
     * @param isGif
     * @return
     */
    fun getAllMediaCondition(time_condition: String, isGif: Boolean): String? {
        return ("(" + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
                + (if (isGif) "" else " AND " + MediaStore.MediaColumns.MIME_TYPE + NOT_GIF)
                + " OR "
                + (MediaStore.Files.FileColumns.MEDIA_TYPE + "=? AND " + time_condition) + ")"
                + " AND " + MediaStore.MediaColumns.SIZE + ">0")
    }


    /**
     * 查询条件(音视频)
     *
     * @param time_condition
     * @return
     */
    fun getSelectionArgsForSingleMediaCondition(time_condition: String): String? {
        return (MediaStore.Files.FileColumns.MEDIA_TYPE + "=?"
                + " AND " + MediaStore.MediaColumns.SIZE + ">0"
                + " AND " + time_condition)
    }

    /**
     * 获取视频(最长或最小时间)
     *
     * @param exMaxLimit
     * @param exMinLimit
     * @param videoMaxS
     * @param videoMinS
     * @return
     */
    fun getDurationCondition(
        exMaxLimit: Long,
        exMinLimit: Long,
        videoMaxS: Long,
        videoMinS: Long
    ): String? {
        var maxS = if (videoMaxS == 0L) Long.MAX_VALUE else videoMaxS
        if (exMaxLimit != 0L) maxS = Math.min(maxS, exMaxLimit)
        return java.lang.String.format(
            Locale.CHINA, "%d <%s duration and duration <= %d",
            Math.max(exMinLimit, videoMinS),
            if (Math.max(exMinLimit, videoMinS) == 0L) "" else "=",
            maxS
        )
    }

    /**
     * 媒体文件数据库字段
     */
    private val PROJECTION = arrayOf(
        MediaStore.Files.FileColumns._ID,
        MediaStore.MediaColumns.DATA,
        MediaStore.MediaColumns.MIME_TYPE,
        MediaStore.MediaColumns.SIZE,
        MediaStore.MediaColumns.WIDTH,
        MediaStore.MediaColumns.HEIGHT,
        DURATION
    )


    fun getAlbum(context: Context): List<String> {
        val mAllImage: ArrayList<String> = ArrayList()
        val contentResolver = context.contentResolver
        //TODO:需要权限判断(READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE)
        val cursor = contentResolver.query(
            QUERY_URI,
            PROJECTION,
            SELECTION_ALL,
            SELECTION_ALL_ARGS,
            ORDER_BY_DESC
        )
        if (cursor != null && cursor.moveToFirst()) {
            val pathIndex: Int = cursor.getColumnIndex(PROJECTION[1])
            val mimeTypeIndex: Int = cursor.getColumnIndex(PROJECTION[2])
            val sizeIndex: Int = cursor.getColumnIndex(PROJECTION[3])
            do {
                val size: Long = cursor.getLong(sizeIndex)
                // 图片大小不得小于 1 KB
                if (size < 1024) {
                    continue
                }
                val type: String = cursor.getString(mimeTypeIndex)
                val path: String = cursor.getString(pathIndex)
                if (TextUtils.isEmpty(path) || TextUtils.isEmpty(type)) {
                    continue
                }
                val file = File(path)
                if (!file.exists() || !file.isFile) {
                    continue
                }
//                val parentFile: File = file.parentFile ?: continue
                // 获取目录名作为专辑名称
//                val albumName: String = parentFile.getName()
//                var data: MutableList<String?>? = mAllAlbum.get(albumName)
//                if (data == null) {
//                    data = ArrayList()
//                    mAllAlbum.put(albumName, data)
//                }
//                data.add(path)
                mAllImage.add(path)
            } while (cursor.moveToNext())
            cursor.close()
        }
        return mAllImage;
    }

}