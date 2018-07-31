package com.sharonov.nikiz.nikizinstagram.screen.profile

import com.sharonov.nikiz.nikizinstagram.content.UserAccountSettings
import com.sharonov.nikiz.nikizinstagram.content.UserSettings

interface EditProfileView {
    fun preFillUserData(userSettings: UserSettings)
    fun showMessage(messageId: Int)
}