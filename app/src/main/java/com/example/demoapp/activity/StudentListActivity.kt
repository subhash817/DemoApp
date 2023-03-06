package com.example.demoapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dem.StudentRoomAdapter
import com.example.demoapp.R
import com.example.demoapp.model.login.UserLogin
import com.example.demoapp.network.RetrofitClient
import com.example.demoapp.roomdb.AppDataBase
import com.example.demoapp.roomdb.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentListActivity : AppCompatActivity() {
    lateinit var appDb: AppDataBase
    lateinit var rcvStudentList: RecyclerView
    lateinit var syncData: ImageView
     var arrStudent: ArrayList<Student?>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)
        appDb = AppDataBase.getDataBase(this)
        rcvStudentList = findViewById(R.id.rcvStudentList) as RecyclerView
        syncData = findViewById(R.id.syncData) as ImageView
        GlobalScope.launch(Dispatchers.IO) {
             arrStudent = appDb.studentDao().allStudent as ArrayList<Student?>?

            if (arrStudent!=null && arrStudent!!.size>0){
                val studentRoomAdapter = StudentRoomAdapter(this@StudentListActivity,arrStudent)
                rcvStudentList.layoutManager = LinearLayoutManager(this@StudentListActivity)
                rcvStudentList.setHasFixedSize(true)
                rcvStudentList.adapter = studentRoomAdapter
            }

        }


        syncData.setOnClickListener {
            if (arrStudent!=null && arrStudent!!.size>0){
                arrStudent!!.forEach {
//                    RetrofitClient.instance.syncData("test_login", user, password)
//                        .enqueue(object : Callback<UserLogin> {
//                            override fun onResponse(
//                                call: Call<UserLogin>,
//                                response: Response<UserLogin>
//                            ) {
//                                if (response.code() == 200 && response.body() != null) {
//                                    val result = response.body()
//                                    if (result != null) {
//                                        result.user_data.username
//                                    }
//                                    Log.d("Login", response.body()!!.ResponseMessage.toString())
//                                    Toast.makeText(
//                                        applicationContext,
//                                        response.body()!!.ResponseMessage.toString(), Toast.LENGTH_LONG
//                                    ).show()
//                                    val intent= Intent(this@LoginActivity,DashBoardActivity::class.java)
//                                    startActivity(intent)
//
//
//
//                                }
//
//                            }
//
//                            override fun onFailure(call: Call<UserLogin>, t: Throwable) {
//                                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
//
//                            }
//
//                        })
                }
            }

            }

    }
}