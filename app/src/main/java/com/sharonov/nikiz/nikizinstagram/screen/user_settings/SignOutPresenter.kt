package com.sharonov.nikiz.nikizinstagram.screen.user_settings

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class SignOutPresenter(private val signOutView: SignOutView) {
    private lateinit var auth: FirebaseAuth
    private lateinit var listener: FirebaseAuth.AuthStateListener

    fun setupFirebaseAuth() {
        auth = FirebaseAuth.getInstance()
        listener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
            user?.uid ?: Log.d("TAG", "signed out")
        }
    }

    fun signOut() {
        auth.signOut()
        signOutView.openHomeActivity()
    }

    fun addAuthStateListener() {
        auth.addAuthStateListener(listener)
    }

    fun removeAuthStateListener() {
        auth.removeAuthStateListener(listener)
    }
}