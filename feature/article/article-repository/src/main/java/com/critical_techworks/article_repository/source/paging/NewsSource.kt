package com.critical_techworks.article_repository.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.critical_techworks.core_network.util.RepositoryResponse
import com.critical_techworks.article_repository.BaseRepository
import com.critical_techworks.article_repository.model.ArticleDto
import com.critical_techworks.article_repository.response.ResponseDto
import com.critical_techworks.article_repository.source.remote.NewsApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsSource @Inject constructor(
    private val newsApi: NewsApi,
    private val baseRepository: BaseRepository
) : PagingSource<Int, ArticleDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDto> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = (getNews(nextPageNumber) as RepositoryResponse.Success).value
            val articles = response.articles
            val totalResults = response.totalResults
            LoadResult.Page(
                data = articles,
                prevKey = if (nextPageNumber > 1) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber * pageSize < totalResults) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleDto>): Int? {
        return state.anchorPosition
    }

    private suspend fun getNews(page: Int): RepositoryResponse<ResponseDto> {
        return withContext(Dispatchers.IO) {
            return@withContext baseRepository.networkResponseOf {
                newsApi.getNews(page = page)
            }
        }
    }
}

const val pageSize: Int = 20