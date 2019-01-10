package com.zch.wanandroid.home

import com.zch.base.net.HttpResult
import com.zch.base.rxlifecycle.IModel
import com.zch.base.rxlifecycle.IPresenter
import com.zch.base.rxlifecycle.IView
import io.reactivex.Observable

/**
 * Created by zch on 2019/01/09.
 */
interface HomeContract {

    interface View : IView {

        fun onFetchBannersSuccess(bannerList: MutableList<Banner>?)

        fun onFetchArticlesSuccess(articleResp: ArticleResp)
    }

    interface Presenter : IPresenter {

        fun fetchBanners()

        fun fetchArticles(pageNum: Int)
    }

    interface Model : IModel {

        fun fetchBanners(): Observable<HttpResult<MutableList<Banner>>>

        fun fetchArticles(pageNum: Int): Observable<HttpResult<ArticleResp>>
    }
}