package dev.com.mritservices.vendingmachineproject.remote.retrofit

import android.content.Context
import android.preference.PreferenceManager
import dev.com.mritservices.vendingmachineproject.ApplicationClass
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class AddCookiesInterceptor() : Interceptor {
    private val context: Context? = ApplicationClass.context
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val preferences = PreferenceManager.getDefaultSharedPreferences(
            context
        ).getStringSet(PREF_COOKIES, HashSet()) as HashSet<String>?
        val original: Request = chain.request()
        if (original.url.toString().contains("distributor")) {
            for (cookie in preferences!!) {
                builder.addHeader("Cookie", cookie)
            }
        }
        return chain.proceed(builder.build())
    }

    companion object {
        const val PREF_COOKIES = "PREF_COOKIES"
    }
}