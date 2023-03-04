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
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.textfield.TextInputEditText

class StudentRegistration : AppCompatActivity() {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
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
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        initView()
        getLocation()

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

    private fun getLocation() {
        //check location persmission
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                100
            )
            return

        }
        // get lat long
        val location=fusedLocationProviderClient.lastLocation
        location.addOnSuccessListener {
            if (it!=null){
                val textlat=it.latitude.toString()
                txtLatLong.text=textlat
                println("lat $textlat")
            }
        }
    }
}

