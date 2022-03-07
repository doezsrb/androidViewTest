package com.example0.fssproapp.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example0.fssproapp.database.ExamDB
import com.example0.fssproapp.database.ExamDao
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ExamRepository(application: Application) {
    lateinit var mExamDao: ExamDao;
    lateinit var allExam:LiveData<List<Exam>>;
    init{
        val db = ExamDB.getDatabase(application)
        mExamDao = db.examDao()
        allExam  = mExamDao.getAll()
    }
    fun getAllExams():LiveData<List<Exam>>{
        return allExam
    }

    fun insert(exam:Exam):Unit{
        ExamDB.databaseWriteExecutor.execute(object:Runnable{
            override fun run() {
                mExamDao.insert(exam)
            }
        })
    }

}