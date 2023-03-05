package com.example.demoapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dem.StudentRoomAdapter
import com.example.demoapp.R
import com.example.demoapp.roomdb.AppDataBase
import com.example.demoapp.roomdb.Student
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StudentRegistration : AppCompatActivity() {
    lateinit var studentList: Button
    lateinit var btnSubmit: Button
    lateinit var edtName: TextInputEditText
    lateinit var edtClass: TextInputEditText
    lateinit var edtRollNumber: TextInputEditText
    lateinit var txtLatLong: TextView
    lateinit var imgProfile: ImageView
    lateinit var appDb: AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_registration)
        studentList = findViewById(R.id.btnStudentList)
        edtName = findViewById(R.id.edtName)
        edtClass = findViewById(R.id.edtClass)
        edtRollNumber = findViewById(R.id.edtRollNumber)
        txtLatLong = findViewById(R.id.txtLatLong)
        btnSubmit = findViewById(R.id.btnSubmit)
        appDb = AppDataBase.getDataBase(this)
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
            val student = Student(
                null, name, className, rollNumber
            )
            GlobalScope.launch(Dispatchers.IO) {
                appDb.studentDao().addTx(student)
            }
            edtName.text?.clear()
            edtClass.text?.clear()
            edtRollNumber.text?.clear()
            Toast.makeText(this@StudentRegistration, "Successfully Add", Toast.LENGTH_LONG).show()
//            val arrStudent = appDb.studentDao().allStudent as ArrayList<Student?>?
//            for (i in arrStudent!!.indices) {
//                val linearLayoutManager = LinearLayoutManager(applicationContext)
//                rcv.setLayoutManager(linearLayoutManager)
//                val studentRoomAdapter=StudentRoomAdapter(this@StudentRegistration,arrStudent)
//                rcv.adapter=studentRoomAdapter
//            }

        }
        studentList.setOnClickListener {
            val intent = Intent(this, StudentList::class.java)
            startActivity(intent)

        }
    }

}

