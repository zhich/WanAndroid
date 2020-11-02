package com.zch.wanandroid.di

import com.zch.common.CoroutinesDispatcherProvider
import com.zch.common.api.WanApi
import com.zch.common.api.WanRetrofitClient
import com.zch.user.login.LoginRepository
import com.zch.user.login.LoginViewModel
import com.zch.user.register.RegisterRepository
import com.zch.user.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by zch on 2020-10-22.
 */
val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { RegisterViewModel(get(), get()) }
}

val repositoryModule = module {
    single { WanRetrofitClient.getService(WanApi::class.java, "https://www.wanandroid.com") }
    single { CoroutinesDispatcherProvider() }
    single { LoginRepository(get()) }
    single { RegisterRepository(get()) }
}

val appModule = listOf(viewModelModule, repositoryModule)