package com.zch.wanandroid

import com.alibaba.android.arouter.launcher.ARouter
import com.zch.base.BaseApp
import io.realm.Realm

/**
 * Created by zch on 2019/01/04.
 */
class App : BaseApp() {

    override fun onCreate() {
        super.onCreate()

        initARouter()
        Realm.init(this)
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}