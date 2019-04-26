package com.example.domain.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async

/**
 * Created by jsmirabal on 4/19/2019.
 */
abstract class BaseUseCase<out Type, in Params> {

    private var job: Job? = null

    abstract suspend fun run(params: Params): Either<Failure, Type>

    suspend operator fun invoke(params: Params) =
        DomainScope.async(Dispatchers.Default) { run(params) }.apply { job = this }

    fun cancel() {
        job?.cancel()
    }
}