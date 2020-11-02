package com.zch.user.register

import com.zch.common.api.WanApi
import com.zch.common.api.doError
import com.zch.common.api.doSuccess
import com.zch.common.base.BaseRepository
import com.zch.common.bean.UserBean
import com.zch.user.login.LoginUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

/**
 * Created by zch on 2020-11-02.
 */
class RegisterRepository(private val api: WanApi) : BaseRepository() {

    @ExperimentalCoroutinesApi
    suspend fun register(username: String, password: String, repassword: String) = flow<LoginUiState<UserBean>> {
        api.register(username, password, repassword).doSuccess {
            emit(LoginUiState(needLogin = true))
        }.doError {
            emit(LoginUiState(isError = it, enableLoginButton = true))
        }
    }.onStart {
        emit(LoginUiState(isLoading = true))
    }.flowOn(Dispatchers.IO)
            .catch { emit(LoginUiState(isError = it.message, enableLoginButton = true)) }
}