package com.optimus.basketbrawl

import android.app.Application
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

class userClass : Application() {
    var name: String? = "Jake Jin"
    var age: Int? = 21
    var gender: String? = "Male"
    var weight: Int? = 160
    var height: Int? = 190

    override fun onCreate() {
        super.onCreate()
    }

    fun getUserName() : String? {
        return name
    }
    fun setUserName(nameStr: String) {
        name = nameStr
    }
    fun getUserAge() : Int? {
        return age
    }
    fun setUserAge(ageInt: Int) {
        age = ageInt
    }
    fun getUserGender() : String? {
        return gender
    }
    fun setUserGender(genderStr: String) {
        gender = genderStr
    }
    fun getUserWeight() : Int? {
        return weight
    }
    fun setUserWeight(weightInt: Int) {
        weight = weightInt
    }
    fun getUserHeight() : Int? {
        return height
    }
    fun setUserHeight(HeightInt: Int) {
        height = HeightInt
    }
    fun resetUser() {
        name = null; age = null; gender = null; weight = null; height = null;
    }
}