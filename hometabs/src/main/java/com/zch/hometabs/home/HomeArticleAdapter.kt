package com.zch.hometabs.home

import com.zch.common.base.BaseBindAdapter
import com.zch.hometabs.BR
import com.zch.hometabs.R
import com.zch.module.article.ArticleBean

/**
 * Created by zch on 2020-11-11.
 */
class HomeArticleAdapter(layoutResId: Int = R.layout.item_home_article) : BaseBindAdapter<ArticleBean>(layoutResId, BR.articleBean) {

    override fun convert(helper: BindViewHolder, item: ArticleBean) {
        super.convert(helper, item)

    }
}