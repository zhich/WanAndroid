package com.zch.base

import com.zch.base.utils.AppUtil

/**
 * Created by zch on 2019/01/05.
 */
object Config {

    fun getBaseUrl(): String {
        return AppUtil.getMetaDataString("baseUrl")
    }
}