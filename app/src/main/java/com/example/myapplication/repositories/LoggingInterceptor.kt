package com.example.myapplication.repositories

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url
        Log.e("URL REQUEST","Request URL: $url")
        return chain.proceed(request)
    }
}
