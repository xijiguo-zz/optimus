package com.optimus.basketbrawl

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.app.Application
import android.support.v7.app.AppCompatActivity
import android.database.Cursor
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView
import android.content.Intent

import java.util.ArrayList
import android.util.Log

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.signup_info.*

/**
 * A login screen that offers login via email/password.
 */
class SignUpInfoActivity : AppCompatActivity() {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_info)
        // Set up the login form.
        confirm_button.setOnClickListener {
            attemptSaveInfo()
        }

        skip_button.setOnClickListener {
            skipInfo()
        }
    }
    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private fun skipInfo() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
    private fun attemptSaveInfo() {
        var cancel = true
        // Reset errors.
        profile_name_editText.error = null
        profile_age_editText.error = null
        profile_gender_editText.error = null
        profile_weight_editText.error = null
        profile_height_editText.error = null

        // Store values at the time of the login attempt.
        val nameStr = profile_name_editText.text.toString()
        val ageInt = profile_age_editText.text.toString().toIntOrNull()
        val genderStr = profile_gender_editText.text.toString()
        val weightInt = profile_weight_editText.text.toString().toIntOrNull()
        val heightInt = profile_height_editText.text.toString().toIntOrNull()

        // Check for a valid email address.
        if (nameStr == "") {
            profile_name_editText.error = "Enter Name"
        } else if (ageInt != null && (ageInt < 10 || ageInt > 80)) {
            profile_age_editText.error = "Invalid Age"
        } else if (weightInt != null && (weightInt < 50 || weightInt > 300)) {
            profile_weight_editText.error = "Invalid Weight"
        } else if (heightInt != null && (heightInt < 50 || heightInt > 250)) {
            profile_weight_editText.error = "Invalid Height"
        } else {
            cancel = false
        }

        if (!cancel) {
            val intent = Intent(this, HomeActivity::class.java)

            (application as userClass).setUserName(nameStr)
            if (ageInt != null) { (application as userClass).setUserAge(ageInt!!) }
            (application as userClass).setUserGender(genderStr)
            if (weightInt != null) { (application as userClass).setUserWeight(weightInt!!) }
            if (heightInt != null) { (application as userClass).setUserHeight(heightInt!!) }

            startActivity(intent)
        }
    }
}

