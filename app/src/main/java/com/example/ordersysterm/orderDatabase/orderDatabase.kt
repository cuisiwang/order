package com.example.ordersysterm.orderDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [orderData::class])
abstract class orderDatabase : RoomDatabase() {
    abstract fun orderDao(): orderDao
    companion object {
        private var instance: orderDatabase? = null
        @Synchronized
        fun getDatabase(context: Context): orderDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext,
                orderDatabase::class.java, "app_database")
                .build().apply {
                    instance = this
                }
        }
    }

}