package com.michaelmagdy.shoppinglist.ui

import com.michaelmagdy.shoppinglist.db.ShoppingItem

interface AddDialogListener {

    fun onAddButtonClicked(item: ShoppingItem)
}