package com.example.test3_2

import com.example.test3_2.MyClass.Companion.data

// 전역 변수(최고 상위 영역: 선언과 동시에 초깃값 할당)
//val name= "이상용"
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

class User {
    var name = "lsy"
    constructor(name: String) {
        this.name = name
    }
    fun someFun() {
        println("name: $name")
    }
}

class User2(name: String, age: Int) {
    init {
        println("객체 생성시 init 실행")
    }
}

class User3(val name: String, age: Int) {
    init {
        println("init안에서는 주생성자 매개변수 사용 가능! $name, $age")
    }
    // var, val로 지정하면 다른 함수에서도 사용 가능
    fun someFun() {
        println("name: $name")
    }
}

class User4(name: String,age: Int, phone: String) {
//    constructor(name: String, age: Int): this(name)

//    constructor(name: String, age: Int, phone: String): this(name)
}

open class Super(name: String) {
}

class Sub: Super {
    constructor(name: String): super(name)
}

open class Super2(name: String) {
    var superData = 10
    fun superFun() {
        println("Super Class - 부모클래스")
    }
}

class Sub2(name: String): Super2(name)

open class Super3(name: String) {
    open var superData = 10
    open fun superFun() {
        println("Super Class - 부모클래스")
    }
}
class Sub3(name: String): Super3(name) {
    override var superData = 20
    override fun superFun() {
        println("자식클래스에서 재정의")
    }
}

open class Super4 {
    var publicData = 10
    protected var protectedData = 10
    private var privateData = 10

    fun superFun() {
        println("Super Class - 부모클래스")
    }
}
class Sub4: Super4() {
    fun subFun() {
        publicData++
        protectedData++
        // privateData++
    }
}

class NonDataClass(val name: String, val age: Int)

data class DataClass(val name: String, val age: Int)

// 익명 클래스, 타입을 명시하지 않으면 외부에서 사용 불가
val obj = object {
    var data = 10
    fun some() {
        println("익명 클래스")
    }
}

open class Super5 {
    open var publicData = 10
    open fun some() {
        println("익명 클래스 사용 테스트")
    }
}

// 익명 클래스: 이름없음. 키워드를 object: 상속할 클래스, 인터페이스 {}
val obj2 = object: Super5() {
    override var publicData = 20
    override fun some() {
        println("익명 클래스 사용 테스트2")
    }
}

class MyClass {
    // 인스턴스 멤버
    var outData = 20
    // static 과 비슷한 효과, 클래스 멤버
    companion object {
        var data = 10
        fun some() {
            println("컴패니언 object 테스트")
        }
    }
}

// 람다 함수 간단 예제(익명함수)
val sum = {no1: Int, no2: Int -> no1 + no2}

// 매개변수 1개인 경우, it 사용전
val sum2 = {no1: Int ->
    println(no1)
    30
}

// 매개변수 1개인 경우, it 사용
// 함수 타입: (Int) -> Unit
val sum3: (Int) -> Unit = {println(it)}

// 매개변수 자리에 함수가 들어감 arg: (int) -> Boolean
fun FunTest(arg: (Int) -> Boolean): () -> String {
    val result = if(arg(10)) {
        "valid"
    } else {
        "invalid"
    }
    return {"고차 함수 테스트: $result"}
}

fun main() {

    // ? 연산자를 이용하여 null 할당 가능
    var data33: String? = null
    // ? 연산자를 사용한 변수에 특정 함수에 접근시 ?. 기호를 사용하거나
    // 또는 !! 예외 발생 연산자를 사용해야함
    // 참고. 널체크가 빠지게 되면 컴파일러가 알려줌
    data33?.length

    // 만약 널이 아니면 해당 길이를 반환하고, 널이면 지정한 값을 대입
    // nvl 함수와 비슷, 삼항다항식과 유사
    data33?.length ?: 0
    println("널 안정성 체크")
    println(data33?.length ?: 0)



    val result5 = FunTest({no -> no > 0})
    println(result5())

   var result3 = sum(10,20)
    println(result3)
    var result4 = sum2(10)
    println("result4의 값 확인: $result4")

    var result2 = MyClass.data
    println(result2)
    MyClass.some()
    var myClass = MyClass()
    myClass.outData

    println(obj2.publicData)
    obj2.some()
    println(obj.toString())

    val nonData1 = NonDataClass("lsy1", 20)
    val nonData2 = NonDataClass("lsy1", 20)
    val dataClass1 = DataClass("lsy2", 30)
    val dataClass2 = DataClass("lsy2", 30)

    println(nonData1.equals(nonData2))
    println(dataClass1.equals(dataClass2))
    println(nonData1.toString())
    println(dataClass1.toString())
    var obj4 = Sub4()

    obj4.publicData


    var obj3 = Sub3("lsy8")
    println(obj3.superData)
    obj3.superFun()


    var obj = Sub2("lsy7")
    println(obj.superData)
    obj.superFun()

    var user5 = User3("lsy5", 40)
    user5.someFun()

    var user2 = User2("lsy2", 30)
    var user3 = User2("lsy3",30)
    var user4 = User2("lsy4", 30)

    var myClass2 = MyClass2()
    myClass2.age

    // 객체 생성시 new 없이 바로 생성자 호출 방법
    val user = User("lsy2")
    user.someFun()

    var data16 = arrayOf<Int>(10, 20, 30)
    for ((index, value) in data16.withIndex()) {
        print(data16[index])
        if (index != data16.size - 1) print(",")
    }
    println("==========================")
    var data15 = arrayOf<Int>(10, 20, 30)
    for (i in data15.indices) {
        print(data15[i])
        if (i != data15.size - 1) print(",")
    }
    println("==========================")
    var sum: Int = 0
    for(i in 1..10) {
        sum += i
    }
    println(sum)

    var data14: Any = "hi"
    var result14 = when(data14) {
        is String -> println("data is String")
        in 1..10 -> println("data is 1..10")
        else -> {
            println("data is not valid")
        }
    }
    println("when 표현식 사용 결과값 테스트: $result14")

    var data13: Any = "hi"
    when(data13) {
        is String -> println("data is String")
        in 1..10 -> println("data is 1..10")
        else -> {
            println("data is not valid")
        }
    }

    var data12 = "hi"
    when(data12) {
        "hi" -> println("data is hi")
        "hello" -> println("data is hello")
        else -> {
            println("data is not valid")
        }
    }

    var data = 10
    val result = if(data>0) {
        println("테스트")
        true
    } else {
        println("else 테스트")
        false
    }
    println("result 결과값 테스트: $result")

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