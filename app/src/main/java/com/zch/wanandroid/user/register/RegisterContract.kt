package com.zch.wanandroid.user.register

import com.zch.base.net.HttpResult
import com.zch.base.rxlifecycle.IModel
import com.zch.base.rxlifecycle.IPresenter
import com.zch.base.rxlifecycle.IView
import com.zch.wanandroid.user.LoginResp
import io.reactivex.Observable

/**
 * Created by zch on 2019/02/20.
 */
interface RegisterContract {

    interface View : IView {
        fun onRegisterSuccess(loginResp: LoginResp?)

        fun onRegisterFail(errMsg: String?)
    }

    interface Presenter : IPresenter {
        fun register(username: String, password: String, repassword: String)
    }

    interface Model : IModel {
        fun register(username: String, password: String, repassword: String): Observable<HttpResult<LoginResp>>
    }
}