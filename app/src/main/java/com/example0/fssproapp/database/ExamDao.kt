package com.example0.fssproapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExamDao{
    @Query("SELECT * FROM exam")
    fun getAll():LiveData<List<Exam>>

    @Insert
    fun insert(exam:Exam)

    @Insert
    fun insertAll(vararg exams:Exam)

    @Delete
    fun delete(exam:Exam)
}