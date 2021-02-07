package com.zch.hometabs.home

import androidx.lifecycle.MutableLiveData
import com.zch.common.ArticleUiState
import com.zch.common.BannerUiState
import com.zch.common.base.BaseViewModel
import kotlinx.coroutines.flow.collect

/**
 * Created by zch on 2020-11-04.
 */
class ArticleViewModel(private val repository: HomeRepository) : BaseViewModel() {

    sealed class ArticleType {
        object Home : ArticleType()
        object Square : ArticleType()
        object LatestProject : ArticleType()
        object ProjectDetailList : ArticleType()
    }

    val bannerUiState = MutableLiveData<BannerUiState>()
    val articleUiState = MutableLiveData<ArticleUiState>()

    private var currentPage = 0

    fun fetchBanner() {
        launchOnUI {
            repository.fetchBanner()
                    .collect {
                        bannerUiState.postValue(it)
                    }
        }
    }

    fun fetchHomeArticles(isRefresh: Boolean = false) = fetchArticleList(ArticleType.Home, isRefresh)

    private fun fetchArticleList(articleType: ArticleType, isRefresh: Boolean = false, cid: Int = 0) {
        launchOnUI {
            if (isRefresh) currentPage = if (articleType is ArticleType.ProjectDetailList) 1 else 0
            when (articleType) {
                ArticleType.Home -> repository.fetchHomeArticles(currentPage).collect {
                    handleResult(it, isRefresh)
                }
            }
        }
    }

    private fun handleResult(result: ArticleUiState, isRefresh: Boolean = false) {
        result.showLoading = false
        result.data?.let {
            if (it.offset > it.total) {
                articleUiState.postValue(result.apply { showEnd = true })
                return
            }
            currentPage++
            articleUiState.postValue(result.also { it1 -> it1.isRefresh = isRefresh })
        } ?: run {
            articleUiState.postValue(result)
        }
    }

    val refreshHome: () -> Unit = {}
}