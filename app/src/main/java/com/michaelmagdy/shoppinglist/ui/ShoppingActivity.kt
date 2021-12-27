package com.michaelmagdy.shoppinglist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.michaelmagdy.shoppinglist.R
import com.michaelmagdy.shoppinglist.ShoppingRepository
import com.michaelmagdy.shoppinglist.db.ShoppingDatabase
import com.michaelmagdy.shoppinglist.db.ShoppingItem
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance


//import org.kodein.di.generic.instance
/*
this import statement was missing which caused the following error
PSI2IR: "org.jetbrains.kotlin.psi2ir.generators.ErrorExpressionException: null: KtCallExpression" with delegate who has name or parameter with the same name as a property
 */
import org.kodein.di.android.kodein


class ShoppingActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: ShoppingViewModelFactory by instance()

    private lateinit var viewModel: ShoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)


        viewModel = ViewModelProvider(this, factory)
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