package com.sharonov.nikiz.nikizinstagram

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.sharonov.nikiz.nikizinstagram.extensions.setupBottomNavigationView
import com.sharonov.nikiz.nikizinstagram.fragments.*
import com.sharonov.nikiz.nikizinstagram.screen.profile.ProfileFragment
import kotlinx.android.synthetic.main.layout_bottom_navigation_view.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        bottomNavigationView.setupBottomNavigationView()
        loadFragment(HomeFragment.newInstance())
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_house -> {
                    loadFragment(HomeFragment.newInstance())
                    true
                }
                R.id.ic_alert -> {
                    loadFragment(NotificationsFragment.newInstance())
                    true
                }
                R.id.ic_profile -> {
                    loadFragment(ProfileFragment.newInstance())
                    true
                }
                R.id.ic_search -> {
                    loadFragment(SearchFragment.newInstance())
                    true
                }
                R.id.ic_circle -> {
                    loadFragment(AddPhotoFragment.newInstance())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()
    }
}
