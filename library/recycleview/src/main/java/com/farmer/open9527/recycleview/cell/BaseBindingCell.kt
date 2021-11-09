package com.farmer.open9527.recycleview.cell

import android.util.SparseArray
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.farmer.open9527.recycleview.click.IBaseCellClick
import java.util.*


/**
 *@author   open_9527
 *Create at 2021/11/4
 **/
abstract class BaseBindingCell<CELL : BaseBindingCell<CELL>> : IBaseCellClick<CELL> {

    protected val TAG = (this as CELL).javaClass.simpleName

    private var mViewType = 0

    constructor(@LayoutRes layoutId: Int) {
        mViewType = getViewTypeByLayoutId(layoutId)
        LAYOUT_SPARSE_ARRAY.put(mViewType, layoutId)
    }

    constructor(view: View) {
        mViewType = getViewTypeByView(view)
        VIEW_SPARSE_ARRAY.put(mViewType, view)
    }


    override fun onClick(v: View) {
        onCellClick(v, this as CELL)
    }

    override fun onLongClick(v: View): Boolean {
        return onCellLongClick(v, this as CELL)
    }

//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as BaseBindingCell<*>
//
//        if (TAG != other.TAG) return false
//        if (mViewType != other.mViewType) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        var result = TAG.hashCode() ?: 0
//        result = 31 * result + mViewType
//        return result
//    }

    /* -------------------------------------------------------------------------------------------- */

    abstract fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    )


    companion object {
        private val LAYOUT_SPARSE_ARRAY = SparseIntArray()
        private val VIEW_SPARSE_ARRAY = SparseArray<View>()

        fun onCreateBaseBindingCellViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BaseBindingCellViewHolder<ViewDataBinding> {
            val layoutByType: Int = LAYOUT_SPARSE_ARRAY.get(viewType, -1)
            if (layoutByType != -1) {
                return BaseBindingCellViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        layoutByType,
                        parent,
                        false
                    )
                )
            }
            val viewByType: View = VIEW_SPARSE_ARRAY.get(viewType)
            val viewDataBinding = DataBindingUtil.bind<ViewDataBinding>(viewByType)
            if (viewDataBinding != null) {
                return BaseBindingCellViewHolder(viewDataBinding)
            }
            throw RuntimeException("onCreateViewHolder: get holder from view type failed.")
        }
    }

    open fun onViewRecycled(holder: BaseBindingCellViewHolder<ViewDataBinding>, position: Int) {

    }

    open fun bindViewHolder(holder: BaseBindingCellViewHolder<ViewDataBinding>, position: Int) {
        bindBaseBindingCellViewHolder(holder, position)
    }


    fun getViewType(): Int {
        return mViewType
    }

    fun isViewType(@LayoutRes layoutId: Int): Boolean {
        return mViewType == getViewTypeByLayoutId(layoutId)
    }

    fun isViewType(view: View): Boolean {
        return mViewType == getViewTypeByView(view)
    }


    private fun getViewTypeByView(view: View): Int {
        return view.hashCode() + javaClass.hashCode()
    }

    private fun getViewTypeByLayoutId(@LayoutRes layoutId: Int): Int {
        return layoutId + javaClass.hashCode()
    }


    fun getItemId(): Long {
        return RecyclerView.NO_ID
    }

    fun getUUID(): String {
        return UUID.randomUUID().toString()
    }

}