package com.farmer.open9527.module.test.media

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.*
import com.farmer.open9527.base.BaseActivity
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.module.test.BR
import com.farmer.open9527.module.test.R
import com.farmer.open9527.module.test.network.NetWorkActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 *@author   open_9527
 *Create at 2021/10/21
 **/
class MediaActivity : BaseActivity() {

    private var mViewModel: MediaViewModel? = null

    // 权限获取
    var permissions: Array<String> = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )


    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(MediaViewModel::class.java);
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.media_activity, BR.vm, mViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initStatusBar()
        super.onCreate(savedInstanceState)
        mViewModel?.albumLiveData?.observe(this, {
            LogUtils.i(TAG, "viewModelScope:$it")
        })
        mViewModel?.viewModelScope?.launch(Dispatchers.IO) {
            mViewModel?.albumLiveData?.postValue(MediaFileUtils.getAlbum(this@MediaActivity))
        }
        mViewModel?.getMediaAlbum(this)
    }

    private fun initStatusBar() {
        BarUtils.transparentStatusBar(this)
        BarUtils.setStatusBarLightMode(this, true)
    }


    //TODO:registerForActivityResult 方法必须在 onStart之前注册
    /**
     * StartActivityForResult
     */
    private val requestDataLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data?.getStringExtra("data")
                // Handle data from SecondActivity
                LogUtils.i(TAG, "data:$data")
                ToastUtils.showShort("data:$data")
            }
        }

    /**
     *  RequestMultiplePermissions
     */
    private val registerPermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (it[permissions[0]]!! && it[permissions[1]]!! && it[permissions[2]]!!) {
                ToastUtils.showShort("权限获取成功!")
            } else {
                ToastUtils.showShort("权限获取失败!")
            }
            LogUtils.i(TAG, "result:" + GsonUtils.toJson(it))
        }

    /**
     * GetMultipleContents
     */
    private val registerMultipleContents =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            LogUtils.i(TAG, "result:" + GsonUtils.toJson(it))
        }

    /**
     * TakePicturePreview
     */
    private val registerTakePicturePreview =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            LogUtils.i(TAG, "result:$it")
        }

    /**
     * TakePicture
     */
    private val registerTakePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) {
            LogUtils.i(TAG, "result:$it")
            if (it) {
                LogUtils.i(TAG, "result:$mTakePictureUri")
                mViewModel?.valueImageUri?.set(mTakePictureUri)
            }
        }


    /**
     * CaptureVideo
     */
    private val registerCaptureVideo =
        registerForActivityResult(ActivityResultContracts.CaptureVideo()) {
            LogUtils.i(TAG, "result:$it")
            if (it) {
                LogUtils.i(TAG, "result:$mCaptureVideoUri")
            } else {
                mViewModel?.valueImageUri?.get()?.let { it1 -> delFileByUri(it1) }
            }

        }

    /**
     * TakePictureUri
     */

    private val registerCustomTakePictureForResultUri =
        registerForActivityResult(TakePictureUri()) {
            LogUtils.i(TAG, "result:$it")

            launchCustomCropImage(it);
        }

    /**
     * CropImage
     */
    private val registerCustomCropImage = registerForActivityResult(CropImage()) {
        LogUtils.i(TAG, "result:$it")
        mViewModel?.valueImageUri?.set(it)
    }

    private fun launchForActivityResult() {
        //只包含 图片,视频,不包含音频 ( */*, image/* , video/*, audio/*),
        registerMultipleContents.launch("*/*")

    }

    private var mCaptureVideoUri: Uri? = null
    private fun launchCaptureVideo() {
        mCaptureVideoUri = MediaFileUtils.createVideoUri(this)
        registerCaptureVideo.launch(mCaptureVideoUri)
    }

    private var mTakePictureUri: Uri? = null
    private fun launchTakePicture() {
        mTakePictureUri = MediaFileUtils.createImageUri(this)
        registerTakePicture.launch(mTakePictureUri)
    }

    private fun launchCustomCropImage(uri: Uri) {
        registerCustomCropImage.launch(CropImageResult(uri, 1, 1))
    }

    inner class ClickProxy {

        var intentClick = View.OnClickListener {
            LogUtils.i(TAG, "intentClick")
            //跳转传值
            val intent = Intent(this@MediaActivity, NetWorkActivity::class.java)
            requestDataLauncher.launch(intent)
        }

        var permissionsClick = View.OnClickListener {
            LogUtils.i(TAG, "requestClick")
            registerPermissions.launch(permissions)
        }

        var mediaClick = View.OnClickListener {
            LogUtils.i(TAG, "mediaClick")
//            mediaFile()
            launchForActivityResult()
        }

        var takePicturePreviewClick = View.OnClickListener {
            registerTakePicturePreview.launch(null)
        }

        var takePictureClick = View.OnClickListener {
            launchTakePicture();

        }

        var captureVideoClick = View.OnClickListener {
            launchCaptureVideo()

        }
        var customTakePictureClick = View.OnClickListener {
            registerCustomTakePictureForResultUri.launch(null)

        }
    }

    private fun delFileByUri(uri: Uri): Boolean {
//        contentResolver.delete(uri, null, null);
        return FileUtils.delete(UriUtils.uri2File(uri))
    }

}