package com.example.client.exception

class ServerInternalErrorException(
    val errorMessage: String? = "User is not allow to do this action."
) : Exception("Unauthorized Exception")