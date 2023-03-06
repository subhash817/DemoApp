package com.example.demoapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.demoapp.R

class DashBoardActivity : AppCompatActivity() {
    lateinit var studentRegistration: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        studentRegistration = findViewById(R.id.cvStudentRegistration)
        initView()


    }

    fun initView() {
        studentRegistration.setOnClickListener {
            val intent = Intent(this, StudentRegistrationActivity::class.java)
            startActivity(intent)
        }

    }
}