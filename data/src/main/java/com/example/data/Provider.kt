package com.example.data

import com.example.data.network.Service
import com.example.data.network.Service.Params

/**
 * Created by jsmirabal on 4/21/2019.
 */
object Provider {

    fun fetchHistory(params: Params) = Service.fetchHistory(params)
}