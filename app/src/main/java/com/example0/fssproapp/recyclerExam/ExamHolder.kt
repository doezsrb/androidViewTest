package com.example0.fssproapp.recyclerExam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example0.fssproapp.R
import com.example0.fssproapp.database.Exam

class ExamHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    lateinit var shuffleText:TextView
    lateinit var numberquest:TextView
    lateinit var timeduration:TextView
    lateinit var explainanswer:TextView
    init{
        shuffleText = itemView.findViewById(R.id.textView40)
        numberquest = itemView.findViewById(R.id.textView55)
        timeduration = itemView.findViewById(R.id.textView54)
        explainanswer = itemView.findViewById(R.id.textView42)
    }
    fun bind(exam: Exam):Unit{
        shuffleText.text = exam.shuffleQuestions
        numberquest.text = exam.numberOfQuestions
        timeduration.text = exam.timeDuration
        explainanswer.text = exam.explainAnswer
    }
    companion object{
        fun create(parent:ViewGroup):ExamHolder{
            var view:View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item,parent,false)
            return ExamHolder(view)
        }
    }
}