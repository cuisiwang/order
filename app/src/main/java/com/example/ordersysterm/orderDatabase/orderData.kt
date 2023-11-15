package com.example.ordersysterm.orderDatabase

import android.media.Image
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class orderData(var name:String, var price: Int, var description: String, var time:String, var number: Int) {
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}