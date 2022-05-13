package com.critical_techworks.article_di


import com.critical_techworks.article_domain.model.Article
import com.critical_techworks.article_domain.util.DomainMapper
import com.critical_techworks.article_repository.model.ArticleDomainMapper
import com.critical_techworks.article_repository.model.ArticleDto
import com.critical_techworks.article_repository.source.NewsDataSource
import com.critical_techworks.article_repository.source.NewsRepository
import com.critical_techworks.article_repository.source.NewsRepositoryImp
import com.critical_techworks.article_repository.source.paging.NewsSource
import com.critical_techworks.article_repository.source.remote.NewsApi
import com.critical_techworks.article_repository.source.remote.NewsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun provideNewsRepository(
        NewsDataSource: NewsDataSource,
        domainMapper: DomainMapper<ArticleDto, Article>
    ): NewsRepository {
        return NewsRepositoryImp(NewsDataSource, domainMapper as ArticleDomainMapper)
    }

    @Provides
    fun provideNewsDataSource(newsSource: NewsSource): NewsDataSource {
        return NewsRemoteDataSource(newsSource)
    }

    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    fun provideNewsDataMapper(): DomainMapper<ArticleDto, Article> {
        return ArticleDomainMapper()
    }
}