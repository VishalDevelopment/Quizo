package com.example.quizo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizo.R
import com.example.quizo.databinding.ActivityLoginBinding
import com.example.quizo.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.txtmail.text = firebaseAuth.currentUser?.email

        binding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            var intent = Intent(this,ActivityLoginBinding::class.java)
            startActivity(intent)
        }

    }
}