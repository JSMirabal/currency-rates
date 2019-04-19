package com.example.currency_rates

import android.app.Application
import com.example.currency_rates.core.util.debug
import com.squareup.leakcanary.LeakCanary

/**
 * Created by jsmirabal on 4/18/2019.
 */
class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        initializeLeakDetection()
    }

    private fun initializeLeakDetection() {
        debug { LeakCanary.install(this) }
    }

    companion object {
        private lateinit var instance: CustomApplication
        @JvmStatic fun get() = instance
    }
}