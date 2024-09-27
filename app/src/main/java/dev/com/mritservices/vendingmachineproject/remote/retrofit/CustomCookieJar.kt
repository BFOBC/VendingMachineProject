package dev.com.mritservices.vendingmachineproject.remote.retrofit

import android.content.Context
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import java.util.Collections
import java.util.concurrent.ConcurrentHashMap

class CustomCookieJar() : CookieJar {

    private val cookieStore = ConcurrentHashMap<String, List<Cookie>>()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieStore[url.host] = cookies
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val cookies = cookieStore[url.host] ?: emptyList()

        // If you need to filter cookies based on path, expiration, etc., you can do it here

        return cookies
    }
}

