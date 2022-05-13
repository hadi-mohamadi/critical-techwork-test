package com.critical_techworks.core_network.util

import com.google.gson.annotations.SerializedName

sealed class RepositoryResponse<out T> {
    data class Success<out T>(@SerializedName("data") val value: T) : RepositoryResponse<T>()
    data class GenericError(
        @SerializedName("code") val code: Int? = null,
        @SerializedName("message") val message: String
    ) : RepositoryResponse<Nothing>()

    object NetworkError : RepositoryResponse<Nothing>()
}