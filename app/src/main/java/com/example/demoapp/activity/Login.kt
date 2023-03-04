package com.example.demoapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.R
import com.google.android.material.textfield.TextInputEditText

class Login : AppCompatActivity() {
    lateinit var login: Button
    lateinit var edtUserId: TextInputEditText
    lateinit var edtPassword: TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login = findViewById(R.id.btnLogin)
        edtUserId = findViewById(R.id.edtUserCode)
        edtPassword = findViewById(R.id.edtPassword)
        initView()
    }

    fun initView() {


        login.setOnClickListener {
            val user: String = edtUserId.text.toString()
            val password: String = edtPassword.text.toString()
            if (user.isEmpty()) {
                edtUserId.setError("Please Enter User Id")
            } else if (password.isEmpty()) {
                edtPassword.setError("Please Enter Password")

            } else {
                val intent = Intent(this, DashBoard::class.java)
                startActivity(intent)
            }

        }

    }
}