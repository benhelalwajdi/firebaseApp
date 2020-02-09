package com.wbh.firebaseapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.wbh.firebaseapp.R
import com.wbh.firebaseapp.Services.UserService

class MainActivity : AppCompatActivity() {


    private var firebaseAuth: FirebaseAuth?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()


    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this.applicationContext,"User LogOut",Toast.LENGTH_LONG).show()
        UserService().logOutAction(this.firebaseAuth!!,this.applicationContext)
    }
}
