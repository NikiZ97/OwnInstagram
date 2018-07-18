package com.sharonov.nikiz.nikizinstagram.content

data class UserAccountSettings(val description: String? = null,
                               val display_name: String? = null,
                               val followers: Long? = null,
                               val following: Long? = null,
                               val posts: Long? = null,
                               val profile_photo: String? = null,
                               val username: String? = null,
                               val website: String? = null)