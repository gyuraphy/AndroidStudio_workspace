package com.example.test3_2
// 전역 변수(최고 상위 영역: 선언과 동시에 초깃값 할당)
val name= "이상용"
val name2: String = "이상용2"
val num = 10
val data5: Int by lazy {
    println("lazy 실행!")
    10
}

class MyClass2 {
    // 지역 변수(클래스 안 영역: 선언과 동시에 초깃값 할당)
    // var name4 <- 오류
    var name = "심상용"
    var age = 40
    val name2 = "배상용"
    lateinit var data1: String
}

fun main() {
    // 가변 길이의 List, Map
    val data11 = mutableMapOf<String, Any>()
    data11.set("key", "value")
    // companion 타입 지정후 클래스 사용가능
    data11.set("key1", MyClass2())
    println(data11.get("key"))

    val data10 = mutableListOf<Int>()
    data10.add(11)
    data10.add(22)
    println(data10[0])


    val data8 = intArrayOf(10,20,30)
    val data9 = booleanArrayOf(true,false)
    println(
        """
Array size: ${data8.size}
Array data: ${data9[0]}, ${data8[1]}, ${data8.get(2)}
            """
    )


    val data7: Array<Int> = Array(3,{0})
    data7[0] = 10
    data7[1] = 20
    data7.set(2, 30)

    println(
        """
Array size: ${data7.size}
Array data: ${data7[0]}, ${data7[1]}, ${data7.get(2)}

            """
)
    
    fun some(data1: Int, data2: Int = 10): Int {
        return data1*data2
    }
    println(some(100))


    fun sum2():Nothing {
        throw Exception()
    }

    var apple: Int? = 10
    apple = 20
    apple = null

    var data6: Nothing? = null

    var data2: Any = 10
    var data3: Any = "String"
    var data4: Any = MyClass2()

    fun test3() {
        println(data2)
        println(data3)
        println(data4)
    }
    var testxx = test3()
    println(testxx)



    fun addSum(no: Int):Int {
        var sum = 0
        for(i in 1..no) {
            sum += i
        }
        return sum
    }

    val name = "yong"
    println("name: $name, sum: ${   addSum(10)}")

    // 함수 내부에서는 선언만하는 것도 가능
    var name5: String

    // MyClass2 myclass2 = new Myclass2();
    var myclass2 = MyClass2()

    // val 재할당 할수 없음
    // myclass2.name2 = "재상용"
    println(myclass2.name)
    println(myclass2.age)
    println(myclass2.name2)
    println("숫자야 나와라 123")
    var yapp : String = "얍"
    println("yapp의 값: "+ yapp)
    println("lazy 테스트 및 결과값 재할당하여 연산")
    println(data5 + 10)

    var data1: Int = 10
    data1 = data1 + 10
// 객체만 .연산자 사용가능
    data1 = data1.plus(10)
    println(data1)

    val str1 = "hi \n yong"
    val str2 = """
    hi
	world
	"""
    println("str1: $str1")
    println("str2: $str2")
}