package com.critical_techworks.article_interactors

import androidx.paging.PagingData
import com.critical_techworks.article_domain.model.Article
import com.critical_techworks.article_repository.source.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNews @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(): Flow<PagingData<Article>> {
        return newsRepository.getNews()
    }
}