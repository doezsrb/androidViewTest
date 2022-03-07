package com.example0.fssproapp.auth

import android.accounts.Account
import android.accounts.AccountAuthenticatorActivity
import android.accounts.AccountManager
import android.annotation.SuppressLint
import android.content.ContentProviderClient
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.StringRequest
import com.example0.fssproapp.ForgotPasswordActivity
import com.example0.fssproapp.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AccountAuthenticatorActivity() {
    lateinit var sServerAuthenticator:ServerAuthenticator
    lateinit var mGoogleSignInClient:GoogleSignInClient
    lateinit var accManager:AccountManager
    var ACCOUNT_TYPE = "com.fssproapp.auth"
    var PARAM_USER_PASS = "param_user_pass"
    var RETURN_FROM_SERVER = "server"
    var REQ_REGISTER = 11;
    init{
        sServerAuthenticator = ServerAuthenticator(this)
    }
    var RC_SIGN_IN = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        accManager = AccountManager.get(this)
        textView9.setOnClickListener {
            val mIntent = Intent(baseContext,RegisterActivity::class.java)
            val extras = Bundle().apply{
                putString(AccountManager.KEY_ACCOUNT_TYPE,ACCOUNT_TYPE)
            }
            mIntent.putExtras(extras)
            startActivityForResult(mIntent,REQ_REGISTER)
        }
        button3.setOnClickListener {
            submit()
        }
        val forgotTextView = findViewById<TextView>(R.id.textView7)
        forgotTextView.setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))
        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)
        buttonGoogle.setOnClickListener {
            logIn()
        }
        val account = GoogleSignIn.getLastSignedInAccount(this)

    }
    @SuppressLint("StaticFieldLeak")
    fun submit(){
        val username = editTextTextPersonName.text.toString()
        val password = editTextTextPersonName2.text.toString()

        object:AsyncTask<Void,Void,Intent>(){
            override fun doInBackground(vararg p0: Void?): Intent {
                val authToken = sServerAuthenticator.logIn(username,password,"full")
                var new = 0
                var res = Intent()
                if(authToken != "fail"){
                    val accounts = accManager.accounts
                    for(acc in accounts){
                        if(acc.name == username){
                            new++
                        }
                    }
                    res = res.apply{
                        putExtra(AccountManager.KEY_ACCOUNT_NAME,username)
                        putExtra(AccountAuthenticator.TOKEN_TYPE,"full")
                        putExtra(AccountManager.KEY_ACCOUNT_TYPE,ACCOUNT_TYPE)
                        putExtra(AccountManager.KEY_AUTHTOKEN,authToken)
                        putExtra(PARAM_USER_PASS,password)
                        putExtra(RETURN_FROM_SERVER,"success")
                        if(new == 0){
                            putExtra(AccountAuthenticator.ADD_ACCOUNT,true)
                        }
                    }
                }else{
                    res = res.apply{
                        putExtra(RETURN_FROM_SERVER,"fail")
                    }
                }
                return res
            }

            override fun onPostExecute(result: Intent?) {
                finishLogin(result)
            }
        }.execute()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }else if(requestCode == REQ_REGISTER){
            finishLogin(data)
        }
    }
    fun finishLogin(result:Intent?){
        if(result?.getStringExtra(RETURN_FROM_SERVER) == "fail"){
            Toast.makeText(this,"Try again",Toast.LENGTH_LONG).show()
            return
        }
        val username = result!!.getStringExtra(AccountManager.KEY_ACCOUNT_NAME)
        val password = result.getStringExtra(PARAM_USER_PASS)
        val account = Account(username,result.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE))
        val authToken = result.getStringExtra(AccountManager.KEY_AUTHTOKEN)

        if(result.getBooleanExtra(AccountAuthenticator.ADD_ACCOUNT,false)){
            val authToken = result.getStringExtra(AccountManager.KEY_AUTHTOKEN)
            val tokenType = result.getStringExtra(AccountAuthenticator.TOKEN_TYPE)
            accManager.addAccountExplicitly(account,password,null)
            accManager.setAuthToken(account,tokenType,authToken)

        }else{
            accManager.setPassword(account,password)
        }

        setAccountAuthenticatorResult(result.extras)
        setResult(RESULT_OK,result)
        finish();

    }
    fun logIn(){
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent,RC_SIGN_IN)
    }
    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>){
        try{
            val account = completedTask.getResult(ApiException::class.java)
            Log.d("USER LOGIN: ",account!!.email.toString())
        }catch (e:ApiException){
            Log.w("TAG TAG","SignInResult: failed code="+e.statusCode)

        }
    }
}