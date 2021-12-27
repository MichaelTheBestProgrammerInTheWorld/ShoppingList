package com.michaelmagdy.shoppinglist

import com.michaelmagdy.shoppinglist.db.ShoppingDatabase
import com.michaelmagdy.shoppinglist.db.ShoppingItem

class ShoppingRepository(private val db: ShoppingDatabase) {


    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}