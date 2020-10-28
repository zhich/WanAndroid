package com.zch.user.login

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.zch.common.CoroutinesDispatcherProvider
import com.zch.common.base.BaseViewModel
import com.zch.common.bean.UserBean
import kotlinx.coroutines.flow.collect

/**
 * Created by zch on 2020-10-21.
 */
class LoginViewModel(private val repository: LoginRepository, val provider: CoroutinesDispatcherProvider) : BaseViewModel() {

    val username = ObservableField("")
    val password = ObservableField("")

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

    private fun isInputValid(username: String?, password: String?) = !username.isNullOrBlank() && !password.isNullOrBlank()
}