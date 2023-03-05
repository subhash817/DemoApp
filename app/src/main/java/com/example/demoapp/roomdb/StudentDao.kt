package com.example.demoapp.roomdb

import androidx.room.*

@Dao
interface StudentDao {
    @get:Query("select *from student_table")
    val allStudent: List<Student?>
    @Insert
    fun addTx(student: Student)
//    @Query("SELECT*FROM student_table")
//    fun getAll(): List<Student>
//
//    @Query("SELECT * FROM student_table WHERE studentRollNumber LIKE :roll LIMIT 1")
//    suspend fun findByRoll(roll: Int): Student
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insert(student: Student)
//
//    @Delete
//    suspend fun delete(student: Student)
//
//    @Query("DELETE FROM student_table")
//    suspend fun deleteAll()
}