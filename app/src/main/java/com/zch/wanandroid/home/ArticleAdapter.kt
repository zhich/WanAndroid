package com.zch.wanandroid.home

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.zch.base.utils.DateUtil
import com.zch.wanandroid.R

/**
 * Created by zch on 2019/01/10.
 */
class ArticleAdapter(articles: MutableList<Article>) : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_article, articles) {

    override fun convert(helper: BaseViewHolder, item: Article) {
        item.run {
            helper.run {
                setText(R.id.tvAuthor, author)
                setText(R.id.tvDate, DateUtil.long2Str(publishTime))
                setText(R.id.tvTitle, title)
                setText(R.id.tvArticleCategory, "$superChapterName/$chapterName")
            }
        }
    }
}