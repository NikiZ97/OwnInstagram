package com.sharonov.nikiz.nikizinstagram.screen.login

import com.sharonov.nikiz.nikizinstagram.screen.general.LoadingView

interface AuthView: LoadingView {
    fun openHomeActivity()
    fun showError(errorMessage: String?)
}