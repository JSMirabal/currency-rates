package com.example.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by jsmirabal on 4/21/2019.
 */
interface CurrencyApi {
    @GET("/history")
    fun fetchHistory(
        @Query("start_at") startDate: String,
        @Query("end_at") endDate: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): Call<HistoryResponse>
}