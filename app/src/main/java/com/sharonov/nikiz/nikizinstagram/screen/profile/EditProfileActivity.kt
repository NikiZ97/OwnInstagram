package com.sharonov.nikiz.nikizinstagram.screen.profile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sharonov.nikiz.nikizinstagram.R
import com.sharonov.nikiz.nikizinstagram.content.User
import com.sharonov.nikiz.nikizinstagram.content.UserSettings
import kotlinx.android.synthetic.main.snippet_center_edit_profile.*
import kotlinx.android.synthetic.main.snippet_top_edit_profile_toolbar.*

class EditProfileActivity : AppCompatActivity(), EditProfileView {

    private lateinit var presenter: EditProfilePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        backArrow.setOnClickListener { finish() }
        presenter = EditProfilePresenter(this, this)
        presenter.setupFirebaseAuth()
    }

    override fun preFillUserData(userSettings: UserSettings) {
        val user: User? = userSettings.user
        val userAccountSettings = userSettings.userAccountSettings
        usernameEditText.setText(userAccountSettings?.username)
        displayNameEditText.setText(userAccountSettings?.display_name)
        websiteEditText.setText(userAccountSettings?.website)
        descriptionEditText.setText(userAccountSettings?.description)
        emailEditText.setText(user?.email)
        phoneEditText.setText(user?.phone_number.toString())
        Glide.with(this).load(userAccountSettings?.profile_photo).into(profilePhoto)
    }
}
