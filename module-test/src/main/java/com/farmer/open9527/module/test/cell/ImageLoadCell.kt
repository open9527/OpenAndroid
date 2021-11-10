package com.farmer.open9527.module.test.cell

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.ColorUtils
import com.farmer.open9527.module.test.R
import com.farmer.open9527.module.test.BR
import com.farmer.open9527.module.test.vo.image.GifsVo
import com.farmer.open9527.module.test.vo.image.ImageVo
import com.farmer.open9527.module.test.vo.image.JpgVo
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder


/**
 *@author   open_9527
 *Create at 2021/11/8
 **/
class ImageLoadCell : BaseBindingCell<ImageLoadCell> {
    val valueUrl = ObservableField<String>()
    val valueWidth = ObservableField<Int>()
    val valueHeight = ObservableField<Int>()
    val valuePlaceholderDrawable =
        ObservableField<Drawable>(ColorDrawable(ColorUtils.getColor(R.color.common_line_color)))
    private val valueIImageLoadCell = ObservableField<IImageLoadCell>()

    constructor(
        imageVo: ImageVo

    ) : super(R.layout.image_load__cell) {
        if (imageVo is JpgVo) {
            valueUrl.set(imageVo.url?.regular)

        } else if (imageVo is GifsVo) {
            valueUrl.set(imageVo.url)
        }
        valueWidth.set(imageVo.width)
        valueHeight.set(imageVo.height)
//        valueIImageLoadCell.set(iImageLoadCell)
    }

    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, cell: ImageLoadCell) {
        val iImageLoadCell = valueIImageLoadCell.get()
        iImageLoadCell?.registerCallBack(cell.valueUrl.get())
    }

    interface IImageLoadCell {
        fun registerCallBack(url: String?) {}
    }
}