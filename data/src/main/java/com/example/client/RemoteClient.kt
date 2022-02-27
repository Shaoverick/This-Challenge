package com.example.client

import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteClient @Inject constructor() {

    //region PROPERTIES ----------------------------------------------------------------------------
    private val retrofit: Retrofit
    private val baseUrl = "https://api.github.com/" //Should be stored in other place, ie: local.properties file
    //endregion


    init {
        val httpUrl = baseUrl.toHttpUrlOrNull() ?: throw IllegalArgumentException("Invalid base url")

        retrofit = Retrofit.Builder()
            .baseUrl(httpUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(buildOkHttpClient())
            .build()
    }


    //region PUBLIC METHODS ------------------------------------------------------------------------
    fun <T> createService(clazz: Class<T>): T = retrofit.create(clazz)
    //endregion

}