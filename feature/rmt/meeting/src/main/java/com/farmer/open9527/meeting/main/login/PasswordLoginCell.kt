package com.farmer.open9527.meeting.main.login

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.ToastUtils
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder

class PasswordLoginCell : BaseBindingCell<PasswordLoginCell> {

    var valueMobile = ObservableField<String>()
    var valuePassword = ObservableField<String>()

    var valueSelect = ObservableBoolean(false)
    var valueRes = ObservableInt(R.drawable.meeting_login_un_select__icon)
    var valueSelectRes = ObservableInt(R.drawable.meeting_login_select__icon)


    var valueShowPassword = ObservableBoolean(false)
    var valueHidePasswordRes = ObservableInt(R.drawable.meeting_login_hide_password__icon)
    var valueShowPasswordRes = ObservableInt(R.drawable.meeting_login_show_password__icon)

    var valueIPasswordLogin = ObservableField<IPasswordLogin>()

    constructor(
        iPasswordLogin: IPasswordLogin
    ) : super(R.layout.meeting__login_password_cell) {
        valueIPasswordLogin.set(iPasswordLogin)
    }


    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, cell: PasswordLoginCell) {
        when (view.id) {
            R.id.iv_password -> {
                cell.valueShowPassword.set(!cell.valueShowPassword.get())
            }
            R.id.btn_login -> {

                if (cell.valueSelect.get()) {
                    valueIPasswordLogin.get()?.login(cell.valueMobile.get(), cell.valuePassword.get())
                } else {
                    ToastUtils.showLong("请先认真阅读并同意以上协议内容。")
                }
            }
            R.id.iv_select -> {
                cell.valueSelect.set(!cell.valueSelect.get())

            }
            R.id.btn_switch -> {
                valueIPasswordLogin.get()?.switch()
            }
        }
    }

    interface IPasswordLogin {
        fun login(mobile: String?, password: String?)
        fun switch()
    }
}