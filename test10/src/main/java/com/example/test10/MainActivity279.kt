package com.example.test10

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import com.example.test10.databinding.ActivityMain279Binding

class MainActivity279 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 바인딩 계속 사용할 것
        // 데이팅 앱에서는, R.id 형식으로 되어있음
        val binding = ActivityMain279Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
        DatePickerDialog(this, object: DatePickerDialog.OnDateSetListener {
            override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                Log.d("lsy", "year : $p1, month : ${p2+1}, dayOfMonth : $p3")
            }
        }, 2023, 2, 10).show()
        }
        binding.button2.setOnClickListener {
        TimePickerDialog(this, object: TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
//                Log.d("lsy", "time : $p1, minute : $p2")
                Toast.makeText(this@MainActivity279, "$p1 시 $p2 분 입니다", Toast.LENGTH_LONG).show()

            }
        }, 15, 0, true).show()
        }
    }
}