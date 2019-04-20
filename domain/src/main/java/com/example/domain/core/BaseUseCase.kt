package com.example.domain.core

import kotlinx.coroutines.*

/**
 * Created by jsmirabal on 4/19/2019.
 */
abstract class BaseUseCase<out Type, in Params> where Type : Any {

    private var job: Job? = null

    abstract suspend fun run(params: Params): Either<Failure, Type>

    suspend fun executeAsync(params: Params) =
        DomainScope.async(Dispatchers.Default) { run(params) }.apply { job = this }

    fun cancel() {
        job?.cancel()
    }
}