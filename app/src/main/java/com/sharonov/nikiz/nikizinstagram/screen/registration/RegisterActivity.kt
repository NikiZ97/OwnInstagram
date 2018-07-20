package com.sharonov.nikiz.nikizinstagram.screen.registration

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.sharonov.nikiz.nikizinstagram.HomeActivity
import com.sharonov.nikiz.nikizinstagram.R
import com.sharonov.nikiz.nikizinstagram.extensions.isEmailCorrect
import com.sharonov.nikiz.nikizinstagram.extensions.isPasswordCorrect
import com.sharonov.nikiz.nikizinstagram.extensions.isStringNotEmpty
import com.sharonov.nikiz.nikizinstagram.extensions.setupValidator
import com.sharonov.nikiz.nikizinstagram.screen.login.AuthView
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), AuthView {

    private lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        presenter = RegisterPresenter(this, this)
        presenter.setupFirebaseAuth()
        registerButton.setOnClickListener {
            if (areAllFieldsValid()) {
                presenter.registerAccount(emailEditText.text.toString(),
                        fullNameEditText.text.toString(),
                        passwordEditText.text.toString())
            }
        }
    }

    private fun areAllFieldsValid(): Boolean {
        val emailIsValid = emailEditText.setupValidator(R.string.error_incorrect_email, { it.isEmailCorrect() }, this)
        val fullNameIsValid = fullNameEditText.setupValidator(R.string.error_empty_name, { it.isStringNotEmpty() }, this)
        val passwordIsValid = passwordEditText.setupValidator(R.string.error_incorrect_password, { it.isPasswordCorrect() }, this)
        return emailIsValid && fullNameIsValid && passwordIsValid
    }

    override fun openHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    override fun showError(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun showInformationMessage(messageResourceId: Int) {
        Toast.makeText(this, messageResourceId, Toast.LENGTH_LONG).show()
    }

    override fun closeActivity() {
        finish()
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
