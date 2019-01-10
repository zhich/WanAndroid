package com.zch.wanandroid.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zch.wanandroid.R
import kotlinx.android.synthetic.main.item_article.view.*

/**
 * Created by zch on 2019/01/10.
 */
class ArticleAdapter(private var articles: MutableList<Article>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    fun updateData(dataList: MutableList<Article>) {
        this.articles = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    class ViewHolder(containerView: View) : RecyclerView.ViewHolder(containerView) {

        fun bind(article: Article) {
            with(article) {
                with(itemView) {
                    tvAuthor.text = author
                    tvDate.text = publishTime.toString()
                    tvTitle.text = title
                    tvArticleCategory.text = "$superChapterName/$chapterName"
                }
            }
        }

    }
}