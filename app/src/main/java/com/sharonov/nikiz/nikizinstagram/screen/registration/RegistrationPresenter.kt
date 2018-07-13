package com.sharonov.nikiz.nikizinstagram.screen.registration

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.sharonov.nikiz.nikizinstagram.screen.login.AuthView

class RegistrationPresenter(private val authView: AuthView) {
    private lateinit var auth: FirebaseAuth
    private lateinit var listener: FirebaseAuth.AuthStateListener
    private var userId: String? = null

    fun setupFirebaseAuth() {
        auth = FirebaseAuth.getInstance()
        listener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
            user?.uid ?: Log.d("TAG", "signed out")
        }
    }

    fun registerAccount(email: String, fullName: String, password: String) {
        authView.showLoading()
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener({ task ->
                    if (!task.isSuccessful) {
                        authView.showError(task.exception?.message)
                        authView.hideLoading()
                    } else {
                        userId = auth.currentUser?.uid
                        authView.openHomeActivity()
                        authView.hideLoading()
                    }
                })
    }

    fun addAuthStateListener() {
        auth.addAuthStateListener(listener)
    }

    fun removeAuthStateListener() {
        auth.removeAuthStateListener(listener)
    }
}