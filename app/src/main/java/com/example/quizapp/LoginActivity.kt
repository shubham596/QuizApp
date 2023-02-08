package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var signuptxt: TextView
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        firebaseAuth= FirebaseAuth.getInstance()
        var btn_Login:Button=findViewById(R.id.btnLogin)
        signuptxt=findViewById(R.id.SignUptxt)
        signuptxt.setOnClickListener {
            val intent= Intent(this,SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_Login.setOnClickListener {
            login()
        }
    }
    private fun login(){
        var emailadress=findViewById<EditText>(R.id.emailAddress)
        var passwordlogin=findViewById<EditText>(R.id.password)
        var email:String=emailadress.text.toString()
        var password:String=passwordlogin.text.toString()
        if(email.isBlank()||password.isBlank()){
            Toast.makeText(this,"Email and Password can't be blank",Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){

                    Toast.makeText(this,"login successful",Toast.LENGTH_SHORT).show()
                    val intent=Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,"error occured",Toast.LENGTH_SHORT).show()
                }
            }

    }
}