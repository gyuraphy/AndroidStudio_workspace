package com.example.test11

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test11.databinding.ActivityMain354Binding
import com.example.test11.databinding.ActivityViewPagerTestBinding
import com.example.test11.databinding.Item354Binding
import com.example.test11.databinding.ViewitemBinding

class ViewPagerTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 수정1
        setContentView(R.layout.activity_view_pager_test)

        // 바인딩 작업. 뷰 페이저2가 기본항목이고 여기에 데이터 항목들이 주입됨.
        val binding= ActivityViewPagerTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 나중에 데이터 부분은, 파이어베이스에서 데이터를 받아 오게됨
        // 임시데이터
        val datas = mutableListOf<Int>()
        datas.add(R.drawable.kakao01)
        datas.add(R.drawable.kakao02)
        datas.add(R.drawable.kakao03)

        // 수정4
        binding.viewpager.adapter=MyPagerAdapter(datas)
    }

    // 수정 6
    // 뷰홀더: 뷰 객체들을 모아주는 역할
    // 주 생성자의 매개변수: val binding: Item354Binding 코드가 깔끔해지고 작업이 편해짐
    class MyPagerViewHolder(val binding: ViewitemBinding): RecyclerView.ViewHolder(binding.root)

    // 어댑터: 뷰 객체에 데이터를 연결(바인딩)하는 작업
    // 주 생성자의 매개변수 부분이 임의로 만든 List, 나중에는 여기에 외부에서 받은 데이터를 받아 올예정
    class MyPagerAdapter(val datas: MutableList<Int>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        override fun getItemCount(): Int{
            return datas.size
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
                = MyPagerViewHolder(ViewitemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            // MyPagerViewHolder -> 뷰가 Item354Binding.xml
            // Item354Binding.xml 파일안 아이디가 -> itemp_pager_textView
            val binding=(holder as MyPagerViewHolder).binding
            //뷰에 데이터 출력
            binding.itemPagerImgView.setImageResource(datas[position])
//            when(position % 3){
//                0 -> binding.itemPagerImgView.setImageResource(R.drawable.kakao01)
//                1 -> binding.itemPagerImgView.setImageResource(R.drawable.kakao02)
//                2 -> binding.itemPagerImgView.setImageResource(R.drawable.kakao03)
//            }
        }
    }
}