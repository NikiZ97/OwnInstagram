package com.sharonov.nikiz.nikizinstagram.screen.profile

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.sharonov.nikiz.nikizinstagram.firebase.getAllUserInfo

class EditProfilePresenter(val editProfileView: EditProfileView, val context: Context) {
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
                val userSettings = getAllUserInfo(dataSnapshot, context, userId)
                editProfileView.preFillUserData(userSettings)
            }
        })
    }
}