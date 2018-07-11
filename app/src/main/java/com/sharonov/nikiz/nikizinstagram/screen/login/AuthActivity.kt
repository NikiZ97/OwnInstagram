package com.sharonov.nikiz.nikizinstagram.screen.login

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.sharonov.nikiz.nikizinstagram.R
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity(), AuthView {

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        presenter = LoginPresenter(this)
        presenter.setupFirebaseAuth()
        loginButton.setOnClickListener { presenter.signIn(emailEditText.text.toString(),
                passwordEditText.text.toString()) }
    }

    override fun openHomeFragment() {
        Toast.makeText(this, "Successfully signed in", Toast.LENGTH_SHORT).show()
    }

    override fun showError() {
        Toast.makeText(this, "Oops! Error occurred!", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        loadingProgressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingProgressBar.visibility = View.GONE
    }

    override fun onStart() {
        super.onStart()
        presenter.addAuthStateListener()
    }

    override fun onStop() {
        super.onStop()
        presenter.removeAuthStateListener()
    }
}
