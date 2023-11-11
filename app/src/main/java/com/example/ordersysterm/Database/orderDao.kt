package com.example.ordersysterm.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface orderDao{

    @Insert
    fun insertOrder(orderData: orderData) : Long

    @Update
    fun updateOrder(newOrder : orderData)

    @Query("select * from orderData")
    fun selectAll(): List<orderData>

    @Delete
    fun deleteOrder(orderData: orderData)
}