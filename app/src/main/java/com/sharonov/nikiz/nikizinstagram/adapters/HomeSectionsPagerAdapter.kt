package com.sharonov.nikiz.nikizinstagram.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.sharonov.nikiz.nikizinstagram.fragments.CameraFragment
import com.sharonov.nikiz.nikizinstagram.fragments.DirectMessagesFragment
import com.sharonov.nikiz.nikizinstagram.fragments.HomeFragment
import com.sharonov.nikiz.nikizinstagram.fragments.HomeScreenFragment

class HomeSectionsPagerAdapter(fm: FragmentManager?): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> {
                CameraFragment.newInstance()
            }
            1 -> {
                HomeScreenFragment.newInstance()
            }
            2 -> {
                DirectMessagesFragment()
            }
            else -> {
                HomeFragment.newInstance()
            }
        }
    }

    override fun getCount(): Int = 3
}