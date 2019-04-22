package com.example.data.core

/**
 * Created by jsmirabal on 4/19/2019.
 */
sealed class Failure {
    data class ApiFailure(val message: String): Failure()
}