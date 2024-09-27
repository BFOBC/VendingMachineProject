package dev.com.mritservices.vendingmachineproject.remote.retrofit

import com.google.gson.annotations.SerializedName

data class NetworkResponse<T>(
    @SerializedName("resultCode") val status: Int = 0,
    @SerializedName("message") val message: String = "",
    @SerializedName("response") val response: T? = null,

)
data class NetworkResponseD<T>(
    @SerializedName("responseCode") val status: Int = 0,
    @SerializedName("message") val message: String = "",
    @SerializedName("data") val response: T? = null,

)
