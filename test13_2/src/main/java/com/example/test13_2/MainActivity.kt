package com.example.test13_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test13_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = binding.id.text
        val pwd = binding.pwd.text

        binding.login.setOnClickListener {
            val intent: Intent = Intent(this, AfterLoginActivity::class.java)
            intent.putExtra("id", "$id")
            intent.putExtra("pwd", "$pwd")
            setResult(RESULT_OK, intent)
            startActivityForResult(intent, 10)
        }
    }
}