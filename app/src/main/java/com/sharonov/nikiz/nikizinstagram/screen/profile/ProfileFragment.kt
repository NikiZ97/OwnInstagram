package com.sharonov.nikiz.nikizinstagram.screen.profile


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.bumptech.glide.Glide
import com.sharonov.nikiz.nikizinstagram.R
import com.sharonov.nikiz.nikizinstagram.content.UserSettings
import com.sharonov.nikiz.nikizinstagram.screen.user_settings.AccountSettingsActivity
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.layout_center_profile.*
import kotlinx.android.synthetic.main.snippet_top_profile.*
import kotlinx.android.synthetic.main.snippet_top_profilebar.*

class ProfileFragment : Fragment(), ProfileView {

    companion object {
        fun newInstance(): ProfileFragment = ProfileFragment()
    }

    private lateinit var presenter: ProfilePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        presenter = ProfilePresenter(this, context)
        presenter.setupFirebaseAuth()
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbar()
        profileProgressBar.visibility = View.GONE
        textEditProfile.setOnClickListener { startActivity(Intent(context, EditProfileActivity::class.java)) }
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(profileToolbar)
        profileMenu.setOnClickListener { startActivity(Intent(activity, AccountSettingsActivity::class.java)) }
    }

    override fun setProfileWidgets(userSettings: UserSettings) {
        val userAccountSettings = userSettings.userAccountSettings
        displayName.text = userAccountSettings?.username
        initials.text = userAccountSettings?.display_name
        website.text = userAccountSettings?.website
        description.text = userAccountSettings?.description
        tvPosts.text = userAccountSettings?.posts.toString()
        tvFollowers.text = userAccountSettings?.followers.toString()
        tvFollowing.text = userAccountSettings?.following.toString()
        Glide.with(context!!).load(userAccountSettings?.profile_photo).into(profileThumbnail)
    }

    override fun onStart() {
        super.onStart()
        presenter.addAuthStateListener()
    }

    override fun onStop() {
        super.onStop()
        presenter.removeAuthStateListener()
    }
}
