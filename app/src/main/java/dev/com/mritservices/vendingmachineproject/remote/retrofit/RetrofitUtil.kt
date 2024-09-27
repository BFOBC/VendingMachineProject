package dev.com.mritservices.vendingmachineproject.remote.retrofit


import dev.com.mritservices.vendingmachineproject.ApplicationClass
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUtil {

    var apiService: ApiService? = null
    var apiServiceVM: ApiService? = null

    suspend fun getApiService(baseUrl:String): ApiService? {
        if (apiService == null) {
            ApplicationClass.context?.apply {
                val contentType = "application/json".toMediaType()
         //       val token = DataStore.getString(context = this, DataStore.token, "")

                val builder = Headers.Builder()
               // builder.add("registrationToken", "")
                //builder.add("Authorization", "Bearer $token")
                //builder.add("deviceId", "")

                val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                       // Timber.tag("OkHttp").d(message)
                    }
                }).apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }

                val cookiesInterceptor=ReceivedCookiesInterceptor()
                val receivedCookiesInterceptor=ReceivedCookiesInterceptor()

                val defaultHttpClient = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(cookiesInterceptor)
                    .addInterceptor(receivedCookiesInterceptor)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)

                  //  .addInterceptor(myCustomInterceptor)  // Add your custom interceptor here
                    .addInterceptor { chain: Interceptor.Chain ->
                        val request: Request = chain.request().newBuilder()
                            .headers(builder.build())
                            .build()
                        chain.proceed(request)
                    }
                    .build()

                apiService = Retrofit.Builder()
                    .baseUrl("https://dev.intelparcel.com:6001") // Replace with your base URL
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(defaultHttpClient)
                    .build()
                    .create(ApiService::class.java)
            }
        }
        return apiService
    }
    suspend fun getApiServiceVM(baseUrl:String,vmID:Int,requestHash:String): ApiService? {
        if (apiServiceVM == null) {
            ApplicationClass.context?.apply {

                val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                      //  Timber.tag("OkHttp").d(message)
                    }
                }).apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }

                val cookiesInterceptor=ReceivedCookiesInterceptor()
                val receivedCookiesInterceptor=ReceivedCookiesInterceptor()

                val defaultHttpClient = OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(cookiesInterceptor)
                    .addInterceptor(receivedCookiesInterceptor)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)

                  //  .addInterceptor(myCustomInterceptor)  // Add your custom interceptor here
                    .addInterceptor { chain: Interceptor.Chain ->
                        val originalRequest = chain.request()
                        val requestBuilder = originalRequest.newBuilder()
                            .header("vm-id", vmID.toString())
                            .header("request-hash", requestHash)
                            .method(originalRequest.method, originalRequest.body)
                        val newRequest = requestBuilder.build()
                        chain.proceed(newRequest)
                    }
                    .build()

                apiServiceVM = Retrofit.Builder()
                    .baseUrl(baseUrl) // Replace with your base URL
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(defaultHttpClient)
                    .build()
                    .create(ApiService::class.java)
            }
        }
        return apiServiceVM
    }
}