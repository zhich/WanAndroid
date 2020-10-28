package com.zch.common.api

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 * Created by zch on 2020-10-21.
 */
data class HttpResult<out T>(
        val errorCode: Int = 0,
        val errorMsg: String? = null,
        val data: T? = null
)

suspend fun <T : Any> HttpResult<T>.doSuccess(successBlock: (suspend CoroutineScope.(T) -> Unit)? = null): HttpResult<T> {
    return coroutineScope {
        if (errorCode != -1) {
            this@doSuccess.data?.let { successBlock?.invoke(this, it) }
        }
        this@doSuccess
    }
}

suspend fun <T : Any> HttpResult<T>.doError(errorBlock: (suspend CoroutineScope.(String) -> Unit)? = null): HttpResult<T> {
    return coroutineScope {
        if (errorCode == -1) {
            this@doError.errorMsg?.let { errorBlock?.invoke(this, it) }
        }
        this@doError
    }
}