package com.example.demoapp.network

import com.example.demoapp.model.login.UserLogin
import com.example.demoapp.model.studentregistration.StudentRegistration
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("meter/tpdil_api/webservices/index.php")
    fun userLogin(
        @Field("apicall") apicall: String,
        @Field("employee_id") employee_id: String,
        @Field("emp_password") emp_password: String
    ):Call<UserLogin>

    @FormUrlEncoded
    @POST("meter/tpdil_api/webservices/index.php")
    fun studentRegistration(
        @Field("apicall") apicall: String,
        @Field("name") name: String,
        @Field("rollno") rollno: String,
        @Field("class") class_12: String,
        @Field("latlong") latlong: String,
        @Field("image") image: String,
    ):Call<StudentRegistration>

     fun syncData(s: String, user: String, password: String): Any
}