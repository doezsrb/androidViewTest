package com.example0.fssproapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example0.fssproapp.database.Exam
import com.example0.fssproapp.database.ExamRepository
import kotlinx.android.synthetic.main.activity_create_exam.*
import kotlinx.coroutines.InternalCoroutinesApi

class CreateExamActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_exam)

        rangeSlider1.addOnChangeListener { slider, value, fromUser ->
            nqText.text = value.toInt().toString()
        }
        createButt.setOnClickListener(this)
        for(i in 1..7){
            val id = resources.getIdentifier("checkbutt"+i.toString(),"id",packageName)
            val btncheck = findViewById<Button>(id)
            if(btncheck.getTag().toString() != "disabled"){
                btncheck.setOnClickListener(this)
            }else{
                btncheck.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
                btncheck.setTextColor(resources.getColor(android.R.color.white))
                btncheck.isClickable = false
            }
        }
    }

    @InternalCoroutinesApi
    override fun onClick(p0: View?) {
        val idButt = p0!!.resources.getResourceEntryName(p0.id)
        val butt = p0 as Button
        if(idButt.startsWith("checkbutt")){
            if(butt.tag == "false"){
                butt.setBackgroundColor(resources.getColor(R.color.colorPrimary))
                butt.setTextColor(resources.getColor(android.R.color.white))
                butt.setTag("true")
            }else{
                butt.setBackgroundColor(resources.getColor(android.R.color.white))
                butt.setTextColor(resources.getColor(android.R.color.black))
                butt.setTag("false")
            }
        }else if(idButt == "createButt"){
            val shuffle = switchShuffle.isChecked.toString()
            val nq = nqText.text
            var time = "empty"
            for(i in 1..7){
                val id = resources.getIdentifier("checkbutt"+i.toString(),"id",packageName)
                val btncheck = findViewById<Button>(id)
                if(btncheck.getTag() == "true"){
                    time = btncheck.text.toString()
                }
            }
            val exp = switchShuffle2.isSelected.toString()

            val exam = Exam(1,shuffle,nq.toString(),time,exp)
            val repository = ExamRepository(this.application)
            repository.insert(exam)

        }
    }
}