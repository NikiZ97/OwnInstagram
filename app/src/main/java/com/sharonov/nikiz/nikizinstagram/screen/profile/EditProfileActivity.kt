package com.sharonov.nikiz.nikizinstagram.screen.profile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sharonov.nikiz.nikizinstagram.R
import com.sharonov.nikiz.nikizinstagram.content.User
import com.sharonov.nikiz.nikizinstagram.content.UserAccountSettings
import com.sharonov.nikiz.nikizinstagram.content.UserEditSettings
import com.sharonov.nikiz.nikizinstagram.content.UserSettings
import com.sharonov.nikiz.nikizinstagram.extensions.condenseUsername
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
        saveChanges.setOnClickListener {
            presenter.saveProfileSettings(getProfileSettingsFromEditTexts())
        }
    }

    private fun getProfileSettingsFromEditTexts(): UserEditSettings {
        val userEditSettings = UserEditSettings()
        with(userEditSettings) {
            email = emailEditText.text.toString()
            phone_number = phoneEditText.text.toString().toLong()
            username = usernameEditText.text.toString().condenseUsername()
            display_name = displayNameEditText.text.toString()
            website = websiteEditText.text.toString()
            description = descriptionEditText.text.toString()
        }
        return userEditSettings
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

    override fun showMessage(messageId: Int) {
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()
    }
}
