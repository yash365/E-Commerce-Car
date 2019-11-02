package com.example.ecommercecar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.ecommercecar.views.HomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener(this)
        Log.d(TAG, "onCreate")
    }

    override fun onClick(v: View?) {
        if(v == loginButton ){

            val emailText: String = email.text.toString()
            val passwordText : String = password.text.toString()

            if(emailText == "test@aigen.tech" && passwordText == "AigenTech"){
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
