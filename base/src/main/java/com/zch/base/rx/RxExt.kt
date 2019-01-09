package com.zch.base.rx

import com.zch.base.net.BaseHttpResult
import io.reactivex.Observable

/**
 * Created by zch on 2019/01/09.
 */

fun <T : BaseHttpResult> Observable<T>.ss(
        onSuccess: (T) -> Unit
) {
    this.compose(SchedulerUtils.ioToMain())
            .subscribe()
}