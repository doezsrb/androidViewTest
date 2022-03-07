package com.example0.fssproapp

import android.animation.ValueAnimator
import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.graphics.drawable.toBitmap
import kotlinx.android.synthetic.main.activity_browse_topics.*

class BrowseTopicsActivity : AppCompatActivity(), View.OnClickListener {
    var change = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse_topics)
        val imgButton = findViewById<ImageView>(R.id.dd1)
        val imgButton2 = findViewById<ImageView>(R.id.dd2)
        imgButton.setOnClickListener(this)
        imgButton2.setOnClickListener(this)


    }

    override fun onClick(p0: View?) {
        var idButt = p0!!.resources.getResourceEntryName(p0.id)
        var imgButton = p0 as ImageView

        if(idButt.startsWith("dd")){
            val parentlayout = p0.parent.parent as LinearLayout

            Log.d("CLICKTRIGGER","CHANGE: "+change.toString())
                if(change){
                    ValueAnimator.ofInt(parentlayout.layoutParams.height,600).apply{
                        duration=600
                        start()
                        addUpdateListener {
                                updatedAnimation->
                            val params = parentlayout.layoutParams
                            params.height = updatedAnimation.animatedValue as Int

                            parentlayout.layoutParams = params

                        }


                    }

                    change = false
                    imgButton.setImageBitmap(resources.getDrawable(R.drawable.arrowup).toBitmap())

                }else{
                    ValueAnimator.ofInt(parentlayout.layoutParams.height,200).apply{
                        duration=600
                        start()
                        addUpdateListener {
                                updatedAnimation->
                            val params = parentlayout.layoutParams
                            params.height = updatedAnimation.animatedValue as Int
                            parentlayout.layoutParams = params
                        }

                    }
                    change = true

                    imgButton.setImageBitmap(resources.getDrawable(R.drawable.rsz_arrowdown).toBitmap())

                }
        }

    }
}