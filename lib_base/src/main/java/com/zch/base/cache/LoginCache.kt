package com.zch.base.cache

import android.text.TextUtils
import com.zch.base.utils.SharedPrefUtil

/**
 * Created by zch on 2019/02/21.
 */
object LoginCache {

    private const val ACCOUNT = "account"
    private const val IS_LOGIN = "isLogin"

    private var currentAccount: String? = null
    private var isLogin: Boolean? = false

    fun setCurrentAccount(account: String) {
        currentAccount = account
        SharedPrefUtil.putString(ACCOUNT, account)
    }

    fun getCurrentAccount(): String {
        if (!TextUtils.isEmpty(currentAccount)) {
            return currentAccount!!
        }
        currentAccount = SharedPrefUtil.getString(ACCOUNT, "")
        return currentAccount!!
    }

    fun setIsLogin(isLogin: Boolean) {
        this.isLogin = isLogin
        SharedPrefUtil.putBoolean(IS_LOGIN, isLogin)
    }

    fun isLogin(): Boolean {
        if (isLogin == true) {
            return true
        }
        isLogin = SharedPrefUtil.getBoolean(IS_LOGIN)
        return isLogin!!
    }
}