package com.zch.user.register

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zch.common.CoroutinesDispatcherProvider
import com.zch.common.base.BaseViewModel
import com.zch.common.bean.UserBean
import com.zch.user.login.LoginUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

/**
 * Created by zch on 2020-11-02.
 */
class RegisterViewModel(private val repository: RegisterRepository, val provider: CoroutinesDispatcherProvider) : BaseViewModel() {

    val username = ObservableField("")
    val password = ObservableField("")
    val repassword = ObservableField("")
    val passwordVisible = ObservableField(false)
    val repasswordVisible = ObservableField(false)

    private var _uiState = MutableLiveData<LoginUiState<UserBean>>()
    val uiState: LiveData<LoginUiState<UserBean>>
        get() = _uiState

    @ExperimentalCoroutinesApi
    fun register() {
        launchOnUI {
            repository.register(username.get() ?: "", password.get() ?: "", repassword.get() ?: "")
                    .collect {
                        _uiState.postValue(it)
                    }
        }
    }

    val verifyInput: (String) -> Unit = {
        _uiState.value = LoginUiState(enableLoginButton = isInputValid(username.get(), password.get(), repassword.get()))
    }

    fun passwordVisibleClick() {
        passwordVisible.set(!passwordVisible.get()!!)
    }

    fun repasswordVisibleClick() {
        repasswordVisible.set(!repasswordVisible.get()!!)
    }

    private fun isInputValid(username: String?, password: String?, repassword: String?) =
            !username.isNullOrBlank() && !password.isNullOrBlank() && !repassword.isNullOrBlank()
}