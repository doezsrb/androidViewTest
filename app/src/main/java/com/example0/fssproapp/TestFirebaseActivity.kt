package com.example0.fssproapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_test_firebase.*

data class User(val lastname:String = "",val name:String = "")
class TestFirebaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_firebase)
        val db = Firebase.database
        val myRef = db.getReference("users")
        myRef.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val users = ArrayList<User>()
                for(s in snapshot.children){
                    val user = s.getValue(User::class.java) as User
                    users.add(user)
                }
                Log.d("ALL USERS: ",users.toString())
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
        testfirebasebtn.setOnClickListener {
            myRef.child("233").setValue(User("Lorem","Ipsum"))
        }
    }
}