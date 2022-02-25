package com.example.client

import com.example.client.exception.*
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

typealias NetworkDeferred<T> = Response<T>

suspend fun <T> awaitToResponse(request: suspend () -> NetworkDeferred<T>): T =
    awaitToResponseInternal(request) ?: throw ParseException()

suspend fun <T> awaitToResponseInternal(request: suspend () -> NetworkDeferred<T>): T? {
    try {
        val response = request.invoke()
        if (response.isSuccessful)
            return response.body()

        val error = when (response.code()) {
            401 -> UnauthorizedException()
            403 -> ForbiddenException(errorFromBody(response) ?: response.message())
            404 -> PageNotFoundException()
            500 -> ServerInternalErrorException(errorFromBody(response) ?: response.message())
            else -> RequestException(
                code = response.code(),
                errorMessage = errorFromBody(response) ?: response.message()
            )
        }

        throw error
    } catch (ex: Exception) {
        val error = when (ex) {
            is ConnectException -> NoInternetConnectionException()
            is UnknownHostException -> NoInternetConnectionException()
            is SocketTimeoutException -> NoInternetConnectionException()
            else -> ex
        }
        throw error
    }
}


private fun <T> errorFromBody(response: Response<T>): String? {
    return response.errorBody()?.string()
}
