package com.example0.fssproapp.contact

import android.Manifest
import android.accounts.AccountManager
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example0.fssproapp.R
import kotlinx.android.synthetic.main.activity_contact.*


class ContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)


        val intent = AccountManager.newChooseAccountIntent(
            null,
            null,
            arrayOf("com.google"),
            false,
            null,
            null,
            null,
            null)
        startActivityForResult(intent, 200)

    }
    fun sendEmail(view: View){
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc82"
            putExtra(Intent.EXTRA_EMAIL,editTextTextPersonName5.text)
            putExtra(Intent.EXTRA_SUBJECT,"subject")
            putExtra(Intent.EXTRA_TEXT,editTextTextMultiLine.text)
        }
        startActivity(Intent.createChooser(emailIntent,"Choose email app"))
    }
}