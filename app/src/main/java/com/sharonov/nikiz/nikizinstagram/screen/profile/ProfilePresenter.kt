package com.sharonov.nikiz.nikizinstagram.screen.profile

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.sharonov.nikiz.nikizinstagram.R
import com.sharonov.nikiz.nikizinstagram.content.User
import com.sharonov.nikiz.nikizinstagram.content.UserAccountSettings
import com.sharonov.nikiz.nikizinstagram.content.UserSettings

class ProfilePresenter(val profileView: ProfileView, val context: Context?) {
    private lateinit var auth: FirebaseAuth
    private lateinit var listener: FirebaseAuth.AuthStateListener
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userId: String

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
                profileView.setProfileWidgets(getAllUserInfo(dataSnapshot))
            }
        })
    }

    private fun getAllUserInfo(dataSnapshot: DataSnapshot): UserSettings {
        val user = User()
        val userAccountSettings = UserAccountSettings()

        dataSnapshot.children.forEach {
            when {
                it.key == context?.getString(R.string.dbname_user_account_settings) ->
                    retrieveUserAccountSettings(userAccountSettings, it)
                it.key == context?.getString(R.string.dbname_users) ->
                        retrieveUserDetails(user, it)
            }
        }
        return UserSettings(user, userAccountSettings)
    }

    private fun retrieveUserAccountSettings(userAccountSettings: UserAccountSettings, dataSnapshot: DataSnapshot) {
        userAccountSettings.display_name = dataSnapshot
                .child(userId)
                .getValue(UserAccountSettings::class.java)
                ?.display_name
        userAccountSettings.username = dataSnapshot
                .child(userId)
                .getValue(UserAccountSettings::class.java)
                ?.username
        userAccountSettings.website = dataSnapshot
                .child(userId)
                .getValue(UserAccountSettings::class.java)
                ?.website
        userAccountSettings.description = dataSnapshot
                .child(userId)
                .getValue(UserAccountSettings::class.java)
                ?.description
        userAccountSettings.profile_photo = dataSnapshot
                .child(userId)
                .getValue(UserAccountSettings::class.java)
                ?.profile_photo
        userAccountSettings.posts = dataSnapshot
                .child(userId)
                .getValue(UserAccountSettings::class.java)
                ?.posts
        userAccountSettings.followers = dataSnapshot
                .child(userId)
                .getValue(UserAccountSettings::class.java)
                ?.followers
        userAccountSettings.following = dataSnapshot
                .child(userId)
                .getValue(UserAccountSettings::class.java)
                ?.following
    }

    private fun retrieveUserDetails(user: User, dataSnapshot: DataSnapshot) {
        user.username = dataSnapshot
                .child(userId)
                .getValue(User::class.java)
                ?.username
        user.email = dataSnapshot
                .child(userId)
                .getValue(User::class.java)
                ?.email
        user.phone_number = dataSnapshot
                .child(userId)
                .getValue(User::class.java)
                ?.phone_number
        user.username = dataSnapshot
                .child(userId)
                .getValue(User::class.java)
                ?.username
        user.username = dataSnapshot
                .child(userId)
                .getValue(User::class.java)
                ?.user_id
    }

    fun addAuthStateListener() {
        auth.addAuthStateListener(listener)
    }

    fun removeAuthStateListener() {
        auth.removeAuthStateListener(listener)
    }
}