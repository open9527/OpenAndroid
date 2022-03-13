package com.farmer.open9527.meeting.main.login

import android.text.TextUtils
import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.constant.RegexConstants
import com.blankj.utilcode.util.*
import com.farmer.open9527.meeting.R
import com.farmer.open9527.meeting.main.MainActivity
import com.farmer.open9527.meeting.main.mine.cell.MineLineCell
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.hjq.http.listener.OnHttpListener
import java.util.regex.Pattern

class LoginViewModel : ViewModel(), OnHttpListener<Any> {

    val valueTitle = ObservableField("登录")
    val valueShowBack = ObservableInt(View.INVISIBLE)

    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueAdapter =
        ObservableField<ListAdapter<BaseBindingCell<*>, BaseBindingCellViewHolder<ViewDataBinding>>>()

    val valueListData = ObservableArrayList<BaseBindingCell<*>>()

    val valueIPasswordLogin = ObservableField<PasswordLoginCell.IPasswordLogin>()
    val valueICodeLogin = ObservableField<CodeLoginCell.ICodeLogin>()

    init {
        valueListData.add(MineLineCell(SizeUtils.dp2px(1f), ColorUtils.getColor(R.color.transparent)))
    }

    fun requestPasswordData() {
        if (valueListData.size > 1) {
            valueListData[1] = PasswordLoginCell(valueIPasswordLogin.get()!!)
        } else {
            valueListData.add(PasswordLoginCell(valueIPasswordLogin.get()!!))
        }
    }

    fun requestCodeData() {
        valueListData[1] = CodeLoginCell(valueICodeLogin.get()!!)
    }

    fun requestLoginCode(mobile: String?, code: String?) {
        if (TextUtils.isEmpty(mobile)) {
            ToastUtils.showLong("请输入手机号!")
            return
        } else if (!Pattern.matches(RegexConstants.REGEX_MOBILE_SIMPLE, mobile)) {
            ToastUtils.showLong("请输入正确的手机号!")
            return
        } else if (TextUtils.isEmpty(code)) {
            ToastUtils.showLong("请输入验证码!")
            return
        }
        MainActivity.startMainActivity()
    }


    fun requestLoginPassword(mobile: String?, password: String?) {
        if (TextUtils.isEmpty(mobile)) {
            ToastUtils.showLong("请输入手机号!")
            return
        } else if (!Pattern.matches(RegexConstants.REGEX_MOBILE_SIMPLE, mobile)) {
            ToastUtils.showLong("请输入正确的手机号!")
            return
        } else if (TextUtils.isEmpty(password)) {
            ToastUtils.showLong("请输入密码!")
            return
        }
        MainActivity.startMainActivity()
    }


    override fun onSucceed(result: Any?) {
    }

    override fun onFail(e: Exception?) {
    }

    companion object {
        private val TAG = javaClass.simpleName
    }
}