package com.devtests.pruebatecnica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private lateinit var loginUName: EditText
    private lateinit var loginPass: EditText
    private lateinit var loginBtn: Button
    private lateinit var signBtn: Button
    private lateinit var loginDBase: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginUName = findViewById(R.id.inputUsername)
        loginPass = findViewById(R.id.inputPassword)
        loginBtn = findViewById(R.id.btnLogin)
        signBtn = findViewById(R.id.btnRegister)
        loginDBase = DBHelper(this)

        loginBtn.setOnClickListener {
            val usertxt = loginUName.text.toString()
            val passwordtxt = loginPass.text.toString()

            if(TextUtils.isEmpty(usertxt) || TextUtils.isEmpty(passwordtxt))  {
                Toast.makeText(this, "Agregar usuario y contraseña", Toast.LENGTH_SHORT).show()

            } else {
                val checkUser = loginDBase.checkUserPassword(usertxt, passwordtxt)
                if(checkUser == true) {
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, HomeActivity::class.java)
                    intent.putExtra("EXTRA_NAME", usertxt)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Usuario y contraseña no validos", Toast.LENGTH_SHORT).show()
                }
            }
        }

        signBtn.setOnClickListener {
            val intent = Intent(applicationContext, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}