package com.sharonov.nikiz.nikizinstagram.content

data class UserSettings(val user: User? = null, val userAccountSettings: UserAccountSettings? = null)

data class UserAccountSettings(var description: String? = null,
                               var display_name: String? = null,
                               var followers: Long? = null,
                               var following: Long? = null,
                               var posts: Long? = null,
                               var profile_photo: String? = null,
                               var username: String? = null,
                               var website: String? = null)

data class User(var user_id: String? = null, var phone_number: Long? = null,
                var email: String? = null, var username: String? = null)