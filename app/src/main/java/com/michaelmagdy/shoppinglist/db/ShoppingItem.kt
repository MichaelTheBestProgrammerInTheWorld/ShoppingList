package com.michaelmagdy.shoppinglist.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "shopping_items")
data class ShoppingItem(var name:String, var amount:Int){

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
