package com.zch.common

/**
 * Created by zch on 2020-11-06.
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

data class BannerBean(val desc: String,
                      val id: Int,
                      val imagePath: String,
                      val isVisible: Int,
                      val order: Int,
                      val title: String,
                      val type: Int,
                      val url: String)