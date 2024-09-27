package dev.com.mritservices.vendingmachineproject.remote.retrofit

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import dev.com.mritservices.vendingmachineproject.ApplicationClass
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkInterceptor : Interceptor {
    private val mApplicationContext: Context = ApplicationClass.context!!

    private val isConnected: Boolean
        get() {
            val cm = mApplicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        if (!isConnected) {
            try {
                throw NoNetworkException()
            }
            catch (ex : RuntimeException){
                Log.d("MyRetrofitException", "getSpecialities: ${ex.message.toString()}")
            }
            catch (ex : IOException){
                Log.d("MyRetrofitException", "getSpecialities: ${ex.message.toString()}")
            }catch (ex : Exception){
                Log.d("MyRetrofitException", "getSpecialities: ${ex.message.toString()}")
            }

        }
        return chain.proceed(originalRequest)
    }

    class NoNetworkException internal constructor() : RuntimeException("Please check Network Connection")
}