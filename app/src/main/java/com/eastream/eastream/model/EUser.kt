package com.eastream.eastream.model

data class EUser(val id: String?,
                 val userId: String,
                 val displayName: String,
                 val avatarUrl: String,
                 val fName: String,
                 val lName: String) {
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "user_id" to this.userId,
            "display_name" to this.displayName,
            "first_name" to this.fName,
            "last_name" to this.lName,
            "profile_pic" to avatarUrl
        )
    }
}
