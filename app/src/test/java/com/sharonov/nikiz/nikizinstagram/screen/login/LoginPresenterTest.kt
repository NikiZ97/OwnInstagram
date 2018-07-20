package com.sharonov.nikiz.nikizinstagram.screen.login

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.junit.Assert.*

@RunWith(JUnit4::class)
class LoginPresenterTest {

    private lateinit var authView: AuthView
    private lateinit var loginPresenter: LoginPresenter

    @Before
    fun setUp() {
        authView = Mockito.mock(AuthView::class.java)
        loginPresenter = LoginPresenter(authView)
    }

    @Test
    fun testCreated() {
        assertNotNull(loginPresenter)
    }

    @After
    fun tearDown() {
    }
}