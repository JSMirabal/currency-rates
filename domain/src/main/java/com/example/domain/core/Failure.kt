package com.example.domain.core

/**
 * Created by jsmirabal on 4/19/2019.
 */
sealed class Failure {
    object NetworkFailure: Failure()
    data class ApiFailure(val message: String): Failure()
    data class DatabaseFailure(val message: String): Failure()
}