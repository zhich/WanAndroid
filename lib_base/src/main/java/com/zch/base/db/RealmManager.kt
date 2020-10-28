package com.zch.base.db

import com.zch.base.cache.LoginCache
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by zch on 2019/01/04.
 */
class RealmManager private constructor() {

    companion object {
        val instance: RealmManager by lazy {
            RealmManager()
        }
    }

    // 在登录时必须赋值，指定数据库实例和密码，退出登录时必须调用 realm.close()
    var defaultRealm: Realm = Realm.getInstance(RealmConfiguration.Builder()
            .schemaVersion(1)
            .migration(DBMigration())
            .name("default.realm")
            .deleteRealmIfMigrationNeeded()
            .build())

    private lateinit var userRealm: Realm

    fun getUserRealm(): Realm {
        if (!::userRealm.isInitialized || userRealm.isClosed) {
            userRealm = Realm.getInstance(RealmConfiguration.Builder()
                    .schemaVersion(1)
                    .migration(DBMigration())
                    .name("${LoginCache.getCurrentAccount()}.realm")
                    .deleteRealmIfMigrationNeeded()
                    .build())
        }
        return userRealm
    }

    /**
     * 切换账号时必须调用，否则用的是上一次的 userRealm
     */
    fun closeUserRealm() {
        if (::userRealm.isInitialized && !userRealm.isClosed) {
            userRealm.close()
        }
    }
}