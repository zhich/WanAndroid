package com.zch.user.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.zch.base.constant.ARouterPathConstant
import com.zch.base.utils.ToastUtil
import com.zch.common.base.BaseVMActivity
import com.zch.user.R
import com.zch.user.databinding.ActivityLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by zch on 2020-10-21.
 */
@Route(path = ARouterPathConstant.User.LOGIN_ACTIVITY)
class LoginActivity : BaseVMActivity() {

    private val binding by binding<ActivityLoginBinding>(R.layout.activity_login)
    private val loginViewModel by viewModel<LoginViewModel>()

    override fun startObserve() {
        loginViewModel.uiState.observe(this, {
            it.isSuccess?.let {
                finish()
            }
            it.isError?.let {
                ToastUtil.showLongText(it)
            }
            if (it.needLogin) {
                loginViewModel.login()
            }
        })
    }

    override fun initView() {
        binding.viewModel = loginViewModel
    }

    override fun initData() {
    }
}