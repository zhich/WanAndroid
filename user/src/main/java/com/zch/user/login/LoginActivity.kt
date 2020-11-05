package com.zch.user.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zch.base.constant.ARouterPathConstant
import com.zch.base.ext.handleInputContent
import com.zch.base.ext.hideKeyboard
import com.zch.base.ext.longToast
import com.zch.base.ext.toast
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

    private val mBinding by binding<ActivityLoginBinding>(R.layout.activity_login)
    private val mLoginViewModel by viewModel<LoginViewModel>()

    @ExperimentalCoroutinesApi
    override fun startObserve() {
        mLoginViewModel.uiState.observe(this, {
            if (it.isLoading) {
                hideKeyboard()
            }
            it.isSuccess?.let {
                toast(getString(R.string.login_success_hint))
                finish()
            }
            it.isError?.let {
                longToast(it)
            }
        })
    }

    override fun initView() {
        mBinding.viewModel = mLoginViewModel
        edtAccount.handleInputContent(ivClearAccount)
        edtPassword.handleInputContent(ivClearPassword)
        tvGoToRegister.setOnClickListener {
            ARouter.getInstance().build(ARouterPathConstant.User.REGISTER_ACTIVITY).navigation()
        }
    }

    override fun initData() {
    }
}