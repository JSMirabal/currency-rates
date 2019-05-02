package com.example.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.db.dao.CurrencyHistoryDao
import com.example.data.db.entity.CurrencyEntity
import com.example.data.db.entity.HistoryEntity
import com.example.data.db.entity.RateEntity

@Database(entities = [HistoryEntity::class, RateEntity::class, CurrencyEntity::class], version = 1, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract fun historyDao(): CurrencyHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: CurrencyDatabase? = null

        fun getDatabase(context: Context): CurrencyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CurrencyDatabase::class.java,
                    "currency_database.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}