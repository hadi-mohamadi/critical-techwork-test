package com.critical_techworks.article_repository.source.remote

import com.critical_techworks.article_repository.BuildConfig
import com.critical_techworks.article_repository.response.ResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET(BuildConfig.source)
    suspend fun getNews(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = BuildConfig.apiKey,
        @Query("page") page: Int,
    ): Response<ResponseDto>

}