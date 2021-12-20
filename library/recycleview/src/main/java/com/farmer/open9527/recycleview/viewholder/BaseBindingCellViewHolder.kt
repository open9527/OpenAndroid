package com.farmer.open9527.recycleview.viewholder

import androidx.databinding.ViewDataBinding


/**
 *@author   open_9527
 *Create at 2021/11/4
 **/
class BaseBindingCellViewHolder<BINDING : ViewDataBinding> : BaseCellViewHolder {

    private var mBinding: BINDING? = null

    constructor(binding: BINDING) : super(binding.root) {
        mBinding = binding;
    }


    fun addBindingParam(variableId: Int, `object`: Any) {
        mBinding?.setVariable(variableId, `object`)
    }

    fun getBinding(): ViewDataBinding? {
        return mBinding
    }

}