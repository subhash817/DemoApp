package com.example.demoapp.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey(autoGenerate = true) val id:Int?,
    @ColumnInfo(name = "studentName") val studentName:String?,
    @ColumnInfo(name = "studentClass") val studentClass:String?,
    @ColumnInfo(name = "studentRollNumber") val studentRollNumber:String?,
)
