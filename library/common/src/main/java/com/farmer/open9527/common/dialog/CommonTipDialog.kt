package com.farmer.open9527.common.dialog

import android.content.Context
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.View
import android.view.Window
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat
import androidx.databinding.ObservableInt
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.SizeUtils
import com.farmer.open9527.common.R
import com.farmer.open9527.common.BR
import com.farmer.open9527.dialog.BaseDialogFragment
import com.farmer.open9527.dialog.DialogDataBindingConfig


/**
 *@author   open_9527
 *Create at 2021/11/3
 **/
class CommonTipDialog(context: Context) : BaseDialogFragment(context) {

    private val valueICommonTipDialog: ObservableField<ICommonTipDialog> = ObservableField()
    private val valueLayout: ObservableInt = ObservableInt(R.layout.dialog_common_tip)
    private val valueHeight = ObservableInt(SizeUtils.dp2px(160f))
    private val valueWidthSpacing = ObservableInt(SizeUtils.dp2px(60f))
    private val valueDimAmount = ObservableFloat(1f)
    private val valueWindowAnimations = ObservableInt(R.style.BottomTransAlphaAcceAnimationStyle)
    val valueTitle = ObservableField<String>()
    val valueContent = ObservableField<CharSequence>()
    val valueCancelContent: ObservableField<String> =
        ObservableField(getString(R.string.common_cancel))
    val valueConfirmContent: ObservableField<String?> =
        ObservableField(getString(R.string.common_confirm))
    val valueCancelTextColor = ObservableInt(ContextCompat.getColor(context, R.color.common_accent_color))
    val valueConfirmTextColor = ObservableInt(ContextCompat.getColor(context, R.color.common_text_hint_color))
    val valueOnlyConfirm = ObservableInt(View.VISIBLE)


    override fun bindLayout(): Int {
        return valueLayout.get()
    }

    override fun getTheme(): Int {
        return R.style.CustomDialog
    }

    override fun setWindowStyle(window: Window) {
        val attributes = window.attributes
        attributes.gravity = Gravity.CENTER
        attributes.width = ScreenUtils.getAppScreenWidth() - valueWidthSpacing.get()
        attributes.height = valueHeight.get()
        attributes.dimAmount = valueDimAmount.get()
        window.attributes = attributes
        window.setWindowAnimations(valueWindowAnimations.get())
    }

    override fun getDataBindingConfig(): DialogDataBindingConfig {
        return DialogDataBindingConfig().addBindingParam(BR.dialog, this)
    }

    override fun initView(dialog: BaseDialogFragment, contentView: View) {
        super.initView(dialog, contentView)
        isCancelable = true
        setCanceledOnTouchOutside(true)
    }

    var confirmClick = View.OnClickListener { v: View? ->
        val iCommonTipDialog = valueICommonTipDialog.get()
        if (iCommonTipDialog != null) {
            iCommonTipDialog.onConfirm()
            dismiss()
        }
    }

    var cancelClick = View.OnClickListener { v: View? ->
        val iCommonTipDialog = valueICommonTipDialog.get()
        if (iCommonTipDialog != null) {
            iCommonTipDialog.onCancel()
            dismiss()
        }
    }


    fun setLayout(layout: Int): CommonTipDialog {
        valueLayout.set(layout)
        return this
    }

    fun setWindowAnimations(@StyleRes animations: Int): CommonTipDialog {
        valueWindowAnimations.set(animations)
        return this
    }

    fun setDimAmount(dimAmount: Float): CommonTipDialog {
        valueDimAmount.set(dimAmount)
        return this
    }

    fun setHeight(height: Int): CommonTipDialog {
        valueHeight.set(height)
        return this
    }

    fun setTitle(title: String?): CommonTipDialog {
        valueTitle.set(title)
        return this
    }

    fun setContent(content: String?): CommonTipDialog {
        valueContent.set(content)
        return this
    }

    fun setSpanContent(
        startContent: String,
        content: String,
        endContent: String,
        @ColorInt contentColor: Int
    ): CommonTipDialog {
        val spannableString = SpannableString(startContent + content + endContent)
        spannableString.setSpan(
            ForegroundColorSpan(contentColor),
            startContent.length,
            startContent.length + content.length,
            Spanned.SPAN_INCLUSIVE_EXCLUSIVE
        )
        valueContent.set(content)
        return this
    }

    fun setConfirmContent(content: String?): CommonTipDialog {
        valueConfirmContent.set(content)
        return this
    }

    fun setCancelContent(content: String?): CommonTipDialog {
        valueCancelContent.set(content)
        return this
    }

    fun setConfirmTextColor(@ColorInt color: Int): CommonTipDialog {
        valueConfirmTextColor.set(color)
        return this
    }

    fun setCancelTextColor(@ColorInt color: Int): CommonTipDialog {
        valueCancelTextColor.set(color)
        return this
    }

    fun setOnlyConfirm(onlyConfirm: Boolean): CommonTipDialog {
        valueOnlyConfirm.set(if (onlyConfirm) View.GONE else View.VISIBLE)
        return this
    }

    fun addListener(iCommonTipDialog: ICommonTipDialog?): CommonTipDialog {
        valueICommonTipDialog.set(iCommonTipDialog)
        return this
    }

    fun showDialog(): CommonTipDialog {
        show()
        return this
    }

    companion object {
        private fun newInstance(context: Context): CommonTipDialog {
            return CommonTipDialog(context)
        }

        fun with(context: Context): CommonTipDialog {
            return newInstance(context)
        }
    }

    interface ICommonTipDialog {
        fun onConfirm() {}
        fun onCancel() {}
    }

}