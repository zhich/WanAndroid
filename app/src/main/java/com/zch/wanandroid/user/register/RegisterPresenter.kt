package com.zch.wanandroid.user.register

import com.zch.base.rx.sss
import com.zch.base.rxlifecycle.RxLifeProvider

/**
 * Created by zch on 2019/02/20.
 */
class RegisterPresenter(var view: RegisterContract.View) : RxLifeProvider(view)
        , RegisterContract.Presenter {

    override fun register(username: String, password: String, repassword: String) {
        RegisterModel().register(username, password, repassword).sss(view, {
            view.onRegisterSuccess(it.data)
        }, {
            view.onRegisterFail(it)
        })
    }
}