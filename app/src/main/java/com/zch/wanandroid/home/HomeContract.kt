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

        fun onFetchBannerListSuccess(bannerList: MutableList<Banner>?)
    }

    interface Presenter : IPresenter {

        fun fetchBannerList()
    }

    interface Model : IModel {

        fun fetchBannerList(): Observable<HttpResult<MutableList<Banner>>>
    }
}