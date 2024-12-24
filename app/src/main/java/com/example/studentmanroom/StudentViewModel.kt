package com.example.studentmanroom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    val studentDao = database.studentDao()
    val allStudents: LiveData<List<Student>> = studentDao.getAllStudents()

    fun searchStudents(query: String): LiveData<List<Student>> = studentDao.searchStudents("%$query%")

    fun insertStudent(student: Student) = viewModelScope.launch {
        studentDao.insertStudent(student)
    }

    fun updateStudent(student: Student) = viewModelScope.launch {
        studentDao.updateStudent(student)
    }

    fun deleteStudents(ids: List<String>) = viewModelScope.launch {
        studentDao.deleteStudents(ids)
    }
}
