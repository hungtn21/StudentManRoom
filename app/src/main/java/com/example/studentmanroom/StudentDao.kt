package com.example.studentmanroom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDao {
    @Query("SELECT mssv, fullName FROM students")
    fun getAllStudents(): LiveData<List<Student>>

    @Query("SELECT mssv, fullName FROM students WHERE mssv LIKE :query OR fullName LIKE :query")
    fun searchStudents(query: String): LiveData<List<Student>>

    @Insert
    suspend fun insertStudent(student: Student)

    @Update
    suspend fun updateStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM students WHERE mssv IN (:ids)")
    suspend fun deleteStudents(ids: List<String>)
}
