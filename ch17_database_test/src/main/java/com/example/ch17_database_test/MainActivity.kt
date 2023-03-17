package com.example.ch17_database_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch17_database_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // 최고 상위 영역 액티비티 안 다른 함수에서 접근하기 용이
    // 주의 할것은 기본 선언하고 초기화를 해줘야하는데
    // lateinit 을 이용하면 해결
    lateinit var binding: ActivityMainBinding
    // 이름 문자열 배열
    var datas: MutableList<String>? = null
    // 나이 문자열을 담는 배열
    var datas2: MutableList<String>? = null
    // 리사이클러뷰를 사용하기 위해 필요한 재료
    lateinit var adapter: MyAdapter

    // savedInstanceState: Bundle? -> 임시로 번들 객체에 담아두는 것
    // onCreate, restoreInstanceState, 3개만 매개변수로 번들 객체를 이용함
    // 임시로 저장하면 액티비티 화면에서 작업했던 것이 화면 회전시에 사라지는데 번들객체에 임시저장가능
    // 단점 앱을 다시 실행하면 사라짐 -> 내부 및 외부 저장소를 이용
    // 현재 SQLite 데이터베이스를 이용
    // 파일 직접 java.io 관련 스트림, 문자열 읽기, 쓰기 작업
    // 공유 프리퍼런스를 이용해서도 가능, 임시로 파일 저장. 현제 트렌디
    // 파이어 베이스로 연동 부분 설명 -> 화요일 시험 -> 프로젝트 관련 설명명

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       // 현재 메인에서 -> 입력 액티비티로 이름 나이를 입력하고 값을 처리하는 부분
       // 입력 액티비티 finish() 로 인텐트에 담아 넘어옴
        val requestLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            // 이름 처리 부분
            it.data!!.getStringExtra("name")?.let {
                datas?.add(it)
                adapter.notifyDataSetChanged()
            }
            // 나이처리 부분
            it.data!!.getStringExtra("age")?.let {
                datas2?.add(it)
                adapter.notifyDataSetChanged()
            }
        }
        binding.mainFab.setOnClickListener {
            // 간단히 다른 액티비티 화면으로 이동
            // 지금은, 후 처리가 들어가 있음
            val intent = Intent(this, AddActivity::class.java)
            requestLauncher.launch(intent)
        }

       // lateinit로 실제 이름, 나이를 담는 배열의 초기화 여기서!
        datas= mutableListOf<String>()
        datas2= mutableListOf<String>()

        //add......................

        val db = DBHelper(this).readableDatabase
        val cursor = db.rawQuery("select * from USER", null)
        cursor.run {
            while(moveToNext()){
                // 첫 컬럼 이름을 값을 읽어서 배열에 담기
                datas?.add(cursor.getString(1))
                // 두번째 컬럼 나이의 값을 읽어서 배열에 담기
                datas2?.add(cursor.getString(2))
            }
        }
        db.close()

       // 리사이클러뷰에서
       // 메인에 리사이클러뷰 어댑터, 레이아웃 매니저를 연결하는 부분
        val layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.layoutManager=layoutManager
        adapter=MyAdapter(datas, datas2)
        binding.mainRecyclerView.adapter=adapter
        binding.mainRecyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if(item.itemId===R.id.menu_main_setting){
//            val intent = Intent(this, SettingActivity::class.java)
//            startActivity(intent)
//        }
//        return super.onOptionsItemSelected(item)
//    }
}