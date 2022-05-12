package com.critical_techworks.article_repository.source.remote

import androidx.paging.*
import com.critical_techworks.article_repository.model.ArticleDto
import com.critical_techworks.article_repository.source.paging.NewsSource
import com.critical_techworks.article_repository.source.paging.pageSize
import com.critical_techworks.article_repository.source.NewsDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(private val newsSource: NewsSource) :
    NewsDataSource {
    override suspend fun getNews(): Flow<PagingData<ArticleDto>> {
        return Pager(PagingConfig(pageSize = pageSize)) { newsSource }.flow
    }
}