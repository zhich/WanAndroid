package com.zch.base.utils

import android.content.Context
import android.content.SharedPreferences

import com.zch.base.BaseApp

/**
 * Created by zch on 2019/01/04.
 */
object SharedPrefUtil {

    private var sp: SharedPreferences? = null

    private val preferences: SharedPreferences
        get() {
            if (sp == null) {
                sp = BaseApp.instance.getSharedPreferences("config", Context.MODE_PRIVATE)
            }
            return this.sp!!
        }

    fun putBoolean(key: String, value: Boolean) {
        val edit = preferences.edit()
        edit.putBoolean(key, value)
        edit.apply()
    }

    @JvmOverloads
    fun getBoolean(key: String, defValue: Boolean = false): Boolean {
        return preferences.getBoolean(key, defValue)
    }

    fun putString(key: String, value: String) {
        val edit = preferences.edit()
        edit.putString(key, value)
        edit.apply()
    }

    fun getString(key: String): String? {
        return getString(key, null)
    }

    fun getString(key: String, defValue: String?): String? {
        return preferences.getString(key, defValue)
    }

    fun putInt(key: String, value: Int) {
        val edit = preferences.edit()
        edit.putInt(key, value)
        edit.apply()
    }

    fun getInt(key: String): Int {
        val sp = preferences
        return getInt(key, -1)
    }

    fun getInt(key: String, defValue: Int): Int {
        return preferences.getInt(key, defValue)
    }

    fun putLong(key: String, value: Long) {
        val edit = preferences.edit()
        edit.putLong(key, value)
        edit.apply()
    }

    fun getLong(key: String): Long {
        return getLong(key, -1)
    }

    fun getLong(key: String, defValue: Long): Long {
        return preferences.getLong(key, defValue)
    }
}
