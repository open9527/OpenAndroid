package com.farmer.open9527.base.page

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 *@author   open_9527
 *Create at 2021/5/18
 **/
abstract class DataBindingActivity : AppCompatActivity() {

    private var mBinding: ViewDataBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        val dataBindingConfig = getDataBindingConfig()
        if (dataBindingConfig != null) {
            val binding = DataBindingUtil.setContentView<ViewDataBinding>(
                this, dataBindingConfig.layout
            )

            binding?.lifecycleOwner = this

            binding?.setVariable(dataBindingConfig.vmVariableId, dataBindingConfig.stateViewModel)

            BindingVariableUtils.bindingVariable(binding, dataBindingConfig.getBindingParams())

            this.mBinding = binding
        }
    }

    open fun initViewModel() {

    }

    open fun getDataBindingConfig(): DataBindingConfig? {

        return null
    }


    //警惕使用。非必要情况下，尽可能不在子类中拿到 binding 实例乃至获取 view 实例
    protected open fun getBinding(): ViewDataBinding? {
        return mBinding
    }


    override fun onDestroy() {
        super.onDestroy()
        mBinding?.unbind()
        mBinding = null
    }
}