package com.critical_techworks.article_repository

import com.critical_techworks.core_network.util.RepositoryResponse
import com.google.gson.Gson
import retrofit2.Response
import javax.inject.Inject

class BaseRepository @Inject constructor() {
    inline fun <reified T : Any> networkResponseOf(service: () -> Response<T>): RepositoryResponse<T> {
        return try {
            val response = service()
            if (response.isSuccessful) {
                RepositoryResponse.Success(response.body()!!)
            } else {
                val gson = Gson()
                val typedValue = gson.fromJson(
                    response.errorBody()?.string(),
                    RepositoryResponse.GenericError::class.java
                )
                RepositoryResponse.GenericError(typedValue.code, typedValue.message)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            RepositoryResponse.NetworkError
        }
    }
}