package com.wbh.firebaseapp

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

class MainActivity : AppCompatActivity() {

    private var register_btn: Button? =null
    private var login_btn: Button?= null
    private var email : EditText?= null
    private var password: EditText?= null
    private var firebaseAuth: FirebaseAuth?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        register_btn = findViewById(R.id.register);
        login_btn = findViewById(R.id.login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        firebaseAuth = FirebaseAuth.getInstance()


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
        else if (TextUtils.isEmpty(password_text)){
            Toast.makeText(applicationContext,"this field is empty", Toast.LENGTH_LONG).show()
        }else {
            firebaseAuth?.createUserWithEmailAndPassword(email_text,password_text)?.addOnCompleteListener(object: OnCompleteListener<AuthResult>{
                override fun onComplete(p0: Task<AuthResult>) {
                    if (p0.isSuccessful){
                        Toast.makeText(applicationContext,"Account Created", Toast.LENGTH_LONG).show()
                    }else{
                        val error = p0.exception?.message
                        Toast.makeText(applicationContext,error.toString(),Toast.LENGTH_LONG).show()
                    }
                }
            });
        }
    }
}
