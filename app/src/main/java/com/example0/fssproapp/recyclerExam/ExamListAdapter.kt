package com.example0.fssproapp.recyclerExam

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example0.fssproapp.database.Exam

class ExamListAdapter(diffUtil:DiffUtil.ItemCallback<Exam>): ListAdapter<Exam, ExamHolder>(diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamHolder {
        return ExamHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ExamHolder, position: Int) {
        var current:Exam = getItem(position)
        holder.bind(current)
    }

    class ExamDiff:DiffUtil.ItemCallback<Exam>(){
        override fun areItemsTheSame(oldItem: Exam, newItem: Exam): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Exam, newItem: Exam): Boolean {
            return oldItem.explainAnswer.equals(newItem.explainAnswer)
        }
    }
}