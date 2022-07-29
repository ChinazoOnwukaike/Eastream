package com.eastream.eastream.model

data class Favorites(val titles: MutableList<String>) {
    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "titles" to this.titles,
        )
    }
}