package com.zch.base.net

import com.zch.base.utils.JsonUtil
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

/**
 * Created by zch on 2019/01/05.
 */
class CheckLoginStateInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        try {
            if (response?.code() == 200) {
                val body = response.body()?.string()
                val mediaType = response.body()?.contentType()
                val result = JsonUtil.fromJson(body, HttpResult::class.java)
                if (result?.errorCode == -1001) {
//                    LogoutAuthPresenter().handleTokenInValid()
                }

                return response.newBuilder()
                        .body(ResponseBody.create(mediaType, body))
                        .build()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response
    }
}