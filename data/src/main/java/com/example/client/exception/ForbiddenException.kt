package com.example.client.exception

class ForbiddenException(
    private val errorMessage: String? = "User is not allow to do this action"
) : Exception(errorMessage)