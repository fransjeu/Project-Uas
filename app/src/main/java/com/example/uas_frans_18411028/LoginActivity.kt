package com.example.uas_frans_18411028

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.uas_frans_18411028.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            val email: String = binding.edtEmail.text.toString().trim()
            val password: String = binding.edtPassword.text.toString().trim()

            if (email.isEmpty()) {
                binding.edtEmail.error = "Input Email"
                binding.edtEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtEmail.error = "Invalid email"
                binding.edtEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 8) {
                binding.edtPassword.error = "password more than 8 characters"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }

            loginUser(email, password)

        }


        binding.txtregsiter.setOnClickListener {
            startActivity(Intent(this, Registeractivity::class.java))
        }
            binding.forgot.setOnClickListener {
                startActivity(Intent(this, ResetPasswordActivity::class.java))
            }
    }

        private fun loginUser(email: String, password: String) {

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    Intent(this, MainActivity::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }


            }
        }


        override fun onStart() {
            super.onStart()
            if (auth.currentUser != null) {
                Intent(this, MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        }
    }
