package com.zch.base

import android.app.Application

/**
 * Created by zch on 2019/01/04.
 */
open class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        lateinit var instance: BaseApp
            protected set
    }
}
