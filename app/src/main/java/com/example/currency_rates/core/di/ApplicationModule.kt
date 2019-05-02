package com.example.currency_rates.core.di

import android.content.Context
import com.example.currency_rates.CustomApplication
import com.example.data.repository.CurrencyRepositoryImpl
import com.example.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by jsmirabal on 5/1/2019.
 */
@Module
class ApplicationModule(private val app: CustomApplication) {

    @Provides @Singleton
    fun appContext(): Context = app

    @Provides
    fun provideCurrencyRepository(repoImpl: CurrencyRepositoryImpl): CurrencyRepository = repoImpl
}