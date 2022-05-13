package com.critical_techworks.article_repository.source

import androidx.paging.PagingData
import androidx.paging.map
import com.critical_techworks.article_domain.model.Article
import com.critical_techworks.article_repository.model.ArticleDomainMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val NewsDataSource: NewsDataSource,
    private val articleDomainMapper: ArticleDomainMapper
) :
    NewsRepository {
    override suspend fun getNews(): Flow<PagingData<Article>> {
        return NewsDataSource.getNews()
            .map { it.map { articleDto -> articleDomainMapper.mapToDomainModel(articleDto) } }
    }
}