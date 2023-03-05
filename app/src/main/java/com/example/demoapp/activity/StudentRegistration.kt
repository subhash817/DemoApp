package com.example.demoapp.activity

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.demoapp.R
import com.google.android.material.textfield.TextInputEditText

class StudentRegistration : AppCompatActivity() {
    lateinit var studentList: Button
    lateinit var btnSubmit: Button
    lateinit var edtName: TextInputEditText
    lateinit var edtClass: TextInputEditText
    lateinit var edtRollNumber: TextInputEditText
    lateinit var txtLatLong: TextView
    lateinit var imgProfile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_registration)
        studentList = findViewById(R.id.btnStudentList)
        edtName = findViewById(R.id.edtName)
        edtClass = findViewById(R.id.edtClass)
        edtRollNumber = findViewById(R.id.edtRollNumber)
        txtLatLong = findViewById(R.id.txtLatLong)
        btnSubmit = findViewById(R.id.btnSubmit)
        initView()

    }

    fun initView() {
        btnSubmit.setOnClickListener {
            val name: String = edtName.text.toString()
            val className: String = edtClass.text.toString()
            val rollNumber: String = edtRollNumber.text.toString()
            if (name.isEmpty()) {
                edtName.setError("Please Enter Name")

            } else if (className.isEmpty()) {
                edtClass.setError("Please Enter Class")

            } else if (rollNumber.isEmpty()) {
                edtRollNumber.setError("Please Enter Roll Number")

            }


        }
        studentList.setOnClickListener {
            val intent = Intent(this, StudentList::class.java)
            startActivity(intent)
        }
    }

}

