package com.sharonov.nikiz.nikizinstagram.screen.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.sharonov.nikiz.nikizinstagram.HomeActivity
import com.sharonov.nikiz.nikizinstagram.R
import com.sharonov.nikiz.nikizinstagram.extensions.isPasswordCorrect
import com.sharonov.nikiz.nikizinstagram.extensions.isStringNotEmpty
import com.sharonov.nikiz.nikizinstagram.extensions.setupValidator
import com.sharonov.nikiz.nikizinstagram.screen.registration.RegisterActivity
import kotlinx.android.synthetic.main.activity_auth.*

class LoginActivity : AppCompatActivity(), AuthView {

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        presenter = LoginPresenter(this)
        presenter.setupFirebaseAuth()
        loginButton.setOnClickListener {
            if (emailAndPasswordValid()) {
                presenter.signIn(emailEditText.text.toString(), passwordEditText.text.toString())
            }
        }

        createAccountTextView.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun emailAndPasswordValid(): Boolean {
        val isEmailValid = emailEditText.setupValidator(R.string.error_empty_login, { it.isStringNotEmpty() }, this)
        val isPasswordValid = passwordEditText.setupValidator(R.string.error_incorrect_password, { it.isPasswordCorrect() }, this)
        return isEmailValid && isPasswordValid
    }

    override fun openHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun showError(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
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
