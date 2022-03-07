package com.example0.fssproapp.auth

import android.accounts.AccountManager
import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example0.fssproapp.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    lateinit var accountType:String
    var PARAM_USER_PASS = "param_user_pass"
    var RETURN_FROM_SERVER = "server"
    lateinit var serverAuthenticator: ServerAuthenticator
    init{
        serverAuthenticator = ServerAuthenticator(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        accountType = intent.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE)
        val spinner: Spinner = findViewById(R.id.monthSpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.months,
            android.R.layout.simple_spinner_item
        ).also{
            arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = arrayAdapter
        }
        val spinner2:Spinner = findViewById(R.id.countrySpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.countries,
            android.R.layout.simple_spinner_item
        ).also{
            arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner2.adapter = arrayAdapter
        }
    }
    @SuppressLint("StaticFieldLeak")
    fun createAccount(view: View){
        val username = registereditTextTextPersonName5.text.toString()
        val password = registereditTextTextPersonName4.text.toString()
        val first_name = registereditTextTextPersonName2.text.toString()
        val last_name = registereditTextTextPersonName3.text.toString()



        object:AsyncTask<Void,Void,Intent>(){
            override fun doInBackground(vararg p0: Void?): Intent {
                val authToken = serverAuthenticator.signUp(username,password,"full")
                var result = Intent()
                if(authToken != "fail"){
                    val data = Bundle().apply {
                        putString(AccountManager.KEY_ACCOUNT_NAME,username)
                        putString(AccountManager.KEY_ACCOUNT_TYPE,accountType)
                        putString(AccountManager.KEY_AUTHTOKEN,authToken)
                        putBoolean(AccountAuthenticator.ADD_ACCOUNT,true)
                        putString(PARAM_USER_PASS,password)
                        putString(AccountAuthenticator.TOKEN_TYPE,"full")
                        putString(RETURN_FROM_SERVER,"success")
                    }
                    result = result.putExtras(data)
                }else{
                    val data = Bundle().apply {
                        putString(RETURN_FROM_SERVER,"fail")
                    }
                    result = result.putExtras(data)
                }


                return result
            }

            override fun onPostExecute(result: Intent?) {
                setResult(RESULT_OK,result)
                finish()
            }
        }.execute()


    }
}