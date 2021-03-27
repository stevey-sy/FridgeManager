package com.example.fridgemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.fridgemanager.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    var auth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        binding.emailLoginButton.setOnClickListener {
            signinAndSignup()
        }
    }
    // 로그인, 회원가입을 처리하는 메소드
    fun signinAndSignup() {
        auth?.createUserWithEmailAndPassword(binding.emailEdittext.text.toString(), binding.passwordEdittext.text.toString())?.addOnCompleteListener {
            task ->
            if (task.isSuccessful) {
                // 유저 계정 생성
                moveMainPage(task.result?.user)
            } else if (!task.exception?.message.isNullOrEmpty()) {
                // 에러 메세지 출력
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
            } else {
                // 회원 가입도 아니고(기존에 존재하는 계정), 에러도 아닐 경우는
                // 로그인 로직으로 진행
                signinEmail()
            }
        }
    }
    // 로그인 메소드
    fun signinEmail() {
        auth?.signInWithEmailAndPassword(binding.emailEdittext.text.toString(), binding.passwordEdittext.text.toString())?.addOnCompleteListener {
                task ->
            if (task.isSuccessful) {
                // id 와 password 가 일치했을 때 로그인 완료.
                // 메인페이지로 이동
                moveMainPage(task.result?.user)
            } else {
                // 일치하지 않았을 때
                Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun moveMainPage(user : FirebaseUser?) {
        if(user !=null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}