package com.zch.wanandroid.home

import com.zch.base.net.HttpResult
import com.zch.base.net.NetWorkManager
import io.reactivex.Observable

/**
 * Created by zch on 2019/01/10.
 */
class HomeModel : HomeContract.Model {

    override fun fetchBanners(): Observable<HttpResult<MutableList<Banner>>> {
        return NetWorkManager
                .getRetrofit()
                .create(IHomeService::class.java)
                .fetchBanners()
    }

    override fun fetchArticles(pageNum: Int): Observable<HttpResult<ArticleResp>> {
        return NetWorkManager
                .getRetrofit()
                .create(IHomeService::class.java)
                .fetchArticles(pageNum)
    }
}