package com.michaelmagdy.shoppinglist.ui

import androidx.lifecycle.ViewModel
import com.michaelmagdy.shoppinglist.ShoppingRepository
import com.michaelmagdy.shoppinglist.db.ShoppingItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepository) : ViewModel() {


    fun upsert(item: ShoppingItem) =
        GlobalScope.launch {
            repository.upsert(item)
        }

    fun delete(item: ShoppingItem) = GlobalScope.launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()
}