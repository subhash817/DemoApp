package com.example.dem

import com.example.demoapp.roomdb.Student
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R

public class StudentRoomAdapter(var context: Context, var studentList: ArrayList<Student?>?) :
    RecyclerView.Adapter<StudentRoomAdapter.myViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.student_item, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val student= studentList?.get(position)
        if (student != null) {
            holder.txtName?.text ?: student.studentName
        }
        if (student != null) {
            holder.txtClass?.text ?: student.studentClass
        }
        if (student != null) {
            holder.txtRollNumber?.text ?: student.studentRollNumber
        }

    }

    override fun getItemCount(): Int {
        return studentList?.size!!
    }

    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var txtName: TextView
         var txtClass: TextView
        var txtRollNumber: TextView
        init {
            txtName=itemView.findViewById(R.id.txtName)
            txtClass=itemView.findViewById(R.id.txtClass)
            txtRollNumber=itemView.findViewById(R.id.txtRollNumber)
        }


    }
}