package com.wbh.firebaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private var register_btn: Button? =null
    private var login_btn: Button?= null
    private var email : EditText?= null
    private var password: EditText?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        register_btn = findViewById(R.id.register);
        login_btn = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        register_btn?.setOnClickListener {
            registerNewUser()
        }



    }

    private fun registerNewUser() {
        var email_text = email?.text.toString().trim()
        var password_text = password?.text.toString().trim()

        if (TextUtils.isEmpty(email_text)){
            Toast.makeText(applicationContext,"this field is empty", Toast.LENGTH_LONG).show()
        }


        if (TextUtils.isEmpty(password_text)){
            Toast.makeText(applicationContext,"this field is empty", Toast.LENGTH_LONG).show()
        }
    }
}
