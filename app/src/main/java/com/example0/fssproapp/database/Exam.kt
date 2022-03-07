package com.example0.fssproapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exam(@PrimaryKey val uid:Int,
                @ColumnInfo(name="shuffle_questions") val shuffleQuestions:String?,
                @ColumnInfo(name="number_of_questions") val numberOfQuestions:String?,
                @ColumnInfo(name="time_duration") val timeDuration:String?,
                @ColumnInfo(name="explain_answer") val explainAnswer:String?
                )