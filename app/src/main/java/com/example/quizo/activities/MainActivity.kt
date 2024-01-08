package com.example.quizo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.GridLayout
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.quizo.R
import com.example.quizo.adapters.QuizAdapter
import com.example.quizo.databinding.ActivityMainBinding
import com.example.quizo.models.Quiz
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Date

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var adapter:QuizAdapter
    private var quizList = mutableListOf<Quiz>()

    lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupViews()




    }

    private fun setUpfirestore() {

        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if (value==null || error!= null){
                Toast.makeText(this, "Error fetching data", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA",value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()
        }
    }



    private fun setupRecyclerView() {
        adapter = QuizAdapter(this,quizList)
        binding.quizRecyclerView.layoutManager = GridLayoutManager(this,2)
        binding.quizRecyclerView.adapter = adapter
    }

    private  fun setupViews(){
        setUpfirestore()
        setUpDrawLayout()
        setupRecyclerView()
        setUpDatePicker()


    }

    private fun setUpDatePicker() {
        binding.btnDatePicker.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager,"DatePicker")
            datePicker.addOnPositiveButtonClickListener {
                Log.d("DATEPICKER",datePicker.headerText)
                val dateFormater = SimpleDateFormat("dd-MM-yyyy")
                val date = dateFormater.format(Date(it))
                val intent = Intent(this,QuestionActivity::class.java)
                intent.putExtra("DATE",date)
                startActivity(intent)

            }
            datePicker.addOnNegativeButtonClickListener {
                Log.d("DATEPICKER",datePicker.headerText)
            }
            datePicker.addOnCancelListener {
                Log.d("DATEPICKER","Date Picker Cancelled")

            }
        }
    }

    private fun  setUpDrawLayout(){
        setSupportActionBar(binding.appBar)
        actionBarDrawerToggle = ActionBarDrawerToggle(this,binding.mainDrawer,
            R.string.app_name,
            R.string.app_name
        )
        actionBarDrawerToggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener {
            val intent = Intent(this,ProfileActivity::class.java)
            startActivity(intent)

            binding.mainDrawer.closeDrawers()
            true
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}