package com.sharonov.nikiz.nikizinstagram.adapters

import android.app.Activity
import android.app.FragmentManager
import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sharonov.nikiz.nikizinstagram.R
import com.sharonov.nikiz.nikizinstagram.dialogs.SignOutDialogFragment
import com.sharonov.nikiz.nikizinstagram.screen.profile.EditProfileActivity

class UserSettingsAdapter(private val settingsList: List<String>,
                          private val context: Context): RecyclerView.Adapter<UserSettingsAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val settingsItem = settingsList[position]
        holder.settingsItemTextView.text = settingsItem
        holder.editProfileItem.setOnClickListener {
            when (position) {
                0 -> {
                    context.startActivity(Intent(context, EditProfileActivity::class.java))
                }
                1 -> {
                    showSignOutDialogFragment()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return UserSettingsAdapter.ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.settings_item_raw, parent, false)
        )
    }

    override fun getItemCount(): Int = settingsList.size

    private fun showSignOutDialogFragment() {
        val signOutDialogFragment = SignOutDialogFragment()
        val fragmentManager: FragmentManager = (context as Activity).fragmentManager
        signOutDialogFragment.show(fragmentManager, "dialog")
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var settingsItemTextView: TextView = view.findViewById(R.id.settingsItemTextView)
        val editProfileItem: ConstraintLayout = view.findViewById(R.id.editProfileItem)
    }
}