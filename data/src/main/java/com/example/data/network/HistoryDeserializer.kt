package com.example.data.network

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

        val jsonObject = json?.asJsonObject
        return HistoryResponse(
            jsonObject?.get("base")?.asString ?: "not found",
            deserializeRates(jsonObject),
            jsonObject?.get("start_at")?.asString ?: "not found",
            jsonObject?.get("end_at")?.asString ?: "not found"
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