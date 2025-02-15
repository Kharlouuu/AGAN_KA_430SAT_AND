package com.example.multilayoutlogger

import android.annotation.SuppressLint
import androidx.fragment.app.FragmentManager.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)


            val btnRegister = findViewById<Button>(R.id.btnRegister)

            btnRegister.setOnClickListener {
                Log.i(TAG, "Navigating to ThirdActivity")
                val intent = Intent(this, ThirdActivity::class.java)
                intent.putExtra("EXTRA_MESSAGE", " ThirdActivity")
                startActivity(intent)
            }

        }
    }

