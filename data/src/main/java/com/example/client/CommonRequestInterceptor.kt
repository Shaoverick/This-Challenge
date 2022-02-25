package com.example.client

import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.*

class CommonRequestInterceptor: Interceptor {

    companion object {
        private const val token = "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"
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val builder: Request.Builder = original.newBuilder()
            .header("access_token", token)
            .method(original.method, original.body)

        return chain.proceed(builder.build())
    }

}
