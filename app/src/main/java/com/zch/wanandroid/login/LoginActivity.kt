package com.zch.wanandroid.login

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.zch.base.constant.ARouterPathConstant
import com.zch.base.rxlifecycle.RxLifecycleActivity
import com.zch.wanandroid.R

/**
 * Created by zch on 2019/01/16.
 */
@Route(path = ARouterPathConstant.Login.LOGIN_ACTIVITY)
class LoginActivity : RxLifecycleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}