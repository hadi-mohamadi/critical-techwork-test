package com.critical_techworks.article_repository.response

import com.critical_techworks.article_domain.model.Article
import com.critical_techworks.article_repository.model.ArticleDto
import com.google.gson.annotations.SerializedName


data class ResponseDto(
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<ArticleDto>
)

data class Response(
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<Article>
)

