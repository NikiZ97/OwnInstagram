package com.sharonov.nikiz.nikizinstagram.screen.profile

import com.sharonov.nikiz.nikizinstagram.content.UserSettings

interface ProfileView {
    fun setProfileWidgets(userSettings: UserSettings)
}