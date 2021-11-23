package com.example.android.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    lateinit var tv_register:EditText
    lateinit var btn_login: Button
    lateinit var et_login_email:EditText
    lateinit var et_login_password:EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        tv_register=findViewById<EditText>(R.id.tv_Register)
        btn_login=findViewById(R.id.btn_login)
        et_login_email=findViewById(R.id.et_email)
        et_login_password=findViewById(R.id.et_password)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tv_register.setOnClickListener {
            intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
        finish()
        }

        btn_login.setOnClickListener {
            when{
                TextUtils.isEmpty(et_login_email.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter email",
                        Toast.LENGTH_SHORT).show()

                }
                TextUtils.isEmpty(et_login_password.text.toString().trim{ it <= ' '}) -> {
                    Toast.makeText(
                        this@LoginActivity,
                        "Please enter Password",
                        Toast.LENGTH_SHORT).show()
                }
                else ->{
                    val email:String = et_login_email.text.toString().trim{ it <= ' '}
                    val password:String=et_login_password.text.toString().trim { it <= ' ' }

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task->
                                if(task.isSuccessful) {
                                    val firebaseuser: FirebaseUser =task.result!!.user!!
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "You are Logged In successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    val intent=Intent(this@LoginActivity,MainActivity::class.java)
                                    intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
                                    intent.putExtra("user_id",FirebaseAuth.getInstance().currentUser!!.uid)
                                    intent.putExtra("email_id",email)
                                    startActivity(intent)
                                    finish()
                                }

                                else{
                                    Toast.makeText(
                                        this@LoginActivity,
                                        task.exception!!.message.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                }

            }

        }


    }
}