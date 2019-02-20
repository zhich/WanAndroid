package com.zch.wanandroid.user.login

import com.zch.base.rx.sss
import com.zch.base.rxlifecycle.RxLifeProvider

/**
 * Created by zch on 2019/02/20.
 */
class LoginPresenter(var view: LoginContract.View) : RxLifeProvider(view)
        , LoginContract.Presenter {

    override fun login(username: String, password: String) {
        LoginModel().login(username, password).sss(view, {
            view.onLoginSuccess(it.data)
        }, {
            it?.run {
                view.onLoginFail(this)
            }
        })
    }
}