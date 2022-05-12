package com.critical_techworks.core_network.util

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WrapperResponse<T>(
    @SerializedName("object") val body: T
)