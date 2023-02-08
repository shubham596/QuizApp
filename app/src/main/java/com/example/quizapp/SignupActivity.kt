package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
lateinit var logintxt:TextView
   lateinit var firebaseAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        firebaseAuth= FirebaseAuth.getInstance()
        var btn_Signup:Button=findViewById(R.id.btn_Signup)
        logintxt=findViewById(R.id.AlredyLogin)
        logintxt.setOnClickListener {
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        btn_Signup.setOnClickListener {
            signUpUser()
        }
    }
    private fun signUpUser(){
        val etEmailAddress = findViewById<EditText>(R.id.EmailAddressSignup)

        val etPassword = findViewById<EditText>(R.id.PasswordSignup)
        val etConfirmPassword= findViewById<EditText>(R.id.Password2Signup)

        var email=etEmailAddress.text.toString()
        var password =etPassword .text.toString()
        var confirmPassword=etConfirmPassword.text.toString()
        if(email.isBlank()||password.isBlank()||confirmPassword.isBlank()){
            Toast.makeText(this,"Email or password can't be blank",Toast.LENGTH_SHORT).show()
            return
        }
        if(password!=confirmPassword){
            Toast.makeText(this,"password must be same",Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this,"Login successfull",Toast.LENGTH_SHORT).show()
                    val intent=Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,"error occured",Toast.LENGTH_SHORT).show()
                }
            }

    }


}