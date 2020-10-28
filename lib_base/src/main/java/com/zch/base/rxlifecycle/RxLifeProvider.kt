package com.zch.base.rxlifecycle

import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.android.ActivityEvent

/**
 * Created by zch on 2019/01/04.
 */
abstract class RxLifeProvider(protected var superView: IView) {

    /**
     * 必须在子类的构造方法中初始化
     */

    fun <T> bindToLifecycle(): LifecycleTransformer<T> {
        return getLifecycleProvider().bindToLifecycle<T>()
    }

    private fun getLifecycleProvider(): LifecycleProvider<ActivityEvent> {
        if (superView !is LifecycleProvider<*>) {
            throw RuntimeException("supperView must be instance of RxLifeProvider")
        }
        return superView as LifecycleProvider<ActivityEvent>
    }

    fun <T> bindUntilEvent(event: ActivityEvent): LifecycleTransformer<T> {
        return getLifecycleProvider().bindUntilEvent(event)
    }
}