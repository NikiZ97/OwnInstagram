package com.sharonov.nikiz.nikizinstagram.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

import com.sharonov.nikiz.nikizinstagram.R
import com.sharonov.nikiz.nikizinstagram.screen.login.AuthActivity
import com.sharonov.nikiz.nikizinstagram.adapters.HomeSectionsPagerAdapter
import kotlinx.android.synthetic.main.layout_center_viewpager.*
import kotlinx.android.synthetic.main.layout_top_tabs.*

class HomeFragment : Fragment() {

    private lateinit var adapter: HomeSectionsPagerAdapter
    private lateinit var auth: FirebaseAuth
    private lateinit var authListener: FirebaseAuth.AuthStateListener

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setupFirebaseAuth()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = HomeSectionsPagerAdapter(activity?.supportFragmentManager)
        container.adapter = adapter
        tabs.setupWithViewPager(container)
        tabs.getTabAt(0)?.icon = resources.getDrawable(R.drawable.ic_camera)
        tabs.getTabAt(1)?.icon = resources.getDrawable(R.drawable.ic_instagram_black)
        tabs.getTabAt(2)?.icon = resources.getDrawable(R.drawable.ic_direct_messages)
    }

    private fun setupFirebaseAuth() {
        auth = FirebaseAuth.getInstance()
        authListener = FirebaseAuth.AuthStateListener {
            val user: FirebaseUser? = it.currentUser
            startLoginActivity(user)
        }
    }

    private fun startLoginActivity(firebaseUser: FirebaseUser?) {
        if (firebaseUser == null) {
            startActivity(Intent(context, AuthActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        auth.addAuthStateListener(authListener)
    }

    override fun onStop() {
        super.onStop()
        auth.removeAuthStateListener(authListener)
    }
}
