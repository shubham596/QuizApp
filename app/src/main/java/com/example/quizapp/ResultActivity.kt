package com.example.quizapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import com.example.quizapp.models.Quiz
import com.google.gson.Gson
import org.w3c.dom.Text

class ResultActivity : AppCompatActivity() {
    lateinit var quiz :Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setUpView()
    }

    private fun setUpView() {
        val quizdata=intent.getStringExtra("QUIZ")
            quiz= Gson().fromJson<Quiz>(quizdata,Quiz::class.java)
        calculateScore()
        setAnswerView()
    }

    private fun calculateScore() {
        var score=0
          var totalScore:Int=0;
        for(entry in quiz.questions.entries){
            totalScore+=10
            val question=entry.value
            if(question.answer==question.userAnswer){
                score+=10
            }
        }
        var txtScore=findViewById<TextView>(R.id.txtScore)
        txtScore.text="your score is :$score on $totalScore "
    }

    private fun setAnswerView() {
        var txtAnswer=findViewById<TextView>(R.id.txtAnswer)
        val builder = StringBuilder("")
        for (entry in quiz.questions.entries) {
            val question = entry.value
            builder.append("<font color'#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#009688'>Answer: ${question.answer}</font><br/><br/>")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        } else {
            txtAnswer.text = Html.fromHtml(builder.toString());
        }
    }

}
