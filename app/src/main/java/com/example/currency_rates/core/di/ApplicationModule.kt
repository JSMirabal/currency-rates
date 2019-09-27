package com.example.currency_rates.core.di

import android.content.Context
import com.example.currency_rates.CustomApplication
import com.example.data.di.DataModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by jsmirabal on 5/1/2019.
 */
@Module(includes = [DataModule::class])
class ApplicationModule(private val app: CustomApplication) {

    @Provides
    @Singleton
    fun appContext(): Context = app
}