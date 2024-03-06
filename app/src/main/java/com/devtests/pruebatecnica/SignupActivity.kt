package com.devtests.pruebatecnica

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignupActivity : AppCompatActivity() {

    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var repeatPassword: EditText
    private lateinit var signUpBtn: Button
    private lateinit var dataBase: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        userName = findViewById(R.id.signupUsername)
        password = findViewById(R.id.signupPassword)
        repeatPassword = findViewById(R.id.signupValPassword)
        signUpBtn = findViewById(R.id.btnUserSignUp)
        dataBase = DBHelper(this)

        signUpBtn.setOnClickListener {
            val username = userName.text.toString()
            val password = password.text.toString()
            val repeatpass = repeatPassword.toString()
            val savedata = dataBase.insertData(username, password)

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(
                    repeatpass
                )
            ) {
                Toast.makeText(
                    this,
                    "Disculpa, no permiten campos vacios",
                    Toast.LENGTH_LONG
                ).show()

            } else {
                //Validacion campo usuario
                if (username.length < 8) {
                    Toast.makeText(
                        this,
                        "Usuario inválido, debe de contener 8 caracteres o más",
                        Toast.LENGTH_LONG
                    ).show()
                }
                // Validacion campo contraseña
                if (password.length < 6) {
                    Toast.makeText(
                        this,
                        "Contraseña inválida",
                        Toast.LENGTH_LONG
                    ).show()
                }
                // Validacion campo repetir contraseña
                if (repeatpass.length < 6) {
                    Toast.makeText(
                        this,
                        "Contraseña invalida",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // Validacion contraseña iguales
                if (!password.equals(repeatpass)) {
                    if (savedata == true) {
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        val intent = Intent(applicationContext, LoginActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}