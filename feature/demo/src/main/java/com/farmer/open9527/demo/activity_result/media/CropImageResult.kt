package com.farmer.open9527.demo.activity_result.media

import android.net.Uri


/**
 *@author   open_9527
 *Create at 2021/10/21
 **/
class CropImageResult(
    private var uri: Uri?,//裁剪框横向比例数值
    private var aspectX: Int,//裁剪框纵向比例数值
    private var aspectY: Int
) {


    fun getUri(): Uri? {
        return uri
    }

    fun setUri(uri: Uri?) {
        this.uri = uri
    }

    fun getAspectX(): Int {
        return aspectX
    }

    fun setAspectX(aspectX: Int) {
        this.aspectX = aspectX
    }

    fun getAspectY(): Int {
        return aspectY
    }

    fun setAspectY(aspectY: Int) {
        this.aspectY = aspectY
    }


}