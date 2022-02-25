package com.example.passporter

import com.example.domain.common.Result

sealed class UIResult<out T> {

    companion object {
        fun <T> fromResult(result: Result<T>): UIResult<T> =
            if (result.isSuccess)
                Success(result.peekSuccessOrNull())
            else
                Failure(result.peekFailureOrNull())
    }

    data class InProgress<out T>(val progressData: T? = null): UIResult<T>()
    
    data class Success<out T>(val data: T? = null): UIResult<T>()
    
    data class Failure<out T>(val error: Throwable? = null): UIResult<T>()

    
    fun peekDataOrNull(): T? =
        when (this) {
            is Success -> data
            is InProgress -> progressData
            else -> null
        }

    fun peekFailureOrNull(): Throwable? =
        when (this) {
            is Failure -> error
            else -> null
        }
    
}
