package com.example.currency_rates.core.util

import com.example.currency_rates.BuildConfig

/**
 * Created by jsmirabal on 4/18/2019.
 */

fun debug(block: () -> Unit) {
    if (BuildConfig.DEBUG) block()
}