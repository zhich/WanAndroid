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
    }

    val bannerUiState = MutableLiveData<BannerUiState>()
    val articleUiState = MutableLiveData<ArticleUiState>()

    fun fetchBanner() {
        launchOnUI {
            repository.fetchBanner()
                    .collect {
                        bannerUiState.postValue(it)
                    }
        }
    }

    val refreshHome: () -> Unit = {}
}