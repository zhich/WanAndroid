package com.zch.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

/**
 * Created by zch on 2019/02/21.
 */
object LiveDataEventManager {

    private val eventMap = HashMap<String, MutableLiveData<Any>>()

    fun with(key: String) = eventMap[key] ?: MutableLiveData<Any>().also { eventMap[key] = it }

    fun remove(key: String, lifecycleOwner: LifecycleOwner) {
        eventMap[key]?.removeObservers(lifecycleOwner)
        eventMap.remove(key)
    }

    fun removeObservers(key: String, lifecycleOwner: LifecycleOwner) {
        eventMap[key]?.removeObservers(lifecycleOwner)
    }

    const val EVENT_LOGIN_SUCCESS = "login_success"
}