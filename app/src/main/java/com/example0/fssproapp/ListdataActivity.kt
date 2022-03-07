package com.example0.fssproapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example0.fssproapp.database.Exam
import com.example0.fssproapp.database.ExamViewModel
import com.example0.fssproapp.recyclerExam.ExamListAdapter
import kotlinx.android.synthetic.main.activity_listdata.*
import kotlinx.coroutines.InternalCoroutinesApi

class ListdataActivity : AppCompatActivity() {

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listdata)
        var adapter:ExamListAdapter = ExamListAdapter(ExamListAdapter.ExamDiff())
        recycler1.adapter = adapter
        recycler1.layoutManager = LinearLayoutManager(this)
        var examViewModel = ViewModelProvider(this).get(ExamViewModel::class.java)
        examViewModel.getAllExams().observe(this,object:Observer<List<Exam>>{
            override fun onChanged(t: List<Exam>?) {
                adapter.submitList(t)
            }
        })

    }
}