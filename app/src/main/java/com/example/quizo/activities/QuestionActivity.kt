package com.example.quizo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizo.adapters.OptionAdapter
import com.example.quizo.databinding.ActivityQuestionBinding
import com.example.quizo.models.Question
import com.example.quizo.models.Quiz
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class QuestionActivity : AppCompatActivity() {

    lateinit var binding: ActivityQuestionBinding
    var quizzes :MutableList<Quiz>? = null
    var question:MutableMap<String,Question>? = null

    var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpFireStore()
        setUpEventListener()
    }

    private fun setUpEventListener() {
        binding.btnPrevious.setOnClickListener {
            index--
            bindViews()
        }
        binding.btnNext.setOnClickListener {
            index++
            bindViews()
        }
        binding.btnSubmit.setOnClickListener {

            val intent = Intent(this,ResultActivity::class.java)
            val json = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ",json)
            startActivity(intent)
        }

    }

    private fun setUpFireStore() {
       val firestore = FirebaseFirestore.getInstance()
        var date = intent.getStringExtra("DATE")

        if(date!=null){
            firestore.collection("quizzes").whereEqualTo("title",date)
                .get()
                .addOnSuccessListener {
                    if (it!=null && !it.isEmpty){
                    Log.d("DATA", it.toObjects(Quiz::class.java,).toString())
                        quizzes = it.toObjects(Quiz::class.java)
                        question = quizzes!![0].questions
                        bindViews()

                    }
                }
        }


    }

    private fun bindViews() {
        binding.btnPrevious.visibility = View.GONE
        binding.btnNext.visibility = View.GONE
        binding.btnSubmit.visibility = View.GONE

        if (index==1){
            binding.btnNext.visibility = View.VISIBLE
        }
        else if (index == question!!.size){
            binding.btnPrevious.visibility = View.VISIBLE
            binding.btnSubmit.visibility = View.VISIBLE
        }
        else{
            binding.btnPrevious.visibility = View.VISIBLE
            binding.btnNext.visibility = View.VISIBLE
        }
        val question = question!!["question$index"]

        question?.let {

            binding.description.text = it.description
            val optionAdapter = OptionAdapter(this, it)
            binding.optionList.layoutManager = LinearLayoutManager(this)
            binding.optionList.adapter =  optionAdapter
            binding.optionList.setHasFixedSize(true)
        }



    }
}