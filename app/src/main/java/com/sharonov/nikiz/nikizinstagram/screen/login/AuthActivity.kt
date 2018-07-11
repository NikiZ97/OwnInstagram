package com.sharonov.nikiz.nikizinstagram.screen.login

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.sharonov.nikiz.nikizinstagram.R
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity(), AuthView {

    private val presenter: LoginPresenter
        get() = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        presenter.setupFirebaseAuth()
        presenter.signIn(loginButton, emailEditText.text.toString(), passwordEditText.text.toString())
    }

    override fun openHomeFragment() {
        Snackbar.make(authorizationLayout, "Successfully signed in", Snackbar.LENGTH_SHORT)
    }

    override fun showError() {
        Snackbar.make(authorizationLayout, "Oops! Error occurred!", Snackbar.LENGTH_SHORT)
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
