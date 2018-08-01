package com.sharonov.nikiz.nikizinstagram.screen.profile

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.sharonov.nikiz.nikizinstagram.content.User
import com.sharonov.nikiz.nikizinstagram.content.UserAccountSettings
import com.sharonov.nikiz.nikizinstagram.content.UserSettings
import com.sharonov.nikiz.nikizinstagram.firebase.getAllUserInfo
import com.sharonov.nikiz.nikizinstagram.R
import com.sharonov.nikiz.nikizinstagram.content.UserEditSettings

class EditProfilePresenter(val editProfileView: EditProfileView, val context: Context) {
    private lateinit var auth: FirebaseAuth
    private lateinit var listener: FirebaseAuth.AuthStateListener
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userId: String
    private lateinit var settings: UserSettings

    fun setupFirebaseAuth() {
        auth = FirebaseAuth.getInstance()
        listener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
            user?.uid ?: Log.d("TAG", "signed out")

        }
        userId = auth.currentUser?.uid ?: ""
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference

        databaseReference.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userSettings = getAllUserInfo(dataSnapshot, context, userId)
                settings = userSettings
                editProfileView.preFillUserData(userSettings)
            }
        })
    }

    /**
     * This method checks if username is unique and saves all settings
     * in the database
     */
    fun saveProfileSettings(userEditSettings: UserEditSettings) {
        val username: String? = null
        databaseReference.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (settings.user?.username == userEditSettings.username) {
                    saveSettingsToDatabase(userEditSettings)
                    editProfileView.showMessage(R.string.prompt_profile_settings_saved)
                } else {
                    checkIfUsernameExists(username, userEditSettings)
                }
            }
        })
    }

    private fun checkIfUsernameExists(username: String?, userEditSettings: UserEditSettings) {
        val reference = FirebaseDatabase.getInstance().reference
        val query = reference
                .child(context.getString(R.string.dbname_users))
                .orderByChild("username")
                .equalTo(username)
        query.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {}

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (!dataSnapshot.exists()) {
                    saveSettingsToDatabase(userEditSettings)
                    editProfileView.showMessage(R.string.prompt_profile_settings_saved)
                }

                if (dataSnapshot.children.any { it.exists() }) {
                    editProfileView.showMessage(R.string.error_username_not_unique)
                }
            }
        })
    }

    private fun saveSettingsToDatabase(userEditSettings: UserEditSettings) {
        databaseReference.child(context.getString(R.string.dbname_users))
                .child(userId)
                .child("email")
                .setValue(userEditSettings.email)
        databaseReference.child(context.getString(R.string.dbname_users))
                .child(userId)
                .child("phone_number")
                .setValue(userEditSettings.phone_number)
        databaseReference.child(context.getString(R.string.dbname_users))
                .child(userId)
                .child("username")
                .setValue(userEditSettings.username)
        databaseReference.child(context.getString(R.string.dbname_user_account_settings))
                .child(userId)
                .child("username")
                .setValue(userEditSettings.username)
        databaseReference.child(context.getString(R.string.dbname_user_account_settings))
                .child(userId)
                .child("website")
                .setValue(userEditSettings.website)
        databaseReference.child(context.getString(R.string.dbname_user_account_settings))
                .child(userId)
                .child("display_name")
                .setValue(userEditSettings.display_name)
        databaseReference.child(context.getString(R.string.dbname_user_account_settings))
                .child(userId)
                .child("description")
                .setValue(userEditSettings.description)
    }
}