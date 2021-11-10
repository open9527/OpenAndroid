package com.farmer.open9527.module.test.media

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContract


/**
 *@author   open_9527
 *Create at 2021/10/21
 **/
public class CaptureVideoUri : ActivityResultContract<Any, Uri>() {

    private var uri: Uri? = null

    override fun createIntent(context: Context, input: Any?): Intent {
        uri = MediaFileUtils.createVideoUri(context);
        return Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(MediaStore.EXTRA_OUTPUT, uri)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
        return uri
    }
}