package com.zch.user.register

import com.alibaba.android.arouter.facade.annotation.Route
import com.zch.base.constant.ARouterPathConstant
import com.zch.base.ext.handleInputContent
import com.zch.base.ext.hideKeyboard
import com.zch.base.ext.longToast
import com.zch.base.ext.toast
import com.zch.common.base.BaseVMActivity
import com.zch.user.R
import com.zch.user.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_register.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by zch on 2020-11-02.
 */
@Route(path = ARouterPathConstant.User.REGISTER_ACTIVITY)
class RegisterActivity : BaseVMActivity() {

    private val mBinding by binding<ActivityRegisterBinding>(R.layout.activity_register)
    private val mRegisterViewModel by viewModel<RegisterViewModel>()

    override fun startObserve() {
        mRegisterViewModel.uiState.observe(this, {
            if (it.isLoading) {
                hideKeyboard()
            }
            it.isSuccess?.let {
                toast(getString(R.string.register_success_hint))
                finish()
            }
            it.isError?.let {
                longToast(it)
            }
            if (it.needLogin) {
                finish()
            }
        })
    }

    override fun initView() {
        mBinding.viewModel = mRegisterViewModel
        edtAccount.handleInputContent(ivClearAccount)
        edtPassword.handleInputContent(ivClearPassword)
        edtRepassword.handleInputContent(ivClearRepassword)
        tvGoToLogin.setOnClickListener {
            finish()
        }
    }

    override fun initData() {
    }
}