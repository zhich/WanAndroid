package com.zch.wanandroid.user.register

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.zch.base.constant.ARouterPathConstant
import com.zch.base.rxlifecycle.RxLifecycleActivity
import com.zch.base.utils.ToastUtil
import com.zch.wanandroid.R
import com.zch.wanandroid.user.LoginResp
import kotlinx.android.synthetic.main.activity_register.*

/**
 * Created by zch on 2019/02/20.
 */
@Route(path = ARouterPathConstant.User.REGISTER_ACTIVITY)
class RegisterActivity : RxLifecycleActivity(), RegisterContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {
            if (checkValidity()) {
                RegisterPresenter(this).register(viewAccount.getContent()
                        , viewPwd.getContent(), viewRePwd.getContent())
            }
        }

        tvGoToLogin.setOnClickListener {
            finish()
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
        if (viewRePwd.getContent().isEmpty()) {
            ToastUtil.showShortText(getString(R.string.re_pwd_can_not_empty))
            return false
        }
        if (viewPwd.getContent() != viewRePwd.getContent()) {
            ToastUtil.showShortText(getString(R.string.pwd_do_not_match))
            return false
        }
        return true
    }

    override fun onRegisterSuccess(loginResp: LoginResp?) {
        ToastUtil.showShortText(getString(R.string.register_success_hint))
        finish()
    }

    override fun onRegisterFail(errMsg: String?) {
        if (errMsg != null) {
            ToastUtil.showShortText(errMsg)
        }
    }
}