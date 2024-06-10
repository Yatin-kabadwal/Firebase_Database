package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var dataBase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signButton = findViewById<Button>(R.id.btnSignUp)
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etMail = findViewById<TextInputEditText>(R.id.etMail)
        val userId = findViewById<TextInputEditText>(R.id.etUserName)
        val userPassword = findViewById<TextInputEditText>(R.id.etPassword)

        signButton.setOnClickListener {
            val name = etName.text.toString()
            val mail = etMail.text.toString()
            val uniqueId = userId.text.toString()
            val password = userPassword.text.toString()

            if (name.isEmpty() || mail.isEmpty() || uniqueId.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = User(name, mail, password, uniqueId)
            dataBase = FirebaseDatabase.getInstance().getReference("Users")
            dataBase.child(uniqueId).setValue(user).addOnSuccessListener {
                Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to Sign Up", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

