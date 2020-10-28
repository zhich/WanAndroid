package com.zch.base.utils

/**
 * Created by zch on 2019/01/04.
 */
object ListUtil {

    fun <V> isEmpty(sourceList: List<V>?): Boolean {
        return null == sourceList || sourceList.isEmpty()
    }
}
