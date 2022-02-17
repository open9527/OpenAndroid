package com.farmer.open9527.share.core.listenner

import androidx.annotation.CallSuper
import com.farmer.open9527.share.core.ShareLogCatUtil
import com.farmer.open9527.share.core.model.OAuthUserInfo

class LoginListener : IBaseListener {

    /**
     * @param accessToken 第三方给的一次性token，几分钟内会失效
     * @param uId         用户的id
     * @param expiresIn   过期时间
     * @param wholeData   第三方本身返回的全部数据
     */
    @CallSuper
    fun onReceiveToken(accessToken: String?, uId: String?, expiresIn: Long, wholeData: String?) {
        ShareLogCatUtil.i(TAG,"accessToken = $accessToken\nuserId = $uId\nexpires_in = $expiresIn")
    }

    /**
     * 得到第三方平台的用户信息
     *
     * {OAuthUserInfo}仅提供基础的信息,可根据需求添加
     */
    fun onReceiveUserInfo(userInfo: OAuthUserInfo) {
        ShareLogCatUtil.i( TAG,"userInfo= $userInfo")
        onComplete()
    }

    companion object {
        private const val TAG = "LoginListener"
    }
}