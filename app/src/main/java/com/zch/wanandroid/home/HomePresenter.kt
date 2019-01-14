package com.zch.wanandroid.home

import com.zch.base.rx.ss
import com.zch.base.rx.sss
import com.zch.base.rxlifecycle.RxLifeProvider

/**
 * Created by zch on 2019/01/09.
 */
class HomePresenter(var homeView: HomeContract.View) : RxLifeProvider(homeView), HomeContract.Presenter {

    private val homeModel: HomeContract.Model = HomeModel()

    override fun fetchBanners() {
        homeModel.fetchBanners().ss({
            homeView.onFetchBannersSuccess(it.data)
        })
    }

    override fun fetchArticles(pageNum: Int) {
        homeModel.fetchArticles(pageNum).sss(homeView, {
            homeView.onFetchArticlesSuccess(it.data!!)
        })
    }
}