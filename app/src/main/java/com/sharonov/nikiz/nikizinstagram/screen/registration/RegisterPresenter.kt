package com.sharonov.nikiz.nikizinstagram.screen.registration

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.sharonov.nikiz.nikizinstagram.R
import com.sharonov.nikiz.nikizinstagram.content.User
import com.sharonov.nikiz.nikizinstagram.content.UserAccountSettings
import com.sharonov.nikiz.nikizinstagram.extensions.condenseUsername
import com.sharonov.nikiz.nikizinstagram.extensions.expandUsername
import com.sharonov.nikiz.nikizinstagram.screen.login.AuthView

class RegisterPresenter(private val authView: AuthView, private val context: Context) {
    private lateinit var auth: FirebaseAuth
    private lateinit var listener: FirebaseAuth.AuthStateListener
    private var userId: String? = null
    private var fullUserName: String? = null
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    fun setupFirebaseAuth() {
        auth = FirebaseAuth.getInstance()
        listener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
            user?.uid ?: Log.d("TAG", "signed out")

        }
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference
    }

    private fun usernameExists(username: String?, dataSnapshot: DataSnapshot?): Boolean {
        val user = User()
        dataSnapshot?.child(userId!!)?.children?.forEach {
            user.username = it.getValue(User::class.java)?.username
            if (user.username?.expandUsername() == username) {
                return true
            }
        }
        return false
    }

    fun registerAccount(email: String, fullName: String, password: String) {
        fullUserName = fullName
        authView.showLoading()
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener({ task ->
                    if (!task.isSuccessful) {
                        authView.showError(task.exception?.message)
                        authView.hideLoading()
                    } else {
                        sendVerificationEmail()
                        userId = auth.currentUser?.uid
                        saveUserToDatabase(email)
                        authView.closeActivity()
                    }
                })
    }

    private fun saveUserToDatabase(email: String) {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (usernameExists(fullUserName, p0)) {
                    fullUserName = databaseReference.push().key?.substring(3, 10)
                }
                addNewUserToDatabase(email, fullUserName, "", "", "")
                auth.signOut()
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.e("TAG", p0.message)
            }

        })
    }

    fun addNewUserToDatabase(email: String?, username: String?, description: String,
                             website: String, profilePhoto: String) {
        val user = User(userId, 1, email, username?.condenseUsername())
        val userAccountSettings = UserAccountSettings(description, username, 0, 0, 0,
                profilePhoto, username?.condenseUsername(), website)
        databaseReference.child(context.getString(R.string.dbname_users))
                .child(userId!!)
                .setValue(user)
        databaseReference.child(context.getString(R.string.dbname_user_account_settings))
                .child(userId!!)
                .setValue(userAccountSettings)
    }

    private fun sendVerificationEmail() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    authView.showError(context.getString(R.string.error_verification_email_send_failed))
                } else {
                    authView.showInformationMessage(R.string.prompt_verification_email_sent)
                    authView.closeActivity()
                }
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