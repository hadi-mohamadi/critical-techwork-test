package com.critical_techworks.article_repository.source

import androidx.paging.PagingData
import com.critical_techworks.article_domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(): Flow<PagingData<Article>>
}