package com.zch.user.login

import com.zch.base.cache.LoginCache
import com.zch.base.db.User
import com.zch.base.db.UserRepo
import com.zch.base.utils.JsonUtil
import com.zch.common.api.WanApi
import com.zch.common.api.doError
import com.zch.common.api.doSuccess
import com.zch.common.base.BaseRepository
import com.zch.common.UserBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

/**
 * Created by zch on 2020-10-21.
 */
class LoginRepository(private val api: WanApi) : BaseRepository() {

    suspend fun login(username: String, password: String) = flow {
        api.login(username, password).doSuccess {
            val user: User = it.let {
                User(
                        account = it.username,
                        id = it.id,
                        password = it.password,
                        icon = it.icon,
                        email = it.email,
                        token = it.token,
                        type = it.type,
                        collectIds = JsonUtil.toJson(it.collectIds),
                        chapterTops = JsonUtil.toJson(it.chapterTops)
                )
            }
            UserRepo.insertOrUpdateUser(user)
            LoginCache.setCurrentAccount(user.account!!)
            LoginCache.setIsLogin(true)
            emit(LoginUiState(isSuccess = it, enableLoginButton = true))
        }.doError {
            emit(LoginUiState<UserBean>(isError = it, enableLoginButton = true))
        }
    }.onStart {
        emit(LoginUiState(isLoading = true))
    }.flowOn(Dispatchers.IO)
            .catch {
                emit(LoginUiState(isError = it.message, enableLoginButton = true))
            }
}