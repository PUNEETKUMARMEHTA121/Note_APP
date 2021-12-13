package com.example.android.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegisterActivity : AppCompatActivity() {
    lateinit var btn_register:Button
    lateinit var et_register_email:EditText
    lateinit var et_register_password:EditText
    lateinit var tv_login:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        btn_register=findViewById<Button>(R.id.btn_register)
        et_register_email=findViewById(R.id.et_email)
        et_register_password=findViewById(R.id.et_password)
        tv_login=findViewById(R.id.tv_Login)
        tv_login.setOnClickListener {
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
            finish()
        }

        btn_register.setOnClickListener {
            when{
                TextUtils.isEmpty(et_register_email.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter email",
                        Toast.LENGTH_SHORT).show()

                }
                TextUtils.isEmpty(et_register_password.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please enter Password",
                        Toast.LENGTH_SHORT).show()
                }
                else ->{
                    val email:String = et_register_email.text.toString().trim{ it <= ' '}
                    val password:String=et_register_password.text.toString().trim { it <= ' ' }

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(
                        OnCompleteListener<AuthResult> { task->
                            if(task.isSuccessful) {
                                val firebaseuser:FirebaseUser=task.result!!.user!!
                            Toast.makeText(
                                this@RegisterActivity,
                                "You are registered successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent=Intent(this@RegisterActivity,MainActivity::class.java)
                                intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
                                intent.putExtra("user_id",firebaseuser.uid)
                                intent.putExtra("email_id",email)
                                startActivity(intent)
                                finish()
                            }

                            else{
                                Toast.makeText(
                                    this@RegisterActivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    )
                }

            }
///
        }
    }

}