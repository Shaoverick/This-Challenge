package com.example.domain.model.entity

data class RepoEntity(
    val name: String,
    val description: String?,
    val ownerEntity: OwnerEntity,
    val fork: Boolean
)


data class OwnerEntity(
    val login: String,
    val avatarUrl: String?
)
