package com.zch.base.db

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by zch on 2019/02/21.
 */
open class User(
        @PrimaryKey
        var account: String? = "",
        var id: Int? = 0,
        var password: String? = "",
        var icon: String? = "",
        var email: String? = "",
        var token: String? = "",
        var type: Int? = 0,
        var collectIds: String? = "",
        var chapterTops: String? = ""
) : RealmObject() {
    companion object {
        val FIELD_ACCOUNT = "account"
    }
}