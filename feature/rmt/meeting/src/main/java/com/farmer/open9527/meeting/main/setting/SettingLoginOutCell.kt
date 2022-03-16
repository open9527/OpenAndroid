package com.farmer.open9527.meeting.main.setting

import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.SpanUtils
import com.blankj.utilcode.util.StringUtils
import com.farmer.open9527.meeting.BR
import com.farmer.open9527.meeting.R
import com.farmer.open9527.meeting.main.login.LoginActivity
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder

class SettingLoginOutCell : BaseBindingCell<SettingLoginOutCell> {

    constructor(

    ) : super(R.layout.meeting__setting_login_out__cell) {

    }

    override fun bindBaseBindingCellViewHolder(
        holder: BaseBindingCellViewHolder<ViewDataBinding>,
        position: Int
    ) {
        holder.addBindingParam(BR.cell, this)
    }

    override fun onCellClick(view: View, cell: SettingLoginOutCell) {
        SPUtils.getInstance().put("token", "")
        ActivityUtils.finishAllActivities()
        LoginActivity.startLoginActivity()
    }

}