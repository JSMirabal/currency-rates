package com.example.data.repository

import android.content.Context
import com.example.data.core.toCurrencyHistory
import com.example.data.db.CurrencyDatabase
import com.example.data.network.Service
import com.example.domain.core.Either
import com.example.domain.core.Failure
import com.example.domain.core.Params
import com.example.domain.entity.CurrencyHistory
import com.example.domain.repository.CurrencyRepository

class CurrencyRepositoryImpl(private val context: Context) : CurrencyRepository {

    override fun fetchHistory(params: Params) = fromService(params)

    private fun fromService(params: Params): Either<Failure, CurrencyHistory> {
        return Service.fetchHistory(params).transform(
            {
                Either.Left(it)
            },
            {
                Either.Right(it.toCurrencyHistory())
            }
        )
    }

    /*private fun fromDatabase(): Either<Failure, CurrencyHistory> {
        val dao = CurrencyDatabase.getDatabase(context).historyDao()
    }*/
}