package com.michaelmagdy.shoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.michaelmagdy.shoppinglist.R
import com.michaelmagdy.shoppinglist.ShoppingRepository
import com.michaelmagdy.shoppinglist.db.ShoppingDatabase
import com.michaelmagdy.shoppinglist.db.ShoppingItem
import kotlinx.android.synthetic.main.activity_shopping.*

class ShoppingActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory)
            .get(ShoppingViewModel::class.java)

        val rvAdapter = ShoppingItemAdapter(listOf(), viewModel)
        rvShoppingItems.apply {
            layoutManager = LinearLayoutManager(this@ShoppingActivity)
            adapter = rvAdapter
        }
        viewModel.getAllShoppingItems().observe(this, Observer {
            rvAdapter.items = it
            rvAdapter.notifyDataSetChanged()
        })
        fab.setOnClickListener {
            AddShoppingItemDialog(this,
            object : AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}