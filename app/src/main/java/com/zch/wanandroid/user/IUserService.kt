package com.zch.wanandroid.user

import com.zch.base.net.HttpResult
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by zch on 2019/02/20.
 */
interface IUserService {

    @POST("/user/register")
    @FormUrlEncoded
    fun register(@Field("username") username: String,
                 @Field("password") password: String,
                 @Field("repassword") repassword: String): Observable<HttpResult<LoginResp>>

    @POST("/user/login")
    @FormUrlEncoded
    fun login(@Field("username") username: String,
              @Field("password") password: String): Observable<HttpResult<LoginResp>>
}