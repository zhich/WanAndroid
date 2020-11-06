package com.zch.user.login

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zch.common.CoroutinesDispatcherProvider
import com.zch.common.base.BaseViewModel
import com.zch.common.UserBean
import kotlinx.coroutines.flow.collect

/**
 * Created by zch on 2020-10-21.
 */
class LoginViewModel(private val repository: LoginRepository, val provider: CoroutinesDispatcherProvider) : BaseViewModel() {

    val username = ObservableField("")
    val password = ObservableField("")
    val passwordVisible = ObservableField(false)

    private var _uiState = MutableLiveData<LoginUiState<UserBean>>()
    val uiState: LiveData<LoginUiState<UserBean>>
        get() = _uiState

    fun login() {
        launchOnUI {
            repository.login(username.get() ?: "", password.get() ?: "")
                    .collect {
                        _uiState.postValue(it)
                    }
        }
    }

    val verifyInput: (String) -> Unit = {
        _uiState.value = LoginUiState(enableLoginButton = isInputValid(username.get(), password.get()))
    }

    fun passwordVisibleClick() {
        passwordVisible.set(!passwordVisible.get()!!)
    }

    private fun isInputValid(username: String?, password: String?) = !username.isNullOrBlank() && !password.isNullOrBlank()
}