package com.zch.wanandroid.user.register

import com.zch.base.rxlifecycle.IView
import com.zch.wanandroid.user.LoginResp

/**
 * Created by zch on 2019/02/20.
 */
interface IRegisterView : IView {
    fun onRegisterSuccess(loginResp: LoginResp?)

    fun onRegisterFail(errMsg: String?)
}