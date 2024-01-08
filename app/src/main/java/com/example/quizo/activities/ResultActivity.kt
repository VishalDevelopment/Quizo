package com.example.quizo.activities

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.quizo.R
import com.example.quizo.databinding.ActivityResultBinding
import com.example.quizo.models.Quiz
import com.google.gson.Gson
import java.lang.StringBuilder

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    lateinit var quiz: Quiz
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
    }

    private fun setUpViews() {
        val quizData = intent.getStringExtra("QUIZ")
         quiz = Gson().fromJson<Quiz>(quizData,Quiz::class.java)

        calculateScore()
        setAnswerView()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun calculateScore() {
        var score = 0

        for (entry in quiz.questions.entries){
        val question = entry.value
            if(question.userAnswer == question.answer){
                score+=10
            }
        }
        binding.result.text = "Your Score : $score"
    }

    private fun setAnswerView() {
        val builder = StringBuilder("")
        for (entry in quiz.questions.entries){
            val question = entry.value
            builder.append("<font color  '#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color=  '#009688'><b>Answer: ${question.answer}</b></font><br/><br/>")
        }

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            binding.txtanswer.text = Html.fromHtml(builder.toString(),Html.FROM_HTML_MODE_COMPACT)

        }
        else{
            binding.txtanswer.text = Html.fromHtml(builder.toString())
        }
    }
}