package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.data.db.entity.CurrencyEntity
import com.example.data.db.entity.HistoryEntity
import com.example.data.db.entity.RateEntity

@Dao
interface CurrencyHistoryDao {

    @Insert(onConflict = REPLACE)
    fun insertCurrencyHistory(currencyHistory: HistoryEntity)

    @Insert(onConflict = REPLACE)
    fun insertRates(rates: List<RateEntity>)

    @Insert(onConflict = REPLACE)
    fun insertCurrencies(currencies: HistoryEntity)

    @Query("SELECT * FROM currency_history LIMIT 1")
    fun loadHistory(): HistoryEntity

    @Query(
        """
            SELECT * FROM rate
            INNER JOIN currency_history AS cs ON cs.id = rate.rate_id
        """
    )
    fun loadHistoryRates(): List<RateEntity>

    @Query(
        """
            SELECT * FROM currency
            INNER JOIN rate AS r ON r.rate_id = currency.currency_id
        """
    )
    fun loadRateCurrencies(): List<CurrencyEntity>
}