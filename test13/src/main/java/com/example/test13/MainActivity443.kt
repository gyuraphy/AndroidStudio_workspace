package com.example.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.example.test13.databinding.ActivityMain443Binding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class MainActivity443 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain443Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
//            var sum = 0L
//            var time = measureTimeMillis {
//                for(i in 1..2_000_000_000){
//                    sum += i
//                }
//            }
//            Log.d("kkang","time : $time")
//            binding.resultView.text = "sum : $sum"

            val handler=object: Handler(){
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    binding.resultView.text = "sum : ${msg.arg1}"
                }
            }

//            thread {
//                var sum = 0L
//                var time = measureTimeMillis {
//                    for (i in 1..2_000_000_000) {
//                        sum += i
//                    }
//                    val message = Message()
//                    message.arg1=sum.toInt()
//                    handler.sendMessage(message)
//                }
//                Log.d("kkang", "time : $time")
//            }

            val channel = Channel<Int>()

            val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
            backgroundScope.launch {
                var sum = 0L
                var time = measureTimeMillis {
                    for (i in 1..2_000_000_000) {
                        sum += i
                    }
                }
                Log.d("kkang", "time : $time")
                channel.send(sum.toInt())
            }

            val mainScope= GlobalScope.launch(Dispatchers.Main) {
                channel.consumeEach {
                    binding.resultView.text = "sum : $it"
                }
            }


        }
    }
}

/*
싱글 탑 예제
액티비티가 화면에 출력되는 상황에서 똑같은 액티비티를 인텐트로 다시 실행하는 예
대표적인 예 -> 알림
카카오톡 채팅에서 알림 메세지가 온 경우 가정
채팅 화면: ChatActivity
다른 친구에게 메세지가 도착(새 메세지)

알림이 뜨면 - > 터치해서 -> 새 채팅방(ChatActivity)
스탠다드로 하게 되면, 새 인텐트 객체 계속 만들어야 하지만
싱글탑 모드로 하게되면, 새로 인텐트 객체를 만들 필요가 없음

ANR 문제 코루틴소개
ANR(Activity Not Response)
액티비티로 구성한 앱 화면은 사용자 이벤트에 빠르게 반응 해야함
(백쪽에 응답하는 희망속도 200ms~300ms,
만약 조금 느려도 500ms, 1000ms 정도로 만들자!)

액티비티가 사용자 이벤트에 5초 이내에 반응하지 않으면 ANR 문제가 발생
액티비티에서 사용자 이벤트를 처리하지 못하는 이유는
액티비티를 실행한 시스템에서 발생한 수행흐름에서 이벤트를 처리를 못하기 때문
예) 오래 걸리는 작업을, 메인스레드(UI스레드) 사용자가 직접 처리 못하기 때문
예2) 계산을 20억 정도 더하는 작업 중에, 화면에 에디트 텍스트 뷰를 클릭해도 무반응

참고)
시간이 오래 걸리는 작업을 서비스 컴포넌트로 처리하면 안되나?
가능하긴함, 서비스가 하는 역할 백에서 작업을 진행하는 부분
액티비티 작업: 화면을 출력하는 부분
좋은 방법은 아님
화면에 담당하는 부분이 아무리 오래 걸려도, 액티비티가 담당해야한다

코루틴(코틀린에서)
1.1 버전부터 코루틴을 지원하기 시작했고,
1.3 버전부터 공식으로 지원
coroutine -> co: 함께, routine: 작업 처리
즉 함께 작업을 처리한다 의미
-> 비동기 처리 방식과 같다

스레드-핸들러 방식으로 처리하고 있는데, API30 레벨에서 deprecated가 되어서 방식만 보고 넘어가기

일단, 간단한 코루틴 설정만 확인하고 넘어감
샘플코드)
작업이 크게 2분류로 나뉨 1:백, 2:메인
스코프를 디스패처(Dispatchers)로 정의
디스패처: 각 스코프에서 구동한 코루틴이 어디에서 동작하는지를 나타내기 위해서
Dispatchers.Main 는 메인 스레드에서 동작
결론, UI를 변경 가능
사용자 이벤트를 처리도하고(화면을 그리는 작업), 빨리 끝나는 작업(오래걸린 작업의 결과값만 뷰에 붙이기)을 맡기는게 좋다.

Dispatchers.Default
오래걸리는 작업 수행

Channel 은 큐 알고리즘과 비슷(First In First Out)
Channel 의 send() 함수로 데이터를 전달하면
이 데이터를 받는 코루틴에서 receive(), consumeEach() 함수 등으로 데이터를 받는다
val channel = Channel<Int>()

val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
backgroundScope.launch {
    var sum = 0L
    var time = measureTimeMillis {
        for (i in 1..2_000_000_000) {
            sum += i
        }
    }
    Log.d("kkang", "time : $time")
    channel.send(sum.toInt())
}

val mainScope= GlobalScope.launch(Dispatchers.Main) {
    channel.consumeEach {
        binding.resultView.text = "sum : $it"
    }
}

액티비티 ANR 문제와 코루틴
디스패처는 이 스코프에서 구동한 코루틴이 어디에서 동작해야 하는지를 나타냅니다.
 Dispatchers.Main: 액티비티의 메인 스레드에서 동작하는 코루틴을 만듭니다.
 Dispatchers.IO: 파일에 읽거나 쓰기 또는 네트워크 작업 등에 최적화되었습니다.
 Dispatchers.Default: CPU를 많이 사용하는 작업을 백그라운드에서 실행합니다.
*/
