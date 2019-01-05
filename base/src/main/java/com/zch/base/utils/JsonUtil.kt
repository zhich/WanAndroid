package com.zch.base.utils

import com.google.gson.Gson
import com.google.gson.JsonNull
import com.google.gson.JsonSyntaxException

import org.json.JSONException
import org.json.JSONObject

import java.lang.reflect.Type

/**
 * Created by zch on 2019/01/04.
 */
object JsonUtil {

    private val gson = Gson()

    fun toJson(src: Any?): String? {
        if (null == src) {
            return gson.toJson(JsonNull.INSTANCE)
        }
        return try {
            gson.toJson(src)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            null
        }
    }

    fun <T> fromJson(json: String?, classOfT: Class<T>): T? {
        return try {
            gson.fromJson<T>(json, classOfT as Type)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            null
        }
    }

    fun fromJson(json: String?, typeOfT: Type): Any? {
        return try {
            gson.fromJson<Any>(json, typeOfT)
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            null
        }
    }

    fun getValue(json: String?, key: String): String {
        try {
            val `object` = JSONObject(json)
            return `object`.optString(key)
        } catch (e: JSONException) {

            e.printStackTrace()
        }
        return ""
    }
}
