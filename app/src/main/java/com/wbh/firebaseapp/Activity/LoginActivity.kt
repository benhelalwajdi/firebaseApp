package com.wbh.firebaseapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.wbh.firebaseapp.R
import com.wbh.firebaseapp.Services.UserService

class LoginActivity : AppCompatActivity() {

    private var register_btn: Button? =null
    private var login_btn: Button?= null
    private var email : EditText?= null
    private var password: EditText?= null
    private var firebaseAuth: FirebaseAuth?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        register_btn = findViewById(R.id.register);
        login_btn = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        firebaseAuth = FirebaseAuth.getInstance()


        register_btn?.setOnClickListener {
            startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
        }

        login_btn?.setOnClickListener {
            var email_text = email?.text.toString().trim()
            var password_text = password?.text.toString().trim()
            UserService().loginAction(email_text,password_text, firebaseAuth!!,this.applicationContext)
        }
    }
}
