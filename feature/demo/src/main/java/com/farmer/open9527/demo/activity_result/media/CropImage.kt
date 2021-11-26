package com.farmer.open9527.demo.activity_result.media

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract


/**
 *@author   open_9527
 *Create at 2021/10/21
 **/
class CropImage : ActivityResultContract<CropImageResult, Uri>() {

    //裁剪后输出的图片文件Uri
    private var mUriOutput: Uri? = null

    override fun createIntent(context: Context, input: CropImageResult?): Intent {
        //把CropImageResult转换成裁剪图片的意图
        val intent = Intent("com.android.camera.action.CROP")
        val mimeType = context.contentResolver.getType(input!!.getUri()!!)
        mUriOutput=MediaFileUtils.createImageUri(context)
        context.grantUriPermission(
            context.packageName,
            mUriOutput,
            Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        //去除默认的人脸识别，否则和剪裁匡重叠
        intent.putExtra("noFaceDetection", true)
        intent.setDataAndType(input!!.getUri(), mimeType)
        //crop=true 有这句才能出来最后的裁剪页面.
        //crop=true 有这句才能出来最后的裁剪页面.
        intent.putExtra("crop", "true")
        intent.putExtra("output", mUriOutput)
        //返回格式
        intent.putExtra("outputFormat", "JPEG")
        intent.putExtra("return-data", false)

        //配置裁剪图片的宽高比例

        //配置裁剪图片的宽高比例
        if (input!!.getAspectX() !== 0 && input!!.getAspectY() !== 0) {
            intent.putExtra("aspectX", input.getAspectX())
            intent.putExtra("aspectY", input.getAspectY())
        }
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        return mUriOutput;
    }
}