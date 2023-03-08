package ExTest0221

import java.util.Scanner

data class User(val id: String, val pw: String, val email: String, var phone: String)

class Login() {
    companion object {
            fun loginTest(user: User){
                if(user.id.equals("admin") && user.pw.equals("1234")){
                    println("adimin 로그인 성공")
                }else{

                }
            }
    }
}

fun main(arg: Array<String>) {

    val RegisterSc: Scanner = Scanner(System.`in`)
    print("아이디를 입력해주세요: ")
    var id = RegisterSc.nextLine()
    print("비밀번호를 입력해주세요: ")
    var pw = RegisterSc.nextLine()
    print("이메일을 입력해주세요: ")
    var email = RegisterSc.nextLine()
    print("전화번호를 입력해주세요: ")
    var phone = RegisterSc.nextLine()
    println("아이디: $id, 비밀번호: $pw, 이메일: $email, 전화번호: $phone")
    println("회원가입이 완료되었습니다")
    var user:User = User(id, pw, email, phone)

    Login.loginTest(user);
}


