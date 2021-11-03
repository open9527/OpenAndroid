package com.farmer.open9527.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity


/**
 *@author   open_9527
 *Create at 2021/11/3
 **/
abstract class BaseDialogFragment : DialogFragment, IBaseDialogFragment,
    DialogInterface.OnKeyListener {

    protected val TAG = javaClass.simpleName

    private var mActivity: FragmentActivity? = null


    constructor(context: Context) : super() {
        mActivity = DialogUtils.getFragmentActivity(context)
    }


    override fun onStart() {
        super.onStart()
        val mDialog = dialog
        if (mDialog != null) {
            val window = mDialog.window!!
            setWindowStyle(window)
            //  window.setWindowAnimations(id);
            //  window.getAttributes().windowAnimations;
        }
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (bindLayout() > 0) {
            val binding: ViewDataBinding =
                DataBindingUtil.inflate(inflater, bindLayout(), container, false)
            binding.lifecycleOwner = this
            return binding.root
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(this, view)
//        mContentView = view
        dialog?.setOnKeyListener(this)
    }


    override fun getTheme(): Int {
        if (bindTheme() > 0) {
            val theme = bindTheme()
            if (theme != View.NO_ID) {
                return theme
            }
        }
        return super.getTheme()
    }

    override fun initView(dialog: BaseDialogFragment, contentView: View) {
        val dataBindingConfig = getDataBindingConfig();
        if (dataBindingConfig != null && getDataBindingConfig()?.getBindingParams()?.size()!! > 0) {
            val dialogBinding = DataBindingUtil.getBinding<ViewDataBinding>(contentView)
            if (dialogBinding != null) {
                DialogUtils.bindingVariable(dialogBinding, dataBindingConfig.getBindingParams())
            }
        }

    }

    override fun onDetach() {
        super.onDetach()
        mActivity?.let { dialog?.let { it1 -> DialogUtils.closeSoftInput(it, it1) } }
    }


    override fun dismiss() {
        mActivity?.let { dialog?.let { it1 -> DialogUtils.closeSoftInput(it, it1) } }
        DialogUtils.runOnUiThread {
            if (DialogUtils.isActivityAlive(mActivity)) {
                super@BaseDialogFragment.dismissAllowingStateLoss()
            }
        }
    }

    override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            dismiss()
            return true
        }
        return false
    }

    open fun show() {
        show(javaClass.simpleName)
    }

    open fun show(tag: String?) {
        DialogUtils.runOnUiThread {
            if (DialogUtils.isActivityAlive(mActivity)) {
                val fm = mActivity!!.supportFragmentManager
                val prev = fm.findFragmentByTag(tag)
                if (prev != null) {
                    fm.beginTransaction().remove(prev)
                }
                super@BaseDialogFragment.show(fm, tag)
            }
        }
    }

    open fun setCanceledOnTouchOutside(cancel: Boolean) {
        val mDialog = dialog
        mDialog?.setCanceledOnTouchOutside(cancel)
    }
}