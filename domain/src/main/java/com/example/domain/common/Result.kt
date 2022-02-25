package com.example.domain.common

sealed class Result<out T> {

    data class Failure<out T>(val throwable: Throwable) : Result<T>()
    data class Success<out T>(val data: T) : Result<T>()

    val isFailure get() = this is Failure
    val isSuccess get() = this is Success

    fun peekSuccessOrNull(): T? =
        when(this) {
            is Success -> this.data
            else -> null
        }

    fun peekFailureOrNull(): Throwable? =
        when(this) {
            is Failure -> this.throwable
            else -> null
        }

}
