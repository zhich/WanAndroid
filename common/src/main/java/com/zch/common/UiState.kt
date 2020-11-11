package com.zch.common

import com.zch.module.article.ArticleResp

/**
 * Created by zch on 2020-11-06.
 */
data class BannerUiState(
        val showError: String? = "",
        val showSuccess: List<BannerBean>? = null
)

data class ArticleUiState(
        val showLoading: Boolean? = false,
        val showError: String? = "",
        val showSuccess: ArticleResp? = null,
        val showEnd: Boolean? = false, // 加载更多
        val isRefresh: Boolean? = false, // 刷新
        val needLogin: Boolean? = false
)