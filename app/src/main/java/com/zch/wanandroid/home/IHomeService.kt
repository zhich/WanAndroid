package com.zch.wanandroid.home

import com.zch.base.net.Result
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by zch on 2019/01/05.
 */
interface IHomeService {

    @GET("/article/list/{pageNum}/json")
    fun getArticles(@Path("pageNum") pageNum: Int): Observable<Result<ArticleResp>>
}