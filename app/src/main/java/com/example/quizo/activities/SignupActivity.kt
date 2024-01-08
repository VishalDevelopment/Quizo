package com.example.quizo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizo.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth


class SignupActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSignup.setOnClickListener {
            signUpUser()
        }
        binding.LoginUser.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun signUpUser(){
        val email = binding.etEmailAddress.text.toString()
        val password = binding.etPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()

        if(email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            Toast.makeText(this, "Blank fill aren't allowed", Toast.LENGTH_SHORT).show()
        }
        if (password!=confirmPassword){
            Toast.makeText(this, "Password and Confirm Password aren't matching", Toast.LENGTH_SHORT).show()
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "Error creating user", Toast.LENGTH_SHORT).show()
                }
            }

    }
}