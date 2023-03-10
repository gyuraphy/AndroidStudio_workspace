package com.example.test8_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import com.example.test8_2.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {


    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        Log.d("lsy", "체크박스 클릭")
    }

//    class MyEventHandler : CompoundButton.OnCheckedChangeListener {
//        override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
//            Log.d("lsy", "체크박스 클릭")
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        var state: Int = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val clickMe = binding.checkbox.setOnCheckedChangeListener {
               a, b ->
            if(state ==0 ){
                binding.image.visibility = View.INVISIBLE
                state = 1
            } else {
                binding.image.visibility = View.VISIBLE
                state = 0
            }
        }
        binding.button.setOnLongClickListener {
            if(state ==0 ){
                binding.image.visibility = View.INVISIBLE
                state = 1
            } else {
                binding.image.visibility = View.VISIBLE
                state = 0
            }
            true
        }
//        binding.button.setOnClickListener {
//            if(state ==0 ){
//                binding.image.visibility = View.INVISIBLE
//                state = 1
//            } else {
//                binding.image.visibility = View.VISIBLE
//                state = 0
//            }
//        }

//        2번째 방법
//        binding.checkbox.setOnCheckedChangeListener(this)
//        1번째 방법
//        binding.checkbox.setOnCheckedChangeListener(MyEventHandler())
    }

}