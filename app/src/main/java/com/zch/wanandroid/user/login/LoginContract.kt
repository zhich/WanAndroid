package com.zch.wanandroid.user.login

import com.zch.base.net.HttpResult
import com.zch.base.rxlifecycle.IModel
import com.zch.base.rxlifecycle.IPresenter
import com.zch.base.rxlifecycle.IView
import com.zch.wanandroid.user.LoginResp
import io.reactivex.Observable

/**
 * Created by zch on 2019/02/20.
 */
interface LoginContract {

    interface View : IView {
        fun onLoginSuccess(loginResp: LoginResp?)

        fun onLoginFail(errMsg: String)
    }

    interface Presenter : IPresenter {
        fun login(username: String, password: String)
    }

    interface Model : IModel {
        fun login(username: String, password: String): Observable<HttpResult<LoginResp>>
    }
}