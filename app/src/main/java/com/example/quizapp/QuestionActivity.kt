package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.models.Question
import com.example.quizapp.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class QuestionActivity : AppCompatActivity() {
    lateinit var btnNext:Button
    lateinit var btnPrevious:Button
    lateinit var btnSubmit:Button

    var quizzes:MutableList<Quiz>?=null
    var questions:MutableMap<String,Question>?=null
    var index=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        btnPrevious=findViewById<Button>(R.id.btnPrevious)
         btnNext=findViewById<Button>(R.id.btnNext)
         btnSubmit=findViewById<Button>(R.id.btnSubmit)
        setUpFirestore()
        setUpListener()
    }

    private fun setUpListener() {
        btnPrevious.setOnClickListener {
            index--;
            bindView()
        }
        btnNext.setOnClickListener {
            index++;
            bindView()
        }
        btnSubmit.setOnClickListener {
            Log.d("final",questions.toString())
val json= Gson().toJson(quizzes!![0]) //add gson dependecies
            val intent= Intent(this,ResultActivity::class.java)
            intent.putExtra("QUIZ",json)
            startActivity(intent)
        }
    }

    private fun setUpFirestore() {
        val firestore=FirebaseFirestore.getInstance()
        var date=intent.getStringExtra("DATE")
        if(date!=null) {
            firestore.collection("quizzes").whereEqualTo("title", date).get()
                .addOnSuccessListener {
                  if(it!=null && !it.isEmpty){
                     quizzes=it.toObjects(Quiz::class.java)
                      questions= quizzes!![0].questions
                      bindView()

                  }
                }
        }
    }

    private fun bindView() {

        btnPrevious.visibility= View.GONE
        btnNext.visibility= View.GONE
        btnSubmit.visibility= View.GONE
        if(index==1){
            btnNext.visibility=View.VISIBLE
        }else if(index== questions!!.size){
            btnSubmit.visibility=View.VISIBLE
            btnPrevious.visibility=View.VISIBLE
        }else{
            btnPrevious.visibility=View.VISIBLE
            btnNext.visibility=View.VISIBLE
        }

val question= questions!!["question$index"]

       question?.let {
           val description=findViewById<TextView>(R.id.description)
           val optionList=findViewById<RecyclerView>(R.id.optionList)
           description.text=it.description
           val optionAdapter=OptionAdapter(this,it)
           optionList.layoutManager= LinearLayoutManager(this)
           optionList.adapter=optionAdapter
           optionList.setHasFixedSize(true)
       }



    }

}