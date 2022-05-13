package com.critical_techworks.article_repository.source

import androidx.paging.PagingData
import com.critical_techworks.article_repository.model.ArticleDto
import kotlinx.coroutines.flow.Flow

interface NewsDataSource {

    suspend fun getNews(): Flow<PagingData<ArticleDto>>
}