package com.zch.wanandroid.home

import com.zch.base.rx.ss
import com.zch.base.rxlifecycle.RxLifeProvider

/**
 * Created by zch on 2019/01/09.
 */
class HomePresenter(var homeView: HomeContract.View) : RxLifeProvider(homeView), HomeContract.Presenter {

    private val homeModel: HomeContract.Model = HomeModel()

    override fun fetchBannerList() {
        homeModel.fetchBannerList().ss({
            homeView.onFetchBannerListSuccess(it.data)
        })
    }
}