package dev.com.mritservices.vendingmachineproject.remote.retrofit

import android.content.Context
import android.preference.PreferenceManager
import dev.com.mritservices.vendingmachineproject.ApplicationClass
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.Response
import java.io.IOException


class ReceivedCookiesInterceptor() : Interceptor {
    private val context: Context? = ApplicationClass.context
    @Throws(IOException::class)
    override fun intercept(chain: Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {
            val cookies = PreferenceManager.getDefaultSharedPreferences(
                context
            ).getStringSet("PREF_COOKIES", HashSet()) as HashSet<String>?
            for (header in originalResponse.headers("Set-Cookie")) {
                cookies!!.add(header)
            }
            val memes = PreferenceManager.getDefaultSharedPreferences(
                context
            ).edit()
            memes.putStringSet("PREF_COOKIES", cookies).apply()
            memes.commit()
        }
        return originalResponse
    }
}