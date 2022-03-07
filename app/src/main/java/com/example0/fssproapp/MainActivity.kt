package com.example0.fssproapp

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.FrameMetrics
import android.view.Window
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import com.example0.fssproapp.Firebase.LoginFirebaseActivity
import com.example0.fssproapp.auth.LoginActivity
import com.example0.fssproapp.auth.RegisterActivity
import com.example0.fssproapp.contact.ContactActivity
import com.example0.fssproapp.kviz.QuizActivity
import com.example0.fssproapp.profile.ProfileActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn

class MainActivity : AppCompatActivity() {
    lateinit var metrics: FrameMetrics
    lateinit var accountManager: AccountManager
    var account:Account? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        accountManager = AccountManager.get(this)
        val accounts = accountManager.getAccountsByType(resources.getString(R.string.type_account))
        if(accounts.size != 0){
            account = accounts[0]
        }

        if(account != null){
            startActivity(Intent(this,MainActivity2::class.java))
            finish()
        }


        val signInButton = findViewById<Button>(R.id.signin)
        signInButton.setOnClickListener {
            startActivity(Intent(this,LoginFirebaseActivity::class.java))

        }


    }

    override fun onResume() {
        super.onResume()
        val accounts = accountManager.getAccountsByType(resources.getString(R.string.type_account))
        if(accounts.isNotEmpty()){
            startActivity(Intent(this,MainActivity2::class.java))
            finish()
        }
    }
}