package com.zch.wanandroid.user.login

import com.zch.base.net.HttpResult
import com.zch.base.net.NetWorkManager
import com.zch.wanandroid.user.IUserService
import com.zch.wanandroid.user.LoginResp
import io.reactivex.Observable

/**
 * Created by zch on 2019/02/20.
 */
class LoginModel : LoginContract.Model {

    override fun login(username: String, password: String): Observable<HttpResult<LoginResp>> {
        return NetWorkManager
                .getRetrofit()
                .create(IUserService::class.java)
                .login(username, password)
    }
}