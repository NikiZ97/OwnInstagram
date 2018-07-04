package com.sharonov.nikiz.nikizinstagram.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sharonov.nikiz.nikizinstagram.R

class UserSettingsAdapter(private val settingsList: List<String>): RecyclerView.Adapter<UserSettingsAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val settingsItem = settingsList[position]
        holder.settingsItemTextView.text = settingsItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return UserSettingsAdapter.ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.settings_item_raw, parent, false)
        )
    }

    override fun getItemCount(): Int = settingsList.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var settingsItemTextView: TextView = view.findViewById(R.id.settingsItemTextView)
    }
}