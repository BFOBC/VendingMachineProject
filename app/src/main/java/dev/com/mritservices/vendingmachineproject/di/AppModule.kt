package dev.com.mritservices.vendingmachineproject.di

import android.util.Log
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.com.mritservices.vendingmachineproject.BuildConfig
import dev.com.mritservices.vendingmachineproject.BuildConfig.BASE_URL
import dev.com.mritservices.vendingmachineproject.remote.retrofit.AddCookiesInterceptor
import dev.com.mritservices.vendingmachineproject.remote.retrofit.ApiService
import dev.com.mritservices.vendingmachineproject.remote.retrofit.AuthInterceptor
import dev.com.mritservices.vendingmachineproject.remote.retrofit.CustomCookieJar
import dev.com.mritservices.vendingmachineproject.remote.retrofit.NetworkInterceptor
import dev.com.mritservices.vendingmachineproject.remote.retrofit.ReceivedCookiesInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providerBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                // Log the message (you can use any logging mechanism here)
                Log.d("okHttp", message)
            }
        })
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ReceivedCookiesInterceptor())
            .addInterceptor(AddCookiesInterceptor())
            .addInterceptor(NetworkInterceptor())
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .cookieJar(CustomCookieJar())
            .build()
    } else {
        OkHttpClient
            .Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(AuthInterceptor())
            .addInterceptor(NetworkInterceptor())
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

}
