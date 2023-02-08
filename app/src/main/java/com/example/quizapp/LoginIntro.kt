package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.view.accessibility.AccessibilityViewCommand
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class LoginIntro : AppCompatActivity() {
    lateinit var btngetstarted:Button
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)
        firebaseAuth= FirebaseAuth.getInstance()
        if(firebaseAuth.currentUser!=null){
            Toast.makeText(this,"already Login",Toast.LENGTH_SHORT).show()
            redirect("Main")
        }
        btngetstarted=findViewById(R.id.btnGetStarted)
        btngetstarted.setOnClickListener {
           redirect("Login")
        }
    }
    private fun redirect(name:String){
        val intent:Intent=when(name){
           "Login"-> Intent(this,LoginActivity::class.java)
            "Main"-> Intent(this,MainActivity::class.java)
            else->throw Exception("no path exist")
        }
        startActivity(intent)
        finish()
    }
}