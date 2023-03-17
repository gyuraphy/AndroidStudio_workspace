package com.example.ch17_database_test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ch17_database_test.databinding.ItemRecyclerviewBinding

// 뷰 홀더: 뷰 객체들을 묶어주는 역할, 목록의 요소
class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

// 어댑터
class MyAdapter(val datas: MutableList<String>?, val datas2: MutableList<String>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding=(holder as MyViewHolder).binding
        binding.itemDataName.text= datas!![position]
        binding.itemDataAge.text= datas2!![position]
    }
}