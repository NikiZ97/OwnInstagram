package com.sharonov.nikiz.nikizinstagram.screen.login

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class LoginPresenter(private val authView: AuthView) {
    private lateinit var auth: FirebaseAuth
    private lateinit var listener: FirebaseAuth.AuthStateListener

    fun setupFirebaseAuth() {
        auth = FirebaseAuth.getInstance()
        listener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
            user?.uid ?: Log.d("TAG", "signed out")
        }
    }

    fun signIn(login: String, password: String) {
        authView.showLoading()
        auth.signInWithEmailAndPassword(login, password).addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                authView.showError(task.exception.toString())
                authView.hideLoading()
            } else {
                authView.openHomeFragment()
                authView.hideLoading()
            }
        }
    }

    fun addAuthStateListener() {
        auth.addAuthStateListener(listener)
    }

    fun removeAuthStateListener() {
        auth.removeAuthStateListener(listener)
    }
}