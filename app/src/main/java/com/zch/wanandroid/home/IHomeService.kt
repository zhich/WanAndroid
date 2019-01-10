package com.zch.wanandroid.home

import com.zch.base.net.HttpResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by zch on 2019/01/05.
 */
interface IHomeService {

    @GET("banner/json")
    fun fetchBanners(): Observable<HttpResult<MutableList<Banner>>>

    @GET("/article/list/{pageNum}/json")
    fun fetchArticles(@Path("pageNum") pageNum: Int): Observable<HttpResult<ArticleResp>>
}