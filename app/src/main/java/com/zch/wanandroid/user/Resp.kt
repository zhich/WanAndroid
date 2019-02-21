package com.zch.wanandroid.user

/**
 * Created by zch on 2019/02/20.
 */
data class LoginResp(
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