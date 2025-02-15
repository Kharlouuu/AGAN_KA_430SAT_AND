package com.example.multilayoutlogger

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager.TAG


class ThirdActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_third)

        Log.d(TAG, "onCreate: ThirdActivity started")

        val btnAction = findViewById<Button>(R.id.btnAction)
        btnAction.setOnClickListener {
            Log.e(TAG, "Successfully Registered! Performing action...")
            Toast.makeText(this, "Button Clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}