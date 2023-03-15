package com.example.test13_2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.test13_2.databinding.ActivityLoginBinding
import com.example.test13_2.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = binding.id.text
        val pwd = binding.pwd.text

        binding.loginBtn.setOnClickListener {
            val intent: Intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("id", "$id")
            intent.putExtra("pwd", "$pwd")
            startActivity(intent)
//            setResult(RESULT_OK, intent)
//            startActivityForResult(intent, 10)
        }
    }
}