package com.example.productshop

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class StoreActivity : AppCompatActivity() {
    private val products = mutableListOf<Product>()
    private lateinit var productListView: ListView
    private lateinit var productImageView: ImageView
    private lateinit var productNameEditText: EditText
    private lateinit var productPriceEditText: EditText
    private lateinit var productDescriptionEditText: EditText
    private lateinit var selectImageButton: Button
    private lateinit var addProductButton: Button
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)

        productNameEditText = findViewById(R.id.productNameEditText)
        productPriceEditText = findViewById(R.id.productPriceEditText)
        productDescriptionEditText = findViewById(R.id.productDescriptionEditText)
        productImageView = findViewById(R.id.productImageView)
        selectImageButton = findViewById(R.id.selectImageButton)
        addProductButton = findViewById(R.id.addProductButton)
        productListView = findViewById(R.id.productListView)

        selectImageButton.setOnClickListener {
            // Открыть галерею для выбора изображения
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, IMAGE_PICK_CODE)
        }

        addProductButton.setOnClickListener {
            addProduct()
        }

        findViewById<Button>(R.id.exitButton).setOnClickListener {
            finish() // Закрывает приложение
        }
    }

    private fun addProduct() {
        val name = productNameEditText.text.toString()
        val price = productPriceEditText.text.toString().toDoubleOrNull()
        val description = productDescriptionEditText.text.toString()

        if (name.isNotEmpty() && price != null && selectedImageUri != null) {
            val product = Product(name, price, selectedImageUri!!, description)
            products.add(product)
            updateProductList()
            clearInputs()
        } else {
            Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
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
        productImageView.setImageURI(null)
        selectedImageUri = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data?.data
            productImageView.setImageURI(selectedImageUri)
        }
    }

    companion object {
        private const val IMAGE_PICK_CODE = 1000
    }
}