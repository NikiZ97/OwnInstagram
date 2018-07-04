package com.sharonov.nikiz.nikizinstagram.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sharonov.nikiz.nikizinstagram.R
import kotlinx.android.synthetic.main.snippet_top_edit_profile_toolbar.*

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        backArrow.setOnClickListener { finish() }
    }
}
