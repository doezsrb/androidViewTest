package com.example0.fssproapp

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example0.fssproapp.profile.ProfileActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.custom_actionbar.*

class MainActivity2 : AppCompatActivity() {
    lateinit var metrics: FrameMetrics
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)



        val recyclerView = findViewById<RecyclerView>(R.id.recyclerMain2)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val items = resources.getStringArray(R.array.menu_drawer)
        val modelItems = mutableListOf<MenuItemModel>()
        for (item in items){
            val modelItem = MenuItemModel(item)
            modelItems.add(modelItem)
        }
        val adapter = MenuAdapter(modelItems)
        recyclerView.adapter = adapter
        val actionbar = LayoutInflater.from(this).inflate(R.layout.custom_actionbar,null)
        val hamburger = actionbar.findViewById<ImageView>(R.id.imageView5)
        hamburger.setOnClickListener {
            drawer1.openDrawer(Gravity.LEFT)
        }
        imageButton.setOnClickListener {
            drawer1.closeDrawer(Gravity.LEFT)
        }
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setCustomView(actionbar,
            androidx.appcompat.app.ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT))
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(resources.getColor(android.R.color.white)))

        val view = supportActionBar!!.customView
        val importFragmentContainer = findViewById<ViewStub>(R.id.fragmentContainer).inflate()
        imageView6.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
        }
    }
}