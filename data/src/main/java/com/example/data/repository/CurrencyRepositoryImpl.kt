package com.example.data.repository

import android.content.Context
import android.util.Log
import com.example.data.core.toCurrencyHistory
import com.example.data.db.CurrencyDatabase
import com.example.data.network.HistoryResponse
import com.example.data.network.Service
import com.example.domain.core.Either
import com.example.domain.core.Either.*
import com.example.domain.core.Failure
import com.example.domain.core.Params
import com.example.domain.entity.CurrencyHistory
import com.example.domain.repository.CurrencyRepository
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

class CurrencyRepositoryImpl
    @Inject constructor(private val context: Context) : CurrencyRepository {

    companion object {
        const val TAG = "CurrencyRepositoryImpl"
    }

    private val database by lazy {
        CurrencyDatabase.getDatabase(context)
    }

    override fun fetchHistory(params: Params) = fromDatabase(params)

    private fun fromService(params: Params): Either<Failure, CurrencyHistory> {
        Log.d(TAG, "Fetching history from service")
        return Service.fetchHistory(params).transform(
            {
                Left(it)
            },
            {
                save(it)
                Right(it.toCurrencyHistory())
            }
        )
    }

    private fun save(data: HistoryResponse) {
        database.historyDao().insert(data)
    }

    private fun fromDatabase(params: Params) =
        try {
            Log.d(TAG, "Fetching history from database")
            val result = database.historyDao().loadHistoryCache(params.startDate, params.endDate)
            if (result != null) {
                Right(result.toCurrencyHistory())
            } else {
                throw RuntimeException("Date range (${params.startDate} | ${params.endDate}) not found.")
            }

        } catch (e: Exception) {
            Log.e(TAG, "Error fetching from database. ${e.message}")
            fromService(params)
        }
}