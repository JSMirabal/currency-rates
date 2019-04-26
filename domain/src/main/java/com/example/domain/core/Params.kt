package com.example.domain.core

data class Params(
    val startDate: String,
    val endDate: String,
    val base: String = "EUR",
    val symbols: String = "USD"
)