package com.farmer.open9527.rmt.export.binding.banner

import android.view.View
import androidx.databinding.BindingAdapter
import com.farmer.open9527.rmt.export.http.vo.sys.BillboardVo
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.BaseBannerAdapter

object BannerBindingAdapter {
    @BindingAdapter(
        value = [
            "bindBillboardBannerAdapter",
            "bindBillboardBannerData",
        ], requireAll = false
    )
    @JvmStatic
    fun setBindingBillboardBanner(
        banner: BannerViewPager<BillboardVo>?,
        bannerAdapter: BaseBannerAdapter<BillboardVo>?,
        bannerData: List<BillboardVo>?
    ) {
        if (bannerData == null) return
        banner?.run {
            adapter = bannerAdapter
            setScrollDuration(1000)
            setIndicatorVisibility(View.GONE)
            setAutoPlay(true)
            create(bannerData)
        }
    }
}
