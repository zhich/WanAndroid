package com.zch.base.rx

import com.zch.base.net.BaseHttpResult
import com.zch.base.net.ErrorCode
import com.zch.base.rxlifecycle.IView
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/**
 * Created by zch on 2019/01/09.
 */

fun <T : BaseHttpResult> Observable<T>.ss(
        onSuccess: (T) -> Unit,
        onFail: ((String?) -> Unit)? = null
): Disposable {
    return this.compose(SchedulerUtils.ioToMain())
            .subscribe({
                when {
                    it.errorCode == ErrorCode.SUCCESS -> onSuccess.invoke(it)
                    it.errorCode == ErrorCode.TOKEN_INVAILD -> {

                    }
                    else -> onFail?.invoke(it.errorMsg)
                }
            }, {
                onFail?.invoke(it.message)
            })
}

fun <T : BaseHttpResult> Observable<T>.sss(
        view: IView?,
        onSuccess: (T) -> Unit,
        onFail: ((String?) -> Unit)? = null
): Disposable {
    view?.showLoading()
    return this.compose(SchedulerUtils.ioToMain())
            .subscribe({
                when {
                    it.errorCode == ErrorCode.SUCCESS -> onSuccess.invoke(it)
                    it.errorCode == ErrorCode.TOKEN_INVAILD -> {

                    }
                    else -> onFail?.invoke(it.errorMsg)
                }
                view?.hideLoading()
            }, {
                view?.hideLoading()
                onFail?.invoke(it.message)
            })
}