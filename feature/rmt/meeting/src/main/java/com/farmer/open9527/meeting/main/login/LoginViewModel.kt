package com.farmer.open9527.meeting.main.login

import android.view.View
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.SizeUtils
import com.farmer.open9527.meeting.R
import com.farmer.open9527.meeting.main.mine.cell.MineLineCell
import com.farmer.open9527.recycleview.cell.BaseBindingCell
import com.farmer.open9527.recycleview.viewholder.BaseBindingCellViewHolder
import com.hjq.http.listener.OnHttpListener

class LoginViewModel : ViewModel(), OnHttpListener<Any> {

    val valueTitle = ObservableField("登录")
    val valueShowBack = ObservableInt(View.INVISIBLE)

    val valueLayoutManager = ObservableField<RecyclerView.LayoutManager>()
    val valueAdapter =
        ObservableField<ListAdapter<BaseBindingCell<*>, BaseBindingCellViewHolder<ViewDataBinding>>>()

    val valueListData = ObservableArrayList<BaseBindingCell<*>>()

    val valueIPasswordLogin = ObservableField<PasswordLoginCell.IPasswordLogin>()
    val valueICodeLogin = ObservableField<CodeLoginCell.ICodeLogin>()


    fun requestPasswordData() {
        valueListData.clear()
        valueListData.add(MineLineCell(SizeUtils.dp2px(50f), ColorUtils.getColor(R.color.transparent)))
        valueListData.add(PasswordLoginCell(valueIPasswordLogin.get()!!))
    }

    fun requestCodeData() {
        valueListData.clear()
        valueListData.add(MineLineCell(SizeUtils.dp2px(50f), ColorUtils.getColor(R.color.transparent)))
        valueListData.add(CodeLoginCell(valueICodeLogin.get()!!))
    }


    override fun onSucceed(result: Any?) {
    }

    override fun onFail(e: Exception?) {
    }

    companion object {
        private val TAG = javaClass.simpleName
    }
}