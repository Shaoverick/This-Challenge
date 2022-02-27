package com.example.client

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

private const val TIMEOUT_IN_SECONDS = 30L

fun buildOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .addInterceptor(
                        CommonRequestInterceptor()
                )
                .addInterceptor (
                        HttpLoggingInterceptor().apply {
                                this.level = HttpLoggingInterceptor.Level.BODY
                        }
                )
                .build()
