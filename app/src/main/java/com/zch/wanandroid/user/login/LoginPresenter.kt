package com.zch.wanandroid.user.login

import com.zch.base.cache.LoginCache
import com.zch.base.db.User
import com.zch.base.db.UserRepo
import com.zch.base.rx.sss
import com.zch.base.rxlifecycle.RxLifeProvider
import com.zch.base.utils.JsonUtil

/**
 * Created by zch on 2019/02/20.
 */
class LoginPresenter(var view: LoginContract.View) : RxLifeProvider(view)
        , LoginContract.Presenter {

    override fun login(username: String, password: String) {
        LoginModel().login(username, password).sss(view, { result ->
            val user: User = result.data?.let {
                User(
                        account = it.username,
                        id = it.id,
                        password = it.password,
                        icon = it.icon,
                        email = it.email,
                        token = it.token,
                        type = it.type,
                        collectIds = JsonUtil.toJson(it.collectIds),
                        chapterTops = JsonUtil.toJson(it.chapterTops)
                )
            }!!
            UserRepo.insertOrUpdateUser(user)
            LoginCache.setCurrentAccount(user.account!!)
            LoginCache.setIsLogin(true)

            view.onLoginSuccess(result.data)
        }, {
            it?.run {
                view.onLoginFail(this)
            }
        })
    }
}