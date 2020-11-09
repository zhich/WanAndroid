package com.zch.wanandroid

import com.alibaba.android.arouter.launcher.ARouter
import com.zch.base.constant.ARouterPathConstant
import com.zch.common.base.BaseActivity

/**
 * Created by zch on 2020-11-09.
 */
class SplashActivity : BaseActivity() {

    override fun getLayoutResId() = R.layout.activity_splash

    override fun initView() {
    }

    override fun initData() {
        ARouter.getInstance().build(ARouterPathConstant.HomeTabs.MAIN_ACTIVITY).navigation()
        finish()
    }
}