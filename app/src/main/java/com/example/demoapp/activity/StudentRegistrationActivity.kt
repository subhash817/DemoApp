package com.example.demoapp.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.demoapp.R
import com.example.demoapp.roomdb.AppDataBase
import com.example.demoapp.roomdb.Student
import com.example.demoapp.utils.GPSTracker
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StudentRegistrationActivity : AppCompatActivity() {
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    lateinit var studentList: Button
    lateinit var btnSubmit: Button
    lateinit var edtName: TextInputEditText
    lateinit var edtClass: TextInputEditText
    lateinit var edtRollNumber: TextInputEditText
    lateinit var txtLatLong: TextView
    lateinit var imgProfile: ImageView
    lateinit var appDb: AppDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        var cameraIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    imageUri = ImagePicker.getImageFromResult(this, result.resultCode, data)
                    startCropImageActivity(imageUri)


                }
            }
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

        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            getLocationPoint()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
    }

    private fun getLocationPoint() {
        try {
            val gpsTracker = GPSTracker(this)
            if (gpsTracker != null && gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude()
                longitude = gpsTracker.getLongitude()

                txtLatLong.text = "".plus(latitude).plus(",").plus(longitude)
            } else {
                // Can't get location.
                // GPS or network is not enabled.
                // Ask user to enable GPS/network in settings.
                gpsTracker!!.showSettingsAlert()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    fun openCameraToCapture() {
        val cameraIntent = ImagePicker.getCameraIntent(this)
        cameraIntentLauncher.launch(cameraIntent)

    }
    fun initView() {
        btnSubmit.setOnClickListener {
            val name: String = edtName.text.toString()
            val className: String = edtClass.text.toString()
            val rollNumber: String = edtRollNumber.text.toString()
            val latlong: String = txtLatLong.text.toString()
            if (name.isEmpty()) {
                edtName.setError("Please Enter Name")

            } else if (className.isEmpty()) {
                edtClass.setError("Please Enter Class")

            } else if (rollNumber.isEmpty()) {
                edtRollNumber.setError("Please Enter Roll Number")

            }
            else if (latlong.isEmpty()){

            }
            else{
                val student = Student(
                    null, name, className, rollNumber
                )
                GlobalScope.launch(Dispatchers.IO) {
                    appDb.studentDao().addTx(student)
                }
                edtName.text?.clear()
                edtClass.text?.clear()
                edtRollNumber.text?.clear()

                Toast.makeText(this@StudentRegistrationActivity, "Successfully Add", Toast.LENGTH_LONG)
                    .show()
            }




        }
        studentList.setOnClickListener {
            val intent = Intent(this, StudentListActivity::class.java)
            startActivity(intent)

        }
    }

    fun checkPermission(permission: String): Boolean {
        when {
            ContextCompat.checkSelfPermission(
                this,
                permission
            ) == PackageManager.PERMISSION_GRANTED -> {
                return true
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                permission
            ) -> {
                requestPermissionLauncher.launch(
                    permission
                )
                return false
            }

            else -> {
                requestPermissionLauncher.launch(
                    permission
                )
                return false
            }
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getLocationPoint()
                Log.i("Permission: ", "Granted")
            } else {
                Log.i("Permission: ", "Denied")
            }
        }


}

