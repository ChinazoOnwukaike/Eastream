package com.eastream.eastream.model

data class ETitle(
    val id: Long? = null,
    val title: String? = null,
    val summary: String? = null,
    val poster: String? = null,
    val networks: List<String>? = null,
    val networkImg: String? = null,
    val showLink: String? = null,
    val backdrop: String? = null,
    val voteAvg: Double? = null,
    val popularity: Double? = null
) {
    fun toMap(): MutableMap<String, Any?> {
        return mutableMapOf(
            "showId" to this.id,
            "title" to this.title,
            "summary" to this.summary,
            "poster" to this.poster,
            "networks" to this.networks,
            "networkImg" to this.networkImg,
            "showLInk" to this.showLink,
            "backdrop" to this.backdrop
        )
    }
}