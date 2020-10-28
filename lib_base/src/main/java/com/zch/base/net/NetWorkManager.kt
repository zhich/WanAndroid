package com.zch.base.net

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.zch.base.Config
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by zch on 2019/01/04.
 */
class NetWorkManager {

    companion object {

        private val lock1 = Any()
        private val lock2 = Any()

        private var sClient: OkHttpClient? = null
        private var sRetrofit: Retrofit? = null

        fun getRetrofit() =
                sRetrofit ?: synchronized(lock1) {
                    sRetrofit = Retrofit.Builder()
                            .client(getOkHttpClient())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .baseUrl(Config.getBaseUrl())
                            .build()
                    return sRetrofit!!
                }

        private fun getOkHttpClient() =
                sClient ?: synchronized(lock2) {
//                    val loggingInterceptor = HttpLoggingInterceptor {
//                        Log.i("RetrofitLog", it)
//                    }.apply {
//                        level = HttpLoggingInterceptor.Level.BODY
//                    }

                    sClient = OkHttpClient.Builder()
                            .retryOnConnectionFailure(true)
                            .addInterceptor(RepackParamsInterceptor())
//                            .addInterceptor(loggingInterceptor)
                            .connectTimeout(60, TimeUnit.SECONDS)
                            .readTimeout(60, TimeUnit.SECONDS)
                            .writeTimeout(60, TimeUnit.SECONDS)
                            .build()

                    sClient
                }

    }
}