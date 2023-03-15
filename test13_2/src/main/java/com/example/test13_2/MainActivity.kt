package com.example.test13_2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.test13_2.databinding.ActivityLoginBinding
import com.example.test13_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        val pwd = intent.getStringExtra("pwd")

        binding.id.text = "$id"
        binding.pwd.text = "$pwd"
    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        setContentView(binding.root)
//        val id2 = data?.getStringExtra("id")
//        Log.d("lsy", "인텐트속 id 값은 넘어왔을까?: $id2")
//        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
//            Toast.makeText(this@MainActivity, "로그인이 완료되었습니다", Toast.LENGTH_SHORT).show()
//        }
//    }
}