package com.michaelmagdy.shoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.michaelmagdy.shoppinglist.R
import com.michaelmagdy.shoppinglist.ShoppingRepository
import com.michaelmagdy.shoppinglist.db.ShoppingDatabase

class ShoppingActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory)
            .get(ShoppingViewModel::class.java)
    }
}