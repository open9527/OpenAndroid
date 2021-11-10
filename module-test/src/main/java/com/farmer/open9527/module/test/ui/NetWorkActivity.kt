package com.farmer.open9527.module.test.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.decode.SvgDecoder
import coil.decode.VideoFrameDecoder
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SizeUtils
import com.farmer.open9527.base.page.DataBindingConfig
import com.farmer.open9527.common.base.CommonActivity
import com.farmer.open9527.module.test.BR
import com.farmer.open9527.module.test.R
import com.farmer.open9527.module.test.viewmodel.NetWorkViewModel
import com.farmer.open9527.recycleview.decoration.SpacesItemDecoration
import com.farmer.open9527.recycleview.layoutmanager.WrapContentLinearLayoutManager


/**
 *@author   open_9527
 *Create at 2021/10/18
 **/
class NetWorkActivity : CommonActivity() {

    private var mViewModel: NetWorkViewModel? = null


    override fun initViewModel() {
        mViewModel = getActivityScopeViewModel(NetWorkViewModel::class.java);

    }

//    private val mViewModel by viewModels<NetWorkViewModel>()


    override fun getDataBindingConfig(): DataBindingConfig {
        return DataBindingConfig(R.layout.net_work__activity, BR.vm, mViewModel!!)
            .addBindingParam(BR.click, ClickProxy())
    }


    override fun initEvent() {
        mViewModel?.userLiveData?.observeState(this) {
            onSuccess {
                LogUtils.i(TAG, "userLiveData: onSuccess")
//                LogUtils.i(TAG, "userLiveData: " + it.toString())
                mViewModel?.fetchWxArticleFromNet()
            }
        }
        mViewModel?.wxArticleLiveData?.observeState(this) {
            onSuccess {
                LogUtils.i(TAG, "wxArticleLiveData: onSuccess")
//                LogUtils.i(TAG, "wxArticleLiveData: " + it.toString())
            }

        }
        mViewModel?.initRecycleView(
            WrapContentLinearLayoutManager(this),
            SpacesItemDecoration(this, RecyclerView.VERTICAL)
        )
    }

    override fun initView(bundle: Bundle?) {
        super.initView(bundle)
        val gif = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fdingyue.ws.126.net%2F2021%2F0830%2Fd1374a0eg00qyn7c20085c000dw0099c.gif&refer=http%3A%2F%2Fdingyue.ws.126.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639019404&t=90c9aa1a4375097ce433cf258c9443a8"
        val url = "https://t7.baidu.com/it/u=2807573989,921590260&fm=193&f=GIF"
        val video = "https://hp.storage.shmedia.tech/e68c4734b7024514b8e7f3bee7fa4106.mp4"
        val videoFrames = "https://hp.storage.shmedia.tech/e68c4734b7024514b8e7f3bee7fa4106.mp4?vframe/jpg/offset/0"
        val imageView = findViewById<ImageView>(R.id.iv_pic)
        val imageView1 = findViewById<ImageView>(R.id.iv_pic_1)

        val imageLoader = ImageLoader.Builder(this)
            .componentRegistry {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    add(ImageDecoderDecoder(this@NetWorkActivity))
                } else {
                    add(GifDecoder())
                }
//                add(SvgDecoder(this@NetWorkActivity))
//                add(VideoFrameDecoder(this@NetWorkActivity))
            }
            .build()

        imageView.load(gif,imageLoader) {
//            crossfade(true)
//            transformations(CircleCropTransformation())
            //            placeholder(R.drawable.image)
//            transformations(GrayscaleTransformation(),RoundedCornersTransformation(SizeUtils.dp2px(10f).toFloat()))
//            transformations(GrayscaleTransformation())
        }

        imageView1.load(videoFrames,imageLoader) {
            crossfade(true)
//            transformations(CircleCropTransformation())
//            placeholder(R.drawable.image)
            transformations(GrayscaleTransformation(),CircleCropTransformation())
        }
    }


    inner class ClickProxy {
        var requestClick = View.OnClickListener {
            LogUtils.i(TAG, "requestClick")
            mViewModel?.fetchWxArticleFromNet();
        }

        var loginClick = View.OnClickListener {
            LogUtils.i(TAG, "loginClick")
//            mViewModel?.login("open_9527", "kuaige930817")
            val intent = Intent()
            intent.putExtra("data", "data from NetWorkActivity")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}