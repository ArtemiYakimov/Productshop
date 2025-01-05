package com.example.productshop

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ProductAdapter(private val context: Context, private val products: List<Product>) : BaseAdapter() {

    override fun getCount(): Int {
        return products.size
    }

    override fun getItem(position: Int): Any {
        return products[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val product = products[position]
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.product_list_item, parent, false)

        val productNameTextView: TextView = view.findViewById(R.id.productNameTextView)
        val productPriceTextView: TextView = view.findViewById(R.id.productPriceTextView)
        val productImageView: ImageView = view.findViewById(R.id.productImageView)

        productNameTextView.text = product.name
        productPriceTextView.text = context.getString(R.string.price_format, product.price)
        productImageView.setImageURI(product.imageUri)

        // Устанавливаем обработчик клика для перехода на экран детализации
        view.setOnClickListener {
            val intent = Intent(context, ProductDetailActivity::class.java).apply {
                putExtra("product", product)
            }
            context.startActivity(intent)
        }

        return view
    }
}