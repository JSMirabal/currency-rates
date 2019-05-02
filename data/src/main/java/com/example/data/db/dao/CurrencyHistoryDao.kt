package com.example.data.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.data.db.entity.CurrencyEntity
import com.example.data.db.entity.HistoryEntity
import com.example.data.db.entity.RateEntity
import com.example.data.network.HistoryResponse


@Dao
abstract class CurrencyHistoryDao {

    fun insert(history: HistoryResponse) {
        val id = history.hashCode().toLong()
        val historyEntity = HistoryEntity(
            id, history.base, history.startDate, history.endDate
        )
        val rates = history.rates.map {
            RateEntity(historyId = id, date = it.date)
        }
        val currencies = history.rates.mapIndexed { index, rate ->
            rate.currencies.map {
                CurrencyEntity(rateId = index+1L, name = it.name, value = it.value)
            }
        }.flatten()
        insertCurrencyHistory(historyEntity)
        insertRates(rates)
        insertCurrencies(currencies)
    }

    @Insert(onConflict = REPLACE)
    abstract fun insertCurrencyHistory(currencyHistory: HistoryEntity)

    @Insert(onConflict = REPLACE)
    abstract fun insertRates(rates: List<RateEntity>)

    @Insert(onConflict = REPLACE)
    abstract fun insertCurrencies(currencies: List<CurrencyEntity>)

    @Query("SELECT * FROM currency_history LIMIT 1")
    abstract fun loadHistory(): HistoryEntity

    @Query(
        """
            SELECT * FROM rate AS r
            INNER JOIN currency_history AS ch ON ch.id = r.history_id
        """
    )
    abstract fun loadHistoryRates(): List<RateEntity>

    @Query(
        """
            SELECT * FROM currency AS c
            INNER JOIN rate AS r ON r.rate_id = c.rate_id
        """
    )
    abstract fun loadRateCurrencies(): List<CurrencyEntity>

    @Query(
        """
            SELECT * FROM currency_history AS ch, rate AS r, currency AS c
            INNER JOIN currency_history ON ch.id = r.history_id
            INNER JOIN rate ON r.rate_id = c.rate_id
            WHERE ch.start_date = :startDate AND ch.end_date = :endDate
        """
    )
    abstract fun loadHistoryCache(startDate: String, endDate: String): HistoryCache?
}

class HistoryCache {
    @Embedded
    var history: HistoryEntity? = null
    @Relation(parentColumn = "id", entityColumn = "history_id", entity = RateEntity::class)
    var rates: List<RateWithCurrencies>? = null
}

class RateWithCurrencies {
    @Embedded
    var rate: RateEntity? = null
    @Relation(parentColumn = "rate_id", entityColumn = "rate_id")
    var currencies: List<CurrencyEntity>? = null
}