package com.zch.common.bean

/**
 * Created by zch on 2020-10-21.
 */
data class UserBean(
        val chapterTops: MutableList<String>,
        val collectIds: MutableList<Int>,
        val email: String,
        val icon: String,
        val id: Int,
        val password: String,
        val token: String,
        val type: Int,
        val username: String
)