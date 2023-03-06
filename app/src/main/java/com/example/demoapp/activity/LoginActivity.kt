package com.example.demoapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.R
import com.example.demoapp.model.login.UserLogin
import com.example.demoapp.network.RetrofitClient
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
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
                edtUserId.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                edtPassword.setError("Please Enter Password")

            } else {
                RetrofitClient.instance.userLogin("test_login", user, password)
                    .enqueue(object : Callback<UserLogin> {
                        override fun onResponse(
                            call: Call<UserLogin>,
                            response: Response<UserLogin>
                        ) {
                            if (response.code() == 200 && response.body() != null) {
                                val result = response.body()
                                if (result != null) {
                                    result.user_data.username
                                }
                                Log.d("Login", response.body()!!.ResponseMessage.toString())
                                Toast.makeText(
                                    applicationContext,
                                    response.body()!!.ResponseMessage.toString(), Toast.LENGTH_LONG
                                ).show()
                                val intent=Intent(this@LoginActivity,DashBoardActivity::class.java)
                                startActivity(intent)



                            }

                        }

                        override fun onFailure(call: Call<UserLogin>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()

                        }

                    })

            }


        }

    }
}