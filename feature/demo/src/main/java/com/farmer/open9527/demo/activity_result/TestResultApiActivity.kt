package com.farmer.open9527.demo.activity_result

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.blankj.utilcode.util.*
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.common.dialog.CommonTipDialog
import com.farmer.open9527.demo.R
import com.farmer.open9527.demo.BR
import com.farmer.open9527.demo.activity_result.media.CropImage
import com.farmer.open9527.demo.activity_result.media.CropImageResult
import com.farmer.open9527.demo.activity_result.media.MediaFileUtils
import com.farmer.open9527.demo.activity_result.media.TakePictureUri
import com.farmer.open9527.demo.load_image.TestImageLoadActivity
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.decoration.SpacesItemDecoration
import com.farmer.open9527.recycleview.layoutmanager.WrapContentLinearLayoutManager



/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
class TestResultApiActivity : CommonActivity() {

    private var mViewModel: TestResultApiViewModel? = null

    private var permissions: Array<String> = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.CAMERA
    )


    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(TestResultApiViewModel::class.java)
    }

    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.test__result_api__activity, BR.vm, mViewModel!!)
    }

    override fun initView(bundle: Bundle?) {
        super.initView(bundle)
        initRecycleView()
        initDialog()
    }


    override fun initRequest() {
        super.initRequest()
        mViewModel?.valueListData?.add(
            TestResultApiCell(
                "registerStartActivityForResult",
                iResultApiCell
            )
        )
        mViewModel?.valueListData?.add(TestResultApiCell("registerMultiplePermissions", iResultApiCell))
        mViewModel?.valueListData?.add(TestResultApiCell("registerMultipleContents", iResultApiCell))
        mViewModel?.valueListData?.add(TestResultApiCell("registerTakePicturePreview", iResultApiCell))
        mViewModel?.valueListData?.add(TestResultApiCell("registerTakePicture", iResultApiCell))
        mViewModel?.valueListData?.add(TestResultApiCell("registerCaptureVideo", iResultApiCell))
        mViewModel?.valueListData?.add(TestResultApiCell("registerCustomTakePicture", iResultApiCell))
        mViewModel?.valueListData?.add(TestResultApiCell("registerCustomCropImage", iResultApiCell))
    }


    private fun initRecycleView() {
        mViewModel?.valueLayoutManager?.set(WrapContentLinearLayoutManager(this))
        mViewModel?.valueItemDecoration?.set(
            SpacesItemDecoration(this)
                .setParam(R.color.common_accent_color, SizeUtils.dp2px(10f))
        )
        mViewModel?.valueAdapter?.set(BaseBindingCellListAdapter())

    }

    private fun initDialog() {
        CommonTipDialog.with(this)
            .setTitle(StringUtils.getString(R.string.test_media_dialog_title))
            .setOnlyConfirm(true)
            .setConfirmContent(StringUtils.getString(R.string.test_media_dialog_confirm))
            .setConfirmTextColor(ColorUtils.getColor(R.color.common_accent_color))
            .addListener(object : CommonTipDialog.ICommonTipDialog {
                override fun onConfirm() {
                    startActivity(TestImageLoadActivity::class.java)
                }
            }).showDialog()
    }


    private val iResultApiCell = object : TestResultApiCell.IResultApiCell {
        override fun registerCallBack(title: String?) {
            when (title!!) {
                "registerStartActivityForResult" -> onRegisterStartActivityForResult()
                "registerMultiplePermissions" -> onRegisterMultiplePermissions()
                "registerMultipleContents" -> onRegisterMultipleContents()
                "registerTakePicturePreview" -> onRegisterTakePicturePreview()
                "registerTakePicture" -> onRegisterTakePicture()
                "registerCaptureVideo" -> onRegisterCaptureVideo()
                "registerCustomTakePicture" -> onRegisterCustomTakePicture()
                "registerCustomCropImage" -> onRegisterCustomCropImage()
            }
        }
    }

    /**
     * StartActivityForResult
     */
    private val registerStartActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val url = result.data?.getStringExtra("url")
                LogUtils.i(TAG, "url:$url")
                ToastUtils.showShort("url:$url")
            }
        }

    private fun onRegisterStartActivityForResult() {
        val intent = Intent(this, TestImageLoadActivity::class.java)
        registerStartActivityForResult.launch(intent)
    }

    /**
     *  RequestMultiplePermissions
     */
    private val registerMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (it[permissions[0]]!! && it[permissions[1]]!! && it[permissions[2]]!!) {
                ToastUtils.showShort("权限获取成功!")
            } else {
                ToastUtils.showShort("权限获取失败!")
            }
            LogUtils.i(TAG, "result:" + GsonUtils.toJson(it))
        }

    private fun onRegisterMultiplePermissions() {
        registerMultiplePermissions.launch(permissions)
    }


    /**
     * GetMultipleContents
     */
    private val registerMultipleContents =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) {
            LogUtils.i(TAG, "result:" + GsonUtils.toJson(it))
        }

    private fun onRegisterMultipleContents() {
        //只包含 图片,视频,不包含音频 ( */*, image/* , video/*, audio/*),
        registerMultipleContents.launch("*/*")
    }


    /**
     * TakePicturePreview
     */
    private val registerTakePicturePreview =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            LogUtils.i(TAG, "result:$it")
        }

    private fun onRegisterTakePicturePreview() {
        registerTakePicturePreview.launch(null)
    }


    /**
     * TakePicture
     */
    private val registerTakePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) {
            LogUtils.i(TAG, "result:$it")
            if (it) {
                LogUtils.i(TAG, "result:$mTakePictureUri")
            } else {
                MediaFileUtils.delFileByUri(mTakePictureUri!!)
            }
        }

    private var mTakePictureUri: Uri? = null
    private fun onRegisterTakePicture() {
        mTakePictureUri = MediaFileUtils.createImageUri(this)
        registerTakePicture.launch(mTakePictureUri)
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
                MediaFileUtils.delFileByUri(mCaptureVideoUri!!)
            }

        }

    private var mCaptureVideoUri: Uri? = null
    private fun onRegisterCaptureVideo() {
        mCaptureVideoUri = MediaFileUtils.createVideoUri(this)
        registerCaptureVideo.launch(mCaptureVideoUri)
    }


    /**
     * TakePictureUri
     */

    private val registerCustomTakePicture =
        registerForActivityResult(TakePictureUri()) {
            LogUtils.i(TAG, "result:$it")
            registerCustomCropImage.launch(CropImageResult(it, 1, 1))
        }

    private fun onRegisterCustomTakePicture() {
        registerCustomTakePicture.launch(null)
    }

    /**
     * CropImage
     */
    private val registerCustomCropImage = registerForActivityResult(CropImage()) {
        LogUtils.i(TAG, "result:$it")
    }

    private fun onRegisterCustomCropImage() {
        //根据图片uri 裁剪
        //  registerCustomCropImage.launch(CropImageResult(uri, 1, 1))
        onRegisterCustomTakePicture()
    }

}