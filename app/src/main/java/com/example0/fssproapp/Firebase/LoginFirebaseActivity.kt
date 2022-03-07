package com.example0.fssproapp.Firebase

import android.app.Notification
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example0.fssproapp.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.dynamiclinks.ktx.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login_firebase.*

class LoginFirebaseActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_firebase)
        auth = Firebase.auth
        val testemail = "cakic@gmail.com"
        val testpass = "1234567"

        button11.setOnClickListener {
            auth.createUserWithEmailAndPassword(testemail,testpass)
                .addOnCompleteListener(this){
                    task->
                    if(task.isSuccessful){
                        val user = auth.currentUser
                        Log.d("USERRR: ",user!!.email)
                    }else{
                        Log.w("ERR USERR: ",task.exception)
                    }
                }
        }
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        user!!.updateProfile(userProfileChangeRequest {
            displayName = "Viktorrr"
        }).addOnCompleteListener {
            task->
            if(task.isSuccessful){
                Log.d("FIREBASE","USER name: "+user.displayName)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 0){

        }
    }
    fun firebaseAuthWithGoogle(idToken:String){

    }

}