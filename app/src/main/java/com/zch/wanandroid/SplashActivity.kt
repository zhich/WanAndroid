package com.zch.wanandroid

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.zch.base.constant.ARouterPathConstant
import com.zch.base.rxlifecycle.RxLifecycleActivity

/**
 * Created by zch on 2019/02/20.
 */
class SplashActivity : RxLifecycleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        ARouter.getInstance().build(ARouterPathConstant.Main.MAIN_ACTIVITY).navigation()
        finish()
    }
}