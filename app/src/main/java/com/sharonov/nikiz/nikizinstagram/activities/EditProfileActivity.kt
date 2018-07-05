package com.sharonov.nikiz.nikizinstagram.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sharonov.nikiz.nikizinstagram.R
import kotlinx.android.synthetic.main.snippet_center_edit_profile.*
import kotlinx.android.synthetic.main.snippet_top_edit_profile_toolbar.*

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        backArrow.setOnClickListener { finish() }
        Glide.with(this).load("https://images.idgesg.net/images/article/2017/08/" +
                "android_robot_logo_by_ornecolorada_cc0_via_pixabay1904852_wide-100732483-large.jpg")
                .apply(RequestOptions()
                        .placeholder(R.mipmap.ic_launcher))
                .into(profilePhoto)
    }
}
