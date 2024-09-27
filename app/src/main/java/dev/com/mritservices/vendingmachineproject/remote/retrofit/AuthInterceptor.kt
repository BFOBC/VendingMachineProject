package dev.com.mritservices.vendingmachineproject.remote.retrofit


import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor  : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json; charset=utf-8")
//        requestBuilder.addHeader("Authorization","Bearer ${SharedPreferenceManager.instance?.read(TOKEN_KEY,"")}")
//        requestBuilder.addHeader("api-key", getApiKey())
        requestBuilder.addHeader("accept", "application/json")
//        requestBuilder.addHeader("Content-Type", "application/json")
        // If token has been saved, add it to the request
        return chain.proceed(requestBuilder.build())
    }

}