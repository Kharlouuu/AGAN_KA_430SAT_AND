package com.example.multilayoutlogger

import android.annotation.SuppressLint
import androidx.fragment.app.FragmentManager.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: MainActivity started")

        val btnGetStarted = findViewById<Button>(R.id.btnGetStarted)


        btnGetStarted.setOnClickListener {
            val intent = Intent (this,SecondActivity::class.java)
            startActivity(intent)
          }
        }
    }