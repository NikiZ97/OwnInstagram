package com.sharonov.nikiz.nikizinstagram.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.sharonov.nikiz.nikizinstagram.R
import com.sharonov.nikiz.nikizinstagram.adapters.UserSettingsAdapter
import kotlinx.android.synthetic.main.layout_center_account_settings.*
import kotlinx.android.synthetic.main.snippet_top_account_settings_toolbar.*
import java.util.ArrayList

class AccountSettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_settings)
        setupSettingsList()
        backArrow.setOnClickListener { finish() }
    }

    private fun setupSettingsList() {
        var settingsList: List<String> = ArrayList()
        settingsList += "Edit profile"
        settingsList += "Sign Out"
        val adapter = UserSettingsAdapter(settingsList)
        settingsRecyclerView.layoutManager = LinearLayoutManager(this)

        settingsRecyclerView.adapter = adapter
    }
}
