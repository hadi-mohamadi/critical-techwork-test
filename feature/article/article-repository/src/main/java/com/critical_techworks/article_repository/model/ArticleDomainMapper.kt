package com.critical_techworks.article_repository.model

import com.critical_techworks.article_domain.model.Article
import com.critical_techworks.article_domain.model.Source
import com.critical_techworks.article_domain.util.DomainMapper

class ArticleDomainMapper : DomainMapper<ArticleDto, Article> {
    override fun mapToDomainModel(model: ArticleDto): Article {
        return Article(
            source = model.source?.let { SourceDomainMapper().mapToDomainModel(it) },
            author = model.author,
            title = model.title,
            description = model.description,
            url = model.url,
            urlToImage = model.urlToImage,
            publishedAt = model.publishedAt,
            content = model.content
        )
    }

    override fun mapFromDomainModel(domainModel: Article): ArticleDto {
        return ArticleDto(
            source = domainModel.source?.let { SourceDomainMapper().mapFromDomainModel(it) },
            author = domainModel.author,
            title = domainModel.title,
            description = domainModel.description,
            url = domainModel.url,
            urlToImage = domainModel.urlToImage,
            publishedAt = domainModel.publishedAt,
            content = domainModel.content
        )
    }

    override fun mapToDomainList(modelList: List<ArticleDto>): List<Article> {
        return modelList.map { mapToDomainModel(it) }
    }

    override fun mapFromDomainList(domainModelList: List<Article>): List<ArticleDto> {
        return domainModelList.map { mapFromDomainModel(it) }
    }
}

class SourceDomainMapper : DomainMapper<SourceDto, Source> {
    override fun mapToDomainModel(model: SourceDto): Source {
        return Source(
            id = model.id,
            name = model.name
        )
    }

    override fun mapFromDomainModel(domainModel: Source): SourceDto {
        return SourceDto(
            id = domainModel.id,
            name = domainModel.name
        )
    }

    override fun mapToDomainList(modelList: List<SourceDto>): List<Source> {
        return modelList.map { mapToDomainModel(it) }
    }

    override fun mapFromDomainList(domainModelList: List<Source>): List<SourceDto> {
        return domainModelList.map { mapFromDomainModel(it) }
    }
}