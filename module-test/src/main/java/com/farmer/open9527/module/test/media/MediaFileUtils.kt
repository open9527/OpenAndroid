package com.farmer.open9527.module.test.media

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.UriUtils
import java.io.File

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


    fun getAlbum(context: Context): List<String> {
        val mAllImage: ArrayList<String> = ArrayList()
        val contentUri = MediaStore.Files.getContentUri("external")
        val sortOrder = MediaStore.Files.FileColumns.DATE_MODIFIED + " DESC"
        val selection =
            "(" + MediaStore.Files.FileColumns.MEDIA_TYPE + "=?)" + " AND " + MediaStore.MediaColumns.SIZE + ">0"

        val contentResolver = context.contentResolver
        val projections = arrayOf(
            MediaStore.Files.FileColumns._ID, MediaStore.MediaColumns.DATA,
            MediaStore.MediaColumns.DISPLAY_NAME, MediaStore.MediaColumns.DATE_MODIFIED,
            MediaStore.MediaColumns.MIME_TYPE, MediaStore.MediaColumns.WIDTH,
            MediaStore.MediaColumns.HEIGHT, MediaStore.MediaColumns.SIZE
        )

        //TODO:需要权限判断(READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE)
        val cursor = contentResolver.query(
            contentUri,
            projections,
            selection,
            arrayOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE.toString()),
            sortOrder
        )
        if (cursor != null && cursor.moveToFirst()) {
            val pathIndex: Int = cursor.getColumnIndex(MediaStore.MediaColumns.DATA)
            val mimeTypeIndex: Int = cursor.getColumnIndex(MediaStore.MediaColumns.MIME_TYPE)
            val sizeIndex: Int = cursor.getColumnIndex(MediaStore.MediaColumns.SIZE)
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