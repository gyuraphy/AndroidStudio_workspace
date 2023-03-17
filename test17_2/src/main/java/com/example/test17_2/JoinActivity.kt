package com.example.test17_2

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.test17_2.databinding.ActivityJoinBinding
import com.example.test17_2.databinding.ActivityMainBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class JoinActivity : AppCompatActivity() {
    lateinit var binding: ActivityJoinBinding
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //camera request launcher.................
        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.imgSize),
                resources.getDimensionPixelSize(R.dimen.imgSize)
            )
            val option = BitmapFactory.Options()
            option.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, option)
            bitmap?.let {
                binding.userImageView.setImageBitmap(bitmap)
            }
        }

        // 카메라 버튼 클릭시 런처앱실행
        binding.cameraButton.setOnClickListener {
            //camera app......................
            //파일 준비...............
            val timeStamp: String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
            )
            filePath = file.absolutePath
            val photoURI: Uri = FileProvider.getUriForFile(
                this,
                "com.example.ch16_provider.fileprovider",
                file
            )

            // 공유 프레퍼런스 파일에 값을 저장하는 부분!
            // imgLoadTest 이름으로 파일 저장
            val pref = getSharedPreferences("profile", Context.MODE_PRIVATE)

            // 키, 값 형태로 저장하는 방식
            // commit을 하게 되면, 실제 저장소 파일에 저장
            pref.edit().run {
                putString("imgfileUri", photoURI.toString())
                putString("imgfile", filePath)
                commit()
            }
            val resultStr2 : String? = pref.getString("imgUri","값이 없으면 디폴트 값이 옵니다.")
            val result3 = resultStr2.toString()
            Log.d("lsy","imgInfo result3 결과 : $resultStr2")
            Log.d("lsy","imgInfo result3 결과 : $result3")

            val uriTest = Uri.fromFile(File(filePath))
            Log.d("lsy"," filePath 경로 찍어보기"+uriTest.toString())

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            requestCameraFileLauncher.launch(intent)

        }

        binding.joinButton.setOnClickListener {
            val userEmail = binding
            val pref = getSharedPreferences("profile", Context.MODE_PRIVATE)
            pref.edit().run {
                putString("userEmail", binding.inputEmail.text.toString())
                putString("userPwd", binding.inputPwd.text.toString())
                commit()
            }
            // 20230317 추가 작업 -> intent에 담아 메인페이지로 전환 -> 메인페이지에서 공유프레퍼런스 안의 키값 밸류값으로 데이터 뿌려보기!
        }

    }

    // 프로필 사이즈 조절
    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        try {
            var inputStream = contentResolver.openInputStream(fileUri)

            //inJustDecodeBounds 값을 true 로 설정한 상태에서 decodeXXX() 를 호출.
            //로딩 하고자 하는 이미지의 각종 정보가 options 에 설정 된다.
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //비율 계산........................
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1
        //inSampleSize 비율 계산
        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}