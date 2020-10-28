package com.zch.common.api

import com.zch.base.net.BaseRetrofitClient
import okhttp3.OkHttpClient

/**
 * Created by zch on 2020-10-21.
 */
object WanRetrofitClient : BaseRetrofitClient() {

    override fun handleBuilder(builder: OkHttpClient.Builder) {

    }
}