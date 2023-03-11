package com.example.demoapp.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.R

class StudentDetailActivity : AppCompatActivity() {
    lateinit var txtName: TextView
    lateinit var txtClass: TextView
    lateinit var txtRoll: TextView
    lateinit var stdLatLong: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)
        txtName = findViewById(R.id.txtName)
        txtClass = findViewById(R.id.txtClass)
        txtRoll = findViewById(R.id.txtRollNumber)
        stdLatLong = findViewById(R.id.txtLatLong)

        var bundle: Bundle? = intent.extras

        var name = bundle!!.getString("studentName")
        var stdClass = bundle!!.getString("studentClass")
        var rollNo = bundle!!.getString("studentRollNumber")
        //var latLong = bundle!!.getString("studentName")
        txtName.text = name
        txtClass.text = stdClass
        txtRoll.text=rollNo



    }
}