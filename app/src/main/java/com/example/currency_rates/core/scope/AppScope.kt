package com.example.currency_rates.core.scope

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Created by jsmirabal on 5/1/2019.
 */
object AppScope: CoroutineScope {

    inline operator fun invoke(block: AppScope.() -> Unit) = block(this)

    private val job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

}