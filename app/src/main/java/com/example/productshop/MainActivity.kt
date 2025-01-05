package com.example.productshop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val createStoreButton: Button = findViewById(R.id.createStoreButton)
        createStoreButton.setOnClickListener {
            // Переход на экран магазина
            val intent = Intent(this, StoreActivity::class.java)
            startActivity(intent)
        }
    }
}