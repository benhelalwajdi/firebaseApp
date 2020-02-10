package com.wbh.firebaseapp.Services

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.wbh.firebaseapp.Activity.MainActivity
import com.wbh.firebaseapp.R

class UserService {


    /*TODO sharedPreferences*/
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "USER-CONNECTED"


    @SuppressLint("CommitPrefEdits")
    public fun loginAction(
        email_text: String,
        password_text: String,
        firebaseAuth: FirebaseAuth,
        ctx: Context
    ) {

        val sharedPref: SharedPreferences
        sharedPref = ctx.getSharedPreferences(PREF_NAME, this.PRIVATE_MODE)

        Log.d("User Connected", sharedPref.getBoolean(PREF_NAME, false).toString())

        if (!sharedPref.getBoolean(PREF_NAME, false)) {
            if (TextUtils.isEmpty(email_text)) {
                Toast.makeText(ctx, "Email field is empty", Toast.LENGTH_LONG).show()
            } else if (TextUtils.isEmpty(password_text)) {
                Toast.makeText(ctx, "Password field is empty", Toast.LENGTH_LONG).show()
            } else {
                firebaseAuth.signInWithEmailAndPassword(email_text, password_text)
                    //lambda exp for minimize line of code
                    .addOnCompleteListener { p0 ->
                        if (p0.isSuccessful) {
                            Toast.makeText(ctx, "You are logedin successfuly", Toast.LENGTH_LONG)
                                .show()
                            var i: Intent = Intent(ctx.applicationContext, MainActivity::class.java)
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            ctx.startActivity(i)
                            val editor = sharedPref.edit()
                            editor.putBoolean(PREF_NAME, true)
                            editor.apply()
                        } else {
                            val error = p0.exception?.message
                            Toast.makeText(ctx, error.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
            }
        } else {
            val editor = sharedPref.edit()
            Log.d("TAG_SHAREDPREFERENCES",editor.toString())
            var i: Intent = Intent(ctx.applicationContext, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            ctx.startActivity(i)
            editor.putBoolean(PREF_NAME, false)
            editor.apply()
        }


    }

    public fun registerAction(
        email_text: String,
        password_text: String,
        firebaseAuth: FirebaseAuth,
        ctx: Context
    ) {

        if (TextUtils.isEmpty(email_text)) {
            Toast.makeText(ctx, "Email this field is empty", Toast.LENGTH_LONG).show()
        } else if (TextUtils.isEmpty(password_text)) {
            Toast.makeText(ctx, "Password this field is empty", Toast.LENGTH_LONG).show()
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email_text, password_text)
                .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                    override fun onComplete(p0: Task<AuthResult>) {
                        if (p0.isSuccessful) {
                            Toast.makeText(ctx, "Account Created", Toast.LENGTH_LONG).show()
                            loginAction(email_text, password_text, firebaseAuth, ctx)
                        } else {
                            val error = p0.exception?.message
                            Toast.makeText(ctx, error.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
                });
        }
    }


    public fun logOutAction(
        firebaseAuth: FirebaseAuth,
        ctx: Context
    ) {
        val sharedPref: SharedPreferences
        sharedPref = ctx.getSharedPreferences(PREF_NAME, this.PRIVATE_MODE)
        val editor = sharedPref.edit()
        editor.putBoolean(PREF_NAME, false)
        editor.apply()
        firebaseAuth.signOut();

    }
}