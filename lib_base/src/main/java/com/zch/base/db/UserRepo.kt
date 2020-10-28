package com.zch.base.db

import android.text.TextUtils
import com.zch.base.cache.LoginCache

/**
 * Created by zch on 2019/02/21.
 */
object UserRepo {

    private var currentUser: User? = null

    fun insertOrUpdateUser(user: User) {
        currentUser = user
        RealmManager.instance.defaultRealm.executeTransaction {
            it.insertOrUpdate(user)
        }
    }

    fun delete(account: String) {
        val deleteUser = getUserFromDB(account) ?: return

        RealmManager.instance.defaultRealm.executeTransaction {
            deleteUser.deleteFromRealm()
        }
    }

    fun getCurrentUser(): User? {
        return getUser(LoginCache.getCurrentAccount())
    }

    fun getUserList(): MutableList<User>? {
        val realmInstance = RealmManager.instance.defaultRealm
        val results = realmInstance
                .where<User>(User::class.java)
                .findAll()
        return realmInstance.copyFromRealm(results)
    }

    private fun getUser(account: String): User? {
        if (currentUser != null && account == currentUser!!.account) {
            return currentUser!!
        }
        val dbUser: User? = getUserFromDB(account) ?: return null
        currentUser = RealmManager.instance.defaultRealm.copyFromRealm(dbUser!!)
        return currentUser
    }

    fun getUserFromDB(account: String): User? {
        if (TextUtils.isEmpty(account)) {
            return null
        }
        val realmInstance = RealmManager.instance.defaultRealm
        return realmInstance
                .where<User>(User::class.java)
                .equalTo(User.FIELD_ACCOUNT, account)
                .findFirst()
    }
}