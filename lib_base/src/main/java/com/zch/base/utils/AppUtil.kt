package com.zch.base.utils

import android.content.Context
import android.content.pm.PackageManager
import com.zch.base.BaseApp

/**
 * Created by zch on 2019/01/05.
 */
object AppUtil {

    fun getVersionCode(context: Context): Int {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return packageInfo.versionCode
    }

    fun getVersionName(context: Context): String {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return packageInfo.versionName
    }

    fun getMetaDataString(key: String): String? {
        return BaseApp.instance.packageManager.getApplicationInfo(
                BaseApp.instance.packageName, PackageManager.GET_META_DATA
        ).metaData.getString(key)
    }

    fun getMetaDataInt(key: String): Int {
        return BaseApp.instance.packageManager.getApplicationInfo(
                BaseApp.instance.packageName, PackageManager.GET_META_DATA
        ).metaData.getInt(key)
    }
}