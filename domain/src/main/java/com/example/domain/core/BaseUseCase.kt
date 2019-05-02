package com.example.domain.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Created by jsmirabal on 4/19/2019.
 */
abstract class BaseUseCase<out Type, in Params> {

    private lateinit var job: Job

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, result: (Either<Failure, Type>) -> Unit = {}) {
        DomainScope {
            async(Dispatchers.IO) { run(params) }.let {
                job = it
                launch { result(it.await()) }
            }
        }
    }

    fun cancel() {
        if (::job.isInitialized) job.cancel()
    }

    fun isCancelled() = job.isCancelled

}