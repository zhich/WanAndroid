package com.zch.module.article

import java.io.Serializable

/**
 * Created by zch on 2020-11-06.
 */
data class ArticleResp(val offset: Int,
                       val size: Int,
                       val total: Int,
                       val pageCount: Int,
                       val curPage: Int,
                       val over: Boolean,
                       val datas: List<ArticleBean>) : Serializable