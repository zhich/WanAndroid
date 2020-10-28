package com.zch.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Created by zch on 2020-10-22.
 */
data class CoroutinesDispatcherProvider(val main: CoroutineDispatcher = Dispatchers.Main,
                                        val computation: CoroutineDispatcher = Dispatchers.Default,
                                        val io: CoroutineDispatcher = Dispatchers.IO) {
    constructor() : this(Dispatchers.Main, Dispatchers.Default, Dispatchers.IO)
}