package com.zch.common

import com.zch.module.article.ArticleResp

/**
 * Created by zch on 2020-11-06.
 */
data class BannerUiState(
        var errMsg: String? = "",
        var data: List<BannerBean>? = null
)

data class ArticleUiState(
        var showLoading: Boolean? = false,
        var errMsg: String? = "",
        var data: ArticleResp? = null,
        var showEnd: Boolean? = false, // 加载更多
        var isRefresh: Boolean? = false, // 刷新
        var needLogin: Boolean? = false
)