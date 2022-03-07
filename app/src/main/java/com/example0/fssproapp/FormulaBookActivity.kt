package com.example0.fssproapp

import android.annotation.SuppressLint
import android.app.ActionBar
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_formula_book.*

class FormulaBookActivity : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formula_book)
        setSupportActionBar(toolbar2)
        val actionbar = LayoutInflater.from(this).inflate(R.layout.custom_actionbar_back,null)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(actionbar,
            androidx.appcompat.app.ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT))
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(android.R.color.white)))
        val customToolbar = supportActionBar!!.customView

        customToolbar.findViewById<ImageView>(R.id.imageViewBack1).setOnClickListener {
            finish()
        }
    }
}