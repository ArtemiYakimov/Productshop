package com.example.productshop

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ExitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exit)

        val thankYouTextView: TextView = findViewById(R.id.thankYouTextView)
        thankYouTextView.text = getString(R.string.thank_you_message)

        val exitButton: Button = findViewById(R.id.exitButton)
        exitButton.setOnClickListener {
            finish() // Закрывает приложение
        }
    }
}