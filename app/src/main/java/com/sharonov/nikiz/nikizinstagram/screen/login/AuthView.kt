package com.sharonov.nikiz.nikizinstagram.screen.login

import com.sharonov.nikiz.nikizinstagram.screen.general.LoadingView

interface AuthView: LoadingView {
    fun openHomeFragment()
    fun showError()
}