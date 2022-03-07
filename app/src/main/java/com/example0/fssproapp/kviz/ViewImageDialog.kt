package com.example0.fssproapp.kviz

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.menu_item.*
import java.lang.IllegalStateException

class ViewImageDialog(imageView:ImageView):DialogFragment() {
    lateinit var img:ImageView
    init {
        img = imageView
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{

            val builder = AlertDialog.Builder(it)

                .setView(img)
                .setPositiveButton("Close", DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                })

            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }
}