package com.zch.user.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zch.base.constant.ARouterPathConstant
import com.zch.base.ext.handleInputContent
import com.zch.base.ext.hideKeyboard
import com.zch.base.ext.longToast
import com.zch.common.base.BaseVMActivity
import com.zch.user.R
import com.zch.user.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by zch on 2020-10-21.
 */
@Route(path = ARouterPathConstant.User.LOGIN_ACTIVITY)
class LoginActivity : BaseVMActivity() {

    private val binding by binding<ActivityLoginBinding>(R.layout.activity_login)
    private val loginViewModel by viewModel<LoginViewModel>()

    @ExperimentalCoroutinesApi
    override fun startObserve() {
        loginViewModel.uiState.observe(this, {
            if (it.isLoading) {
                hideKeyboard()
            }
            it.isSuccess?.let {
                finish()
            }
            it.isError?.let {
                longToast(it)
            }
        })
    }

    override fun initView() {
        binding.viewModel = loginViewModel
        edtAccount.handleInputContent(ivClearAccount)
        edtPassword.handleInputContent(ivClearPassword)
        tvGoToRegister.setOnClickListener {
            ARouter.getInstance().build(ARouterPathConstant.User.REGISTER_ACTIVITY).navigation()
        }
    }

    override fun initData() {
    }
}