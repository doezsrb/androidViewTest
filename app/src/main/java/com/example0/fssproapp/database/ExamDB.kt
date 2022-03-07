package com.example0.fssproapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = arrayOf(Exam::class),version=1)
abstract class ExamDB:RoomDatabase() {
    abstract fun examDao(): ExamDao

    companion object{
        @Volatile var INSTANCE: ExamDB? = null
        val databaseWriteExecutor:ExecutorService = Executors.newFixedThreadPool(4)
        @InternalCoroutinesApi
        fun getDatabase(context: Context): ExamDB {

            if(INSTANCE == null){
                synchronized(ExamDB::class.java){
                    if(INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ExamDB::class.java,"examdb").build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}

