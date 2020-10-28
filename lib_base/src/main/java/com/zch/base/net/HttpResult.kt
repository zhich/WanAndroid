package com.zch.base.net

/**
 * Created by zch on 2019/01/05.
 */
class HttpResult<T>(
        val data: T? = null
) : BaseHttpResult()

open class BaseHttpResult {
    val errorCode: Int = 0
    val errorMsg: String? = null
}