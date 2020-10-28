package com.zch.base.rx

import com.zch.base.rx.scheduler.IoMainScheduler

/**
 * Created by zch on 2019/01/10.
 */
object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}