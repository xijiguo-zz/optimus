package com.optimus.basketbrawl

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SignupButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("purpose", "signup")
            startActivity(intent)
        }

        LoginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("purpose", "login")
            startActivity(intent)
        }
    }
}
