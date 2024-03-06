package com.devtests.pruebatecnica

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //val loginNameUs = findViewById<MaterialTextView>(R.id.loginNameUs)
        val welcmUser:String = intent.extras?.getString("EXTRA_NAME").orEmpty()
        AlertDialog.Builder(this).apply {
            setTitle("Bienvenido")
            setMessage("$welcmUser")
            setPositiveButton("Salir") { _: DialogInterface, _: Int ->
                //loginNameUs.text = "$welcmUser"
            }
            setNegativeButton("", null)
        }.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.toolbar -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}