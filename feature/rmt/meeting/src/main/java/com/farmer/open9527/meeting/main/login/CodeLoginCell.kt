package com.farmer.open9527.meeting.main.login

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.ToastUtils
import com.farmer.open9527.common.action.HandlerAction
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder

class CodeLoginCell : BaseBindingCell<CodeLoginCell> {

    var valueMobile = ObservableField<String>()
    var valuePassword = ObservableField<String>()

    var valueSelect = ObservableBoolean(false)

    var valueRes = ObservableInt(R.drawable.meeting_login_un_select__icon)
    var valueSelectRes = ObservableInt(R.drawable.meeting_login_select__icon)

    var valueCountdownStart = ObservableBoolean(false)
    var valueCountdownStop = ObservableBoolean(false)

    var valueICodeLogin = ObservableField<ICodeLogin>()

    constructor(
        iPasswordLogin: ICodeLogin
    ) : super(R.layout.meeting__login_code_cell) {
        valueICodeLogin.set(iPasswordLogin)
    }


    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, cell: CodeLoginCell) {
        when (view.id) {
            R.id.bt_get_code -> {
                valueCountdownStart.set(true)
            }
            R.id.btn_login -> {

                if (cell.valueSelect.get()) {
                    valueICodeLogin.get()?.login(cell.valueMobile.get(), cell.valuePassword.get())
                } else {
                    ToastUtils.showLong("请先认真阅读并同意以上协议内容。")
                }
            }
            R.id.iv_select -> {
                cell.valueSelect.set(!cell.valueSelect.get())
            }
            R.id.btn_switch -> {
                valueCountdownStop.set(true)
                valueICodeLogin.get()?.switch()
            }
        }
    }

    interface ICodeLogin {
        fun login(mobile: String?, code: String?)
        fun switch()
    }
}