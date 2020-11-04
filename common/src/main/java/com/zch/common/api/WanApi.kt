package com.zch.common.api

import com.zch.common.bean.UserBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by zch on 2020-10-21.
 */
interface WanApi {

    @POST("/user/register")
    @FormUrlEncoded
    suspend fun register(@Field("username") username: String, @Field("password") password: String, @Field("repassword") repassword: String): HttpResult<UserBean>

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): HttpResult<UserBean>
}