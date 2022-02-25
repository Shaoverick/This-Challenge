package com.example.domain.model.apiModel

import com.google.gson.annotations.SerializedName

data class RepoApiModel(
    @SerializedName("name")         val name: String,
    @SerializedName("description")  val description: String?,
    @SerializedName("owner")        val owner: Owner,
    @SerializedName("fork")         val fork: Boolean
)


data class Owner(
    @SerializedName("login")        val login: String,
    @SerializedName("avatar_url")   val avatarUrl: String?
)