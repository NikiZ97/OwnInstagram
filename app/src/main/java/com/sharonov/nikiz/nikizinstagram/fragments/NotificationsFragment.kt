package com.sharonov.nikiz.nikizinstagram.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sharonov.nikiz.nikizinstagram.R

class NotificationsFragment : Fragment() {

    companion object {
        fun newInstance(): NotificationsFragment = NotificationsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_likes, container, false)
    }
}
