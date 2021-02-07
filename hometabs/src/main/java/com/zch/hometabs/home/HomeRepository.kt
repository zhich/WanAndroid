package com.zch.hometabs.home

import com.zch.common.ArticleUiState
import com.zch.common.BannerUiState
import com.zch.common.api.WanApi
import com.zch.common.api.doError
import com.zch.common.api.doSuccess
import com.zch.common.base.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Created by zch on 2020-11-04.
 */
class HomeRepository(private val api: WanApi) : BaseRepository() {

    suspend fun fetchBanner() = flow {
        api.fetchBanner().doSuccess {
            emit(BannerUiState(data = it))
        }.doError {
            emit(BannerUiState(it))
        }
    }.flowOn(Dispatchers.IO)
            .catch {
                emit(BannerUiState(it.message))
            }

    suspend fun fetchHomeArticles(page: Int) = flow {
        api.fetchHomeArticles(page).doSuccess {
            emit(ArticleUiState(data = it))
        }.doError {
            emit(ArticleUiState(errMsg = it))
        }
    }.flowOn(Dispatchers.IO)
            .catch {
                emit(ArticleUiState(errMsg = it.message))
            }
}