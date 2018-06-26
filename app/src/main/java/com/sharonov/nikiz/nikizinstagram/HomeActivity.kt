package com.sharonov.nikiz.nikizinstagram

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sharonov.nikiz.nikizinstagram.extensions.setupBottomNavigationView
import kotlinx.android.synthetic.main.layout_bottom_navigation_view.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottomNavigationView.setupBottomNavigationView()
    }
}
