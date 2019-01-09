package com.zch.wanandroid.home

import com.zch.base.rxlifecycle.RxLifeProvider

/**
 * Created by zch on 2019/01/09.
 */
class HomePresenter(var homeView: HomeContract.View) : RxLifeProvider(homeView), HomeContract.Presenter {

    override fun fetchBanner() {

    }
}