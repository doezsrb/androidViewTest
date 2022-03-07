package com.example0.fssproapp.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example0.fssproapp.database.ExamRepository
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ExamViewModel(application: Application): AndroidViewModel(application) {

    lateinit var mRepository: ExamRepository
    lateinit var allExam:LiveData<List<Exam>>
    init{
        mRepository = ExamRepository(application)
        allExam = mRepository.getAllExams()
    }
    fun getAllExams():LiveData<List<Exam>>{
        return allExam
    }
    fun insert(exam:Exam):Unit{
        mRepository.insert(exam)
    }
}