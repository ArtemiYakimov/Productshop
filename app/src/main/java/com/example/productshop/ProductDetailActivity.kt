package com.example.productshop

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var productImageView: ImageView
    private lateinit var productNameTextView: TextView
    private lateinit var productPriceTextView: TextView
    private lateinit var productDescriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        productImageView = findViewById(R.id.productDetailImageView)
        productNameTextView = findViewById(R.id.productDetailNameTextView)
        productPriceTextView = findViewById(R.id.productDetailPriceTextView)
        productDescriptionTextView = findViewById(R.id.productDetailDescriptionTextView)

        // Получаем данные из Intent
        val product: Product? = intent.getParcelableExtra("product")

        if (product == null) {
            Log.e("ProductDetailActivity", "Product is null")
            productNameTextView.text = "Ошибка: продукт не найден"
        } else {
            Log.d("ProductDetailActivity", "Product received: ${product.name}")
            productNameTextView.text = product.name
            productPriceTextView.text = getString(R.string.price_format, product.price)
            productDescriptionTextView.text = product.description
            productImageView.setImageURI(product.imageUri)
        }

        findViewById<Button>(R.id.exitButton).setOnClickListener {
            finish() // Закрывает приложение
        }
    }
}