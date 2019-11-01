package com.example.ecommercecar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v == loginButton ){

            val emailText: String = email.text.toString()
            val passwordText : String = password.text.toString()

            if(emailText == "test@aigen.tech" && passwordText == "AigenTech"){
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
