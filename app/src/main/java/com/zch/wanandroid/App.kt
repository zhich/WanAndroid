package com.zch.wanandroid

import com.alibaba.android.arouter.launcher.ARouter
import com.zch.base.BaseApp
import com.zch.wanandroid.di.appModule
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by zch on 2019/01/04.
 */
class App : BaseApp() {

    override fun onCreate() {
        super.onCreate()

        initARouter()
        Realm.init(this)

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }

    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}