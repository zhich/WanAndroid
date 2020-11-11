package com.zch.common.api

import com.zch.common.BannerBean
import com.zch.common.UserBean
import com.zch.module.article.ArticleResp
import retrofit2.http.*

/**
 * Created by zch on 2020-10-21.
 */
interface WanApi {

    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(@Field("username") username: String, @Field("password") password: String, @Field("repassword") repassword: String): HttpResult<UserBean>

    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): HttpResult<UserBean>

    @GET("/banner/json")
    suspend fun fetchBanner(): HttpResult<List<BannerBean>>

    @GET("/article/list/{page}/json")
    suspend fun fetchHomeArticles(@Path("page") page: Int): HttpResult<ArticleResp>
}