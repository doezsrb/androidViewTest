package com.example0.fssproapp.profile

import android.accounts.AccountManager
import android.content.Context
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example0.fssproapp.MainActivity
import com.example0.fssproapp.R
import kotlinx.android.synthetic.main.activity_profile.*
import org.json.JSONObject

class  ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val accManager = AccountManager.get(this)
        val accounts = accManager.getAccountsByType("com.fssproapp.auth")


        val sharedPref = getSharedPreferences(getString(R.string.shared_preferences_name), Context.MODE_PRIVATE)
        val selected_acc = sharedPref.getInt("selected_account",accounts.size-1)
        textView61.text = accounts[selected_acc].name
        textView62.text = accManager.peekAuthToken(accounts[selected_acc],"full")


        logout1.setOnClickListener {
            accManager.removeAccount(accounts[0],this,null,null)
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
    }
}