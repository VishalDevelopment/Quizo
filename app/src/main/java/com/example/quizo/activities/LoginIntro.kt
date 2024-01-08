package com.example.quizo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizo.databinding.ActivityLoginIntroBinding
import com.google.firebase.auth.FirebaseAuth
import java.lang.Exception

class LoginIntro : AppCompatActivity() {

lateinit var binding: ActivityLoginIntroBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityLoginIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val auth = FirebaseAuth.getInstance()

        if(auth.currentUser !=null){
            redirect("Main")
        }

        binding.Go.setOnClickListener {
            redirect("Login")
        }


    }

    private fun redirect(name:String){
        val intent = when(name){
            "Login" -> Intent(this, LoginActivity::class.java)
            "Main" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("No path exists")
        }

        startActivity(intent)
        finish()
    }

}