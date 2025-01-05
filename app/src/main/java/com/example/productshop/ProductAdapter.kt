package com.example.productshop

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ProductAdapter(private val context: Context, private val products: List<Product>) : BaseAdapter() {




    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val product = products[position]
        val productNameTextView: TextView = view.findViewById(R.id.productNameTextView)
        val productPriceTextView: TextView = view.findViewById(R.id.productPriceTextView)
        val productImageView: ImageView = view.findViewById(R.id.productImageView)

        productNameTextView.text = product.name

        return view
    }
}