package com.zch.base.net

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by zch on 2019/01/05.
 */
class RepackParamsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(repackParams(chain.request()))
    }

    private fun repackParams(request: Request): Request {
        return request
    }
}