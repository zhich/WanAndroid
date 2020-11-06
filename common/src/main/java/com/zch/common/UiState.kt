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
        val showLoading: Boolean,
        val showError: String?,
        val showSuccess: ArticleResp?,
        val showEnd: Boolean, // 加载更多
        val isRefresh: Boolean, // 刷新
        val needLogin: Boolean? = null
)