package com.example.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "currency_history")
data class HistoryEntity(
    @PrimaryKey
    val id: Long = 0,
    val base: String,
    @ColumnInfo(name = "start_date")
    val startDate: String,
    @ColumnInfo(name = "end_date")
    val endDate: String
)

@Entity(
    tableName = "rate", foreignKeys = [ForeignKey(
        entity = HistoryEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("rate_id"),
        onDelete = CASCADE,
        onUpdate = CASCADE
    )]
)
data class RateEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rate_id")
    val id: Long = 0,
    val date: String
)

@Entity(
    tableName = "currency", foreignKeys = [ForeignKey(
        entity = RateEntity::class,
        parentColumns = arrayOf("rate_id"),
        childColumns = arrayOf("currency_id"),
        onDelete = CASCADE,
        onUpdate = CASCADE
    )]
)
data class CurrencyEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "currency_id")
    val id: Long = 0,
    val name: String,
    val value: Double
)