package com.example.data.di

import android.content.Context
import com.example.data.db.CurrencyDatabase
import com.example.data.network.CurrencyApi
import com.example.data.network.HistoryDeserializer
import com.example.data.network.HistoryResponse
import com.example.data.repository.CurrencyRepositoryImpl
import com.example.domain.repository.CurrencyRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by jsmirabal on 9/26/2019.
 */
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.exchangeratesapi.io/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .registerTypeAdapter(HistoryResponse::class.java, HistoryDeserializer())
                        .create()
                )
            )
            .build()

    @Provides
    @Singleton
    fun provideCurrencyApi(retrofit: Retrofit): CurrencyApi =
        retrofit.create(CurrencyApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(context: Context): CurrencyDatabase = CurrencyDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun provideCurrencyRepository(repository: CurrencyRepositoryImpl): CurrencyRepository = repository
}