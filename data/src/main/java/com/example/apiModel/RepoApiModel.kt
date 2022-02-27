package com.example.apiModel

import com.google.gson.annotations.SerializedName

data class RepoApiModel(
    @SerializedName("name")         val name: String,
    @SerializedName("description")  val description: String?,
    @SerializedName("owner")        val owner: Owner,
    @SerializedName("fork")         val fork: Boolean = false,
    @SerializedName("html_url")     val htmlUrl: String

)


data class Owner(
    @SerializedName("login")        val login: String,
    @SerializedName("avatar_url")   val avatarUrl: String?,
    @SerializedName("html_url")     val htmlUrl: String

)