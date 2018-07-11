package com.sharonov.nikiz.nikizinstagram.screen.login

import android.support.v7.widget.AppCompatButton
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class LoginPresenter(private val authView: AuthView) {
    private lateinit var auth: FirebaseAuth
    private lateinit var listener: FirebaseAuth.AuthStateListener

    public fun setupFirebaseAuth() {
        auth = FirebaseAuth.getInstance()
        listener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
            user?.uid ?: Log.d("TAG", "signed out")
        }
    }

    public fun signIn(login: String, password: String) {
        //TODO: add fields validation
        if (login.isNotEmpty() && password.isNotEmpty()) {
            authView.showLoading()
            auth.signInWithEmailAndPassword(login, password).addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    authView.showError()
                    authView.hideLoading()
                } else {
                    authView.openHomeFragment()
                    authView.hideLoading()
                }
            }
        }
    }

    public fun addAuthStateListener() {
        auth.addAuthStateListener(listener)
    }

    public fun removeAuthStateListener() {
        auth.removeAuthStateListener(listener)
    }
}