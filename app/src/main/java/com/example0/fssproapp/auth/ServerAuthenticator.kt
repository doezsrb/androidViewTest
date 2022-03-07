package com.example0.fssproapp.auth

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class ServerAuthenticator(context:Context) {
    lateinit var ctx:Context
    init {
        ctx = context
    }
    fun signUp(username:String?,password:String?,type:String?):String{
        val params = HashMap<String,String>().apply{
            put("username",username.toString())
            put("password",password.toString())
            put("type","register")
        }
        val domain = "http://10.0.2.2/androidtokentest/"
        val future = RequestFuture.newFuture<String>()
        val queue = Volley.newRequestQueue(ctx)
        val request = object: StringRequest(Request.Method.POST,domain,future,future){
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }
        queue.add(request)
        return future.get()
    }
    fun logIn(username: String?,password: String?,type:String?):String{
        val params = HashMap<String,String>().apply{
            put("username",username.toString())
            put("password",password.toString())
            put("type","login")
        }
        val domain = "http://10.0.2.2/androidtokentest/"
        val future = RequestFuture.newFuture<String>()
        val queue = Volley.newRequestQueue(ctx)
        val request = object: StringRequest(Request.Method.POST,domain,future,future){
            override fun getParams(): MutableMap<String, String> {
                return params
            }
        }
        queue.add(request)
        return future.get()
    }
}