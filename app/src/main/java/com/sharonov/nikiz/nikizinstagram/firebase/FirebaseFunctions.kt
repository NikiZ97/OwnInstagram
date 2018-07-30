package com.sharonov.nikiz.nikizinstagram.firebase

import android.content.Context
import com.google.firebase.database.DataSnapshot
import com.sharonov.nikiz.nikizinstagram.R
import com.sharonov.nikiz.nikizinstagram.content.User
import com.sharonov.nikiz.nikizinstagram.content.UserAccountSettings
import com.sharonov.nikiz.nikizinstagram.content.UserSettings

fun getAllUserInfo(dataSnapshot: DataSnapshot, context: Context?, userId: String): UserSettings {
    val user = User()
    val userAccountSettings = UserAccountSettings()

    dataSnapshot.children.forEach {
        when {
            it.key == context?.getString(R.string.dbname_user_account_settings) ->
                retrieveUserAccountSettings(userAccountSettings, it, userId)
            it.key == context?.getString(R.string.dbname_users) ->
                retrieveUserDetails(user, it, userId)
        }
    }
    return UserSettings(user, userAccountSettings)
}

private fun retrieveUserAccountSettings(userAccountSettings: UserAccountSettings, dataSnapshot: DataSnapshot, userId: String) {
    userAccountSettings.display_name = dataSnapshot
            .child(userId)
            .getValue(UserAccountSettings::class.java)
            ?.display_name
    userAccountSettings.username = dataSnapshot
            .child(userId)
            .getValue(UserAccountSettings::class.java)
            ?.username
    userAccountSettings.website = dataSnapshot
            .child(userId)
            .getValue(UserAccountSettings::class.java)
            ?.website
    userAccountSettings.description = dataSnapshot
            .child(userId)
            .getValue(UserAccountSettings::class.java)
            ?.description
    userAccountSettings.profile_photo = dataSnapshot
            .child(userId)
            .getValue(UserAccountSettings::class.java)
            ?.profile_photo
    userAccountSettings.posts = dataSnapshot
            .child(userId)
            .getValue(UserAccountSettings::class.java)
            ?.posts
    userAccountSettings.followers = dataSnapshot
            .child(userId)
            .getValue(UserAccountSettings::class.java)
            ?.followers
    userAccountSettings.following = dataSnapshot
            .child(userId)
            .getValue(UserAccountSettings::class.java)
            ?.following
}

private fun retrieveUserDetails(user: User, dataSnapshot: DataSnapshot, userId: String) {
    user.username = dataSnapshot
            .child(userId)
            .getValue(User::class.java)
            ?.username
    user.email = dataSnapshot
            .child(userId)
            .getValue(User::class.java)
            ?.email
    user.phone_number = dataSnapshot
            .child(userId)
            .getValue(User::class.java)
            ?.phone_number
    user.username = dataSnapshot
            .child(userId)
            .getValue(User::class.java)
            ?.username
    user.username = dataSnapshot
            .child(userId)
            .getValue(User::class.java)
            ?.user_id
}