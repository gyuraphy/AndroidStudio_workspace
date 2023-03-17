package com.example.ch17_database_test

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.ch17_database_test.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.menu_add_save ->{
            //add........................
            val name = binding.addName.text.toString()
            val age = binding.addAge.text.toString()
            val db = DBHelper(this).writableDatabase
            db.execSQL(
                "insert into USER (name, age) values (?,?)",
                arrayOf<String>(name, age)
            )
            db.close()
            val intent = intent
            intent.putExtra("name", name)
            intent.putExtra("age", age)
            setResult(Activity.RESULT_OK, intent)
            finish()
            true
        }
        else -> true
    }
}