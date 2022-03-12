package com.farmer.open9527.demo.dialog

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.animation.OvershootInterpolator
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.SizeUtils
import com.farmer.open9527.common.BR
import com.farmer.open9527.demo.R
import com.farmer.open9527.dialog.BaseDialogFragment
import com.farmer.open9527.dialog.DialogDataBindingConfig
import com.farmer.open9527.recycleview.adapter.BaseBindingCellListAdapter
import com.farmer.open9527.recycleview.animator.BaseAnimation
import com.farmer.open9527.recycleview.animator.ScaleInAnimation
import com.farmer.open9527.recycleview.animator.SlideInUpAnimator
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.decoration.GridSpaceItemDecoration
import com.farmer.open9527.recycleview.layoutmanager.WrapContentGridLayoutManager
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder

class ListDialog(context: Context) : BaseDialogFragment(context) {

    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueItemDecoration = ObservableField<RecyclerView.ItemDecoration>()
    val valueItemAnimator = ObservableField<RecyclerView.ItemAnimator>()
    val valueAnimation = ObservableField<BaseAnimation>()
//    val valueAdapter = ObservableField<BaseBindingCellListAdapter<BaseBindingCell<*>>>()
    val valueAdapter =  ObservableField<ListAdapter<BaseBindingCell<*>, BaseBindingCellViewHolder<ViewDataBinding>>>()

    val valueListData = ObservableArrayList<BaseBindingCell<*>>()


    override fun bindLayout(): Int {
        return R.layout.test__list__dialog
    }

    override fun getTheme(): Int {
        return R.style.CustomDialog
    }

    override fun setWindowStyle(window: Window) {
        super.setWindowStyle(window)
        val attributes = window.attributes
        attributes.gravity = Gravity.BOTTOM
        attributes.width = ScreenUtils.getAppScreenWidth()
        attributes.height = ScreenUtils.getScreenHeight() / 3
//        attributes.dimAmount = 0f
        window.attributes = attributes
        window.setWindowAnimations(R.style.BottomTransAlphaAcceAnimationStyle)
    }


    override fun getDataBindingConfig(): DialogDataBindingConfig {
        return DialogDataBindingConfig().addBindingParam(BR.dialog, this)
    }

    override fun initView(dialog: BaseDialogFragment, contentView: View) {
        super.initView(dialog, contentView)
        valueLayoutManager.set(WrapContentGridLayoutManager(context, 3))
        valueItemAnimator.set(SlideInUpAnimator(OvershootInterpolator(1f)))
        valueAnimation.set(ScaleInAnimation())
        valueItemDecoration.set(
            GridSpaceItemDecoration(SizeUtils.dp2px(10f), true)
        )
        valueAdapter.set(BaseBindingCellListAdapter())
    }


    fun setDataList(dataList: List<BaseBindingCell<*>>): ListDialog {
        valueListData.clear()
        valueListData.addAll(dataList)
        return this
    }


    fun showDialog(): ListDialog {
        show()
        return this
    }

    companion object {
        private fun newInstance(context: Context): ListDialog {
            return ListDialog(context)
        }

        fun with(context: Context): ListDialog {
            return newInstance(context)
        }
    }
}