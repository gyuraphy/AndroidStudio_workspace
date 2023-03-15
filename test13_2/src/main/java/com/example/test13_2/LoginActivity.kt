package com.example.test13_2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import com.example.test13_2.databinding.ActivityLoginBinding
import com.example.test13_2.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = binding.id.text
        val pwd = binding.pwd.text

        binding.loginBtn.setOnClickListener {
            val intent: Intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("id", "$id")
            intent.putExtra("pwd", "$pwd")
            startActivity(intent)
        }
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val saveId = savedInstanceState.getString("saveId")
        val savePwd = savedInstanceState.getString("savePwd")

        binding.id.hint = saveId
        binding.pwd.hint = savePwd
    }
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = binding.id.text
        val pwd = binding.pwd.text

        outState.putString("saveId", "$id")
        outState.putString("savePwd", "$pwd")

    }
}