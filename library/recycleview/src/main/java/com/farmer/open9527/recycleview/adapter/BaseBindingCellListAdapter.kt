package com.farmer.open9527.recycleview.adapter

import android.animation.Animator
import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.*
import com.farmer.open9527.recycleview.animator.*
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.cell.BaseBindingCell.Companion.onCreateBaseBindingCellViewHolder
import com.farmer.open9527.recycleview.diffutilcallbacks.DiffUtilCallbacks
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder


/**
 *@author   open_9527
 *Create at 2021/11/4
 **/
open class BaseBindingCellListAdapter<CELL : BaseBindingCell<*>> :
    ListAdapter<CELL, BaseBindingCellViewHolder<ViewDataBinding>> {

    constructor() : super(DiffUtilCallbacks<CELL>().getCellItemCallback())

    constructor(diffCallback: DiffUtil.ItemCallback<CELL>) : super(diffCallback)

    constructor(config: AsyncDifferConfig<CELL>) : super(config)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseBindingCellViewHolder<ViewDataBinding> {
        return onCreateBaseBindingCellViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        currentList[position].bindViewHolder(holder, position)

    }


    override fun getItemViewType(position: Int): Int {
        val cell = currentList[position]
        return cell.getViewType()
    }

    override fun getItemId(position: Int): Long {
        return currentList[position].getItemId()
    }

    override fun onViewRecycled(holder: BaseBindingCellViewHolder<ViewDataBinding>) {
        super.onViewRecycled(holder)
        val position: Int = holder.bindingAdapterPosition
        if (position < 0 || position >= currentList.size) {
            return
        }
        currentList[position].onViewRecycled(holder, position)
    }

    override fun getCurrentList(): List<CELL> {
        return super.getCurrentList()
    }



    override fun onViewAttachedToWindow(holder: BaseBindingCellViewHolder<ViewDataBinding>) {
        super.onViewAttachedToWindow(holder)
//        val type = holder.itemViewType
        addAnimation(holder)
    }
    /*************************** Animation ******************************************/

    var animationEnable: Boolean = false
    var adapterAnimation: BaseAnimation? = null
        set(value) {
            animationEnable = true
            field = value
        }

    private var mLastPosition = -1
    private fun addAnimation(holder: RecyclerView.ViewHolder) {
        if (animationEnable) {
            if (holder.layoutPosition > mLastPosition) {
                val animation: BaseAnimation = adapterAnimation ?: AlphaInAnimation()
                animation.animators(holder.itemView).forEach {
                    startAnim(it, holder.layoutPosition)
                }
                mLastPosition = holder.layoutPosition
            }
        }
    }

    /**
     * 开始执行动画方法
     * 可以重写此方法，实行更多行为
     *
     * @param anim
     * @param index
     */
    protected open fun startAnim(anim: Animator, index: Int) {
        anim.start()
    }

    /**
     * 内置默认动画类型
     */
    enum class AnimationType {
        AlphaIn, ScaleIn, SlideInBottom, SlideInLeft, SlideInRight
    }

    /**
     * 使用内置默认动画设置
     * @param animationType AnimationType
     */
    fun setAnimationWithDefault(animationType: AnimationType) {
        adapterAnimation = when (animationType) {
            AnimationType.AlphaIn -> AlphaInAnimation()
            AnimationType.ScaleIn -> ScaleInAnimation()
            AnimationType.SlideInBottom -> SlideInBottomAnimation()
            AnimationType.SlideInLeft -> SlideInLeftAnimation()
            AnimationType.SlideInRight -> SlideInRightAnimation()
        }
    }

    /*************************** Animation ******************************************/
}