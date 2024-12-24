package com.example.studentmanroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    private val onItemClicked: (Student) -> Unit,
    private val onCheckedChange: (Student, Boolean) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    private val students = mutableListOf<Student>()
    private val selectedStudents = mutableSetOf<Student>()

    fun updateData(newStudents: List<Student>) {
        students.clear()
        students.addAll(newStudents)
        notifyDataSetChanged()
    }

    fun getSelectedStudents(): List<Student> = selectedStudents.toList()

    inner class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mssvTextView = view.findViewById<TextView>(R.id.textMSSV)
        private val fullNameTextView = view.findViewById<TextView>(R.id.textFullName)
        private val checkBox = view.findViewById<CheckBox>(R.id.checkbox)

        fun bind(student: Student) {
            mssvTextView.text = student.mssv
            fullNameTextView.text = student.fullName
            checkBox.setOnCheckedChangeListener(null)
            checkBox.isChecked = selectedStudents.contains(student)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                onCheckedChange(student, isChecked)
                if (isChecked) selectedStudents.add(student) else selectedStudents.remove(student)
            }
            itemView.setOnClickListener { onItemClicked(student) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount(): Int = students.size
}
