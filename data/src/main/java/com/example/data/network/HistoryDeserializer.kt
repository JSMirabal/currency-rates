package com.example.data.network

import com.example.data.core.NOT_FOUND
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type

/**
 * Created by jsmirabal on 4/21/2019.
 */
class HistoryDeserializer : JsonDeserializer<HistoryResponse> {

    override fun deserialize(

        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): HistoryResponse {
        // Parse per type
        val jsonObject = json?.asJsonObject
        return HistoryResponse(
            jsonObject?.get("base")?.asString ?: NOT_FOUND,
            deserializeRates(jsonObject),
            jsonObject?.get("start_at")?.asString ?: NOT_FOUND,
            jsonObject?.get("end_at")?.asString ?: NOT_FOUND
        )
    }

    private fun deserializeRates(jsonObject: JsonObject?) =
        jsonObject?.get("rates")?.asJsonObject?.entrySet()?.map { rateEntry ->
            HistoryResponse.Rate(
                rateEntry.key,
                deserializeCurrencies(rateEntry)
            )
        }.orEmpty()

    private fun deserializeCurrencies(rateEntry: Map.Entry<String, JsonElement>) =
        rateEntry.value.asJsonObject?.entrySet()?.map { currencyEntry ->
            HistoryResponse.Rate.Currency(
                currencyEntry.key,
                currencyEntry.value.asDouble
            )
        }.orEmpty()
}