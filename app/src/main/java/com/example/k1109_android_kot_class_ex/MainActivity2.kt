package com.example.k1109_android_kot_class_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.k1109_android_kot_class_ex.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    var view1 = 1
    var int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val binding = ActivityMain2Binding.inflate(layoutInflater)
        // 액티비티 화면 출력
        setContentView(binding.root)
        Log.d("test2", "전"+"${view1}")
        view1 = binding.image2.visibility
        view1 = binding.image2.visibility
        Log.d("test2", "중"+"${view1}")
        binding.btn2.setOnClickListener {
            Log.d("click", "버튼 클릭 테스트")
            binding.image2.visibility = View.INVISIBLE
            Log.d("test2", "후"+"${view1}")
        }
//        val rid : Int = R.id.text1
    }
}