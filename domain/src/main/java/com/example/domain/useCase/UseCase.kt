package com.example.domain.useCase

import android.util.Log
import com.example.domain.common.Result

abstract class UseCase<in Params, out Type> {

    protected abstract suspend fun call(params: Params): Type

    suspend fun execute(params: Params): Result<Type> =
        try {
            Result.Success(call(params))
        } catch (throwable: Throwable) {
            Log.e(javaClass.canonicalName, throwable.message, throwable)
            Result.Failure(throwable)
        }

}
