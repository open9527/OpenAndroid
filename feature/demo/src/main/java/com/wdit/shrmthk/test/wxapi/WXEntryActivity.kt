package com.wdit.shrmthk.test.wxapi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.farmer.open9527.share.WeChat
import com.farmer.open9527.share.core.ShareLogCatUtil
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler

class WXEntryActivity : Activity(), IWXAPIEventHandler {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            WeChat.getWeChatApi()?.handleIntent(intent, this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        WeChat.getWeChatApi()?.handleIntent(intent, this)
    }

    override fun onReq(req: BaseReq) {
        ShareLogCatUtil.i(TAG,"transaction=" + req.transaction)
        ShareLogCatUtil.i(TAG,"type=" + req.type)
        when (req.type) {
            ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX -> {

            }
            ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX -> {

            }
            else -> {
            }
        }
        finish()
    }

    override fun onResp(baseResp: BaseResp) {
        //ERR_OK = 0(用户同意) ERR_AUTH_DENIED = -4（用户拒绝授权） ERR_USER_CANCEL = -2（用户取消）
        var result = ""
        when (baseResp.errCode) {
            BaseResp.ErrCode.ERR_OK -> {
                result = "用户同意"
                when (baseResp.type) {
                    ConstantsAPI.COMMAND_SENDAUTH -> {
                        val authResp = baseResp as SendAuth.Resp
                        val code = authResp.code
                        WeChat.getAccessToken(code)
                    }
                    ConstantsAPI.COMMAND_LAUNCH_WX_MINIPROGRAM -> {
                        val launchMiniProResp = baseResp as WXLaunchMiniProgram.Resp
                        //对应小程序组件 <button open-type="launchApp"> 中的 app-parameter 属性
                        val extraData = launchMiniProResp.extMsg
                        ShareLogCatUtil.i(TAG,"extraData=$extraData")
                    }

                }
            }
            BaseResp.ErrCode.ERR_USER_CANCEL -> {
                result = "用户取消"
            }
            BaseResp.ErrCode.ERR_AUTH_DENIED -> {
                result = "用户拒绝授权"
            }
            BaseResp.ErrCode.ERR_UNSUPPORT -> {
                result = "不支持错误"
            }
            else -> {
                result = "发送返回"
            }
        }
        ShareLogCatUtil.i(TAG,"result=$result" + "  type=" + baseResp.type)
        finish()
    }

    companion object {
        private const val TAG = "WXEntryActivity"
    }
}