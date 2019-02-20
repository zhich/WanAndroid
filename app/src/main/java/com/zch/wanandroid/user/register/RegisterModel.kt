package com.zch.wanandroid.user.register

import com.zch.base.net.HttpResult
import com.zch.base.net.NetWorkManager
import com.zch.wanandroid.user.IUserService
import com.zch.wanandroid.user.LoginResp
import io.reactivex.Observable

/**
 * Created by zch on 2019/02/20.
 */
class RegisterModel : RegisterContract.Model {

    override fun register(username: String, password: String, repassword: String): Observable<HttpResult<LoginResp>> {
        return NetWorkManager
                .getRetrofit()
                .create(IUserService::class.java)
                .register(username, password, repassword)
    }
}