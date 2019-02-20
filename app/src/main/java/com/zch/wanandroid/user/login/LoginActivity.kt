package com.zch.wanandroid.user.login

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zch.base.constant.ARouterPathConstant
import com.zch.base.rxlifecycle.RxLifecycleActivity
import com.zch.base.utils.ToastUtil
import com.zch.wanandroid.R
import com.zch.wanandroid.user.LoginResp
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by zch on 2019/01/16.
 */
@Route(path = ARouterPathConstant.User.LOGIN_ACTIVITY)
class LoginActivity : RxLifecycleActivity(), LoginContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            //            ARouter.getInstance().build(ARouterPathConstant.Main.MAIN_ACTIVITY).navigation()
//            finish()
            if (checkValidity()) {
                LoginPresenter(this).login(viewAccount.getContent(), viewPwd.getContent())
            }
        }

        tvGoToRegister.setOnClickListener {
            ARouter.getInstance().build(ARouterPathConstant.User.REGISTER_ACTIVITY).navigation()
        }
    }

    private fun checkValidity(): Boolean {
        if (viewAccount.getContent().isEmpty()) {
            ToastUtil.showShortText(getString(R.string.account_can_not_empty))
            return false
        }
        if (viewPwd.getContent().isEmpty()) {
            ToastUtil.showShortText(getString(R.string.pwd_can_not_empty))
            return false
        }
        return true
    }

    override fun onLoginSuccess(loginResp: LoginResp?) {
        ToastUtil.showShortText(getString(R.string.login_success_hint))
    }

    override fun onLoginFail(errMsg: String) {
        ToastUtil.showShortText(errMsg)
    }
}