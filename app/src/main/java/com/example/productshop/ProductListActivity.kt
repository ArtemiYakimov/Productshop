package com.example.productshop

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ProductListActivity : AppCompatActivity() {

    private val products = mutableListOf<Product>()
    private lateinit var productListView: ListView
    private lateinit var productNameEditText: EditText
    private lateinit var productPriceEditText: EditText
    private lateinit var productDescriptionEditText: EditText
    private lateinit var productImageView: ImageView
    private var productImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        productListView = findViewById(R.id.productListView)
        productNameEditText = findViewById(R.id.productNameEditText)
        productPriceEditText = findViewById(R.id.productPriceEditText)
        productDescriptionEditText = findViewById(R.id.productDescriptionEditText)
        productImageView = findViewById(R.id.productImageView)

        val addButton: Button = findViewById(R.id.addButton)
        addButton.setOnClickListener { addProduct() }

        val exitButton: Button = findViewById(R.id.exitButton)
        exitButton.setOnClickListener {
            finish()
            Toast.makeText(this, "Программа завершена", Toast.LENGTH_SHORT).show()
        }

        productImageView.setOnClickListener {
            openGallery()
        }

        productListView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product", products[position])
            startActivity(intent)
        }
    }

    private fun addProduct() {
        val name = productNameEditText.text.toString()
        val price = productPriceEditText.text.toString().toDoubleOrNull()
        val description = productDescriptionEditText.text.toString()

        if (name.isNotEmpty() && price != null && productImageUri != null) {
            // Преобразуем Uri в строку
            val product = Product(name, price, description, productImageUri.toString())
            products.add(product)
            updateProductList()
            clearInputs()
        } else {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateProductList() {
        val adapter = ProductAdapter(this, products)
        productListView.adapter = adapter
    }

    private fun clearInputs() {
        productNameEditText.text.clear()
        productPriceEditText.text.clear()
        productDescriptionEditText.text.clear()
        productImageView.setImageResource(0)
        productImageUri = null
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            productImageUri = data?.data
            productImageView.setImageURI(productImageUri)
        }
    }

    companion object {
        private const val GALLERY_REQUEST_CODE = 100
    }
}