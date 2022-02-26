package com.example.domain.model.entity

typealias UrlString = String

data class RepoEntity(
    val name: String,
    val description: String?,
    val ownerEntity: OwnerEntity,
    val fork: Boolean,
    val htmlUrl: UrlString
)


data class OwnerEntity(
    val login: String,
    val avatarUrl: String?,
    val htmlUrl: UrlString
)
