package com.example0.fssproapp.kviz

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import com.example0.fssproapp.R
import kotlinx.android.synthetic.main.activity_quiz.*
data class Question(val quest:String,val answers:Array<String>,val img:Int)
class QuizActivity : AppCompatActivity() {

    var current_quest = 0
    val questions = listOf(Question("Lorem IpsumLorem IpsumLorem IpsumLorem IpsumLorem Ipsum" +
            "Lorem IpsumLorem IpsumLorem IpsumLorem IpsumLorem Ipsum" +
            "Lorem IpsumLorem IpsumLorem IpsumLorem IpsumLorem Ipsum",arrayOf("-1","5","7"),R.drawable.back),
        Question("222Lorem IpsumLorem IpsumLorem IpsumLorem IpsumLorem Ipsum" +
                "Lorem IpsumLorem IpsumLorem IpsumLorem IpsumLorem Ipsum" +
                "Lorem IpsumLorem IpsumLorem IpsumLorem IpsumLorem Ipsum",arrayOf("2","4"),R.drawable.rsz_arrowdown),
        Question("33333Lorem IpsumLorem IpsumLorem IpsumLorem IpsumLorem Ipsum" +
                "Lorem IpsumLorem IpsumLorem IpsumLorem IpsumLorem Ipsum" +
                "Lorem IpsumLorem IpsumLorem IpsumLorem IpsumLorem Ipsum",arrayOf("-2","3","4"),R.drawable.arrowup))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)


        startQuiz(questions[current_quest])
    }
    fun startQuiz(question:Question){
        textView57.text = "${current_quest+1} of ${questions.size}"
        textView56.text = question.quest
        textView59.setOnClickListener {
            val imageView = ImageView(this)
            imageView.setImageDrawable(resources.getDrawable(question.img))
            ViewImageDialog(imageView).show(supportFragmentManager.beginTransaction(),"test")
        }


        for(i in 1..question.answers.size){
            var btn = getButton(i.toString())
            btn.text = question.answers[i-1]
        }
        if(question.answers.size < 3){
            var btn = getButton("3")
            btn.visibility = View.INVISIBLE
        }else{
            var btn = getButton("3")
            btn.visibility = View.VISIBLE
        }

        if((current_quest-1) < 0){
            button14.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
            button14.isClickable = false

        }else{
            button14.setBackgroundColor(resources.getColor(R.color.colorPrimary))
            button14.isClickable = true
        }
        if((current_quest+1) > questions.size-1){
            button13.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
            button13.isClickable = false

        }else{
            button13.setBackgroundColor(resources.getColor(R.color.colorPrimary))
            button13.isClickable = true
        }


    }
    fun prevQuest(view:View){
        current_quest--
        startQuiz(questions[current_quest])
    }
    fun nextQuest(view:View){

        current_quest++
        startQuiz(questions[current_quest])

    }
    fun getButton(i:String):Button{
        val id = resources.getIdentifier("check"+i,"id",packageName)
        val btncheck = findViewById<Button>(id)
        return btncheck
    }
}
