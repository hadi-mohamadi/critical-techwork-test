package com.critical_techworks.core_network.util

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class HttpInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.newBuilder()
            .build()
        val requestBuilder = chain
            .request()
            .newBuilder()
        val newRequest = requestBuilder.url(url).build()
        return try {
            chain.proceed(newRequest)
        } catch (e: Exception) {
            throw IOException(e.message)
        }
    }
}