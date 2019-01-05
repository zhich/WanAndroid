package com.zch.base.net

/**
 * Created by zch on 2019/01/05.
 */
open class Result<T>(
        val data: T? = null,
        val errorCode: Int = 0,
        val errorMsg: String? = null
)