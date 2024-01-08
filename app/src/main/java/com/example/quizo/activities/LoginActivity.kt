package com.example.quizo.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizo.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var binding: ActivityLoginBinding
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.SignupUSer.setOnClickListener {
          val  intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun login(){
        val email = binding.etEmailAddress.text.toString()
        val password = binding.etPassword.text.toString()

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Blank fills aren't allowed.", Toast.LENGTH_SHORT).show()
        }
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this) {
            if (it.isSuccessful){
                Toast.makeText(this, "LoggedIn Sucessfully", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }
            else{
                Toast.makeText(this, "Authentication Failed" , Toast.LENGTH_SHORT).show()
            }

        }
    }
}