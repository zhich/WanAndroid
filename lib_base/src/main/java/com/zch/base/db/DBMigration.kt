package com.zch.base.db

import io.realm.DynamicRealm
import io.realm.RealmMigration

/**
 * Created by zch on 2019/02/21.
 */
class DBMigration : RealmMigration {

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema = realm.schema
        if (oldVersion == 1L) {

            //Example usage
//            schema.create("Person")
//                    .addField("id", Long::class.java, FieldAttribute.PRIMARY_KEY)
//                    .addField("name", String::class.java)
//                    .addField("age", Int::class.javaPrimitiveType)
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is DBMigration
    }

    override fun hashCode(): Int {
        return 0xABCDEF
    }
}