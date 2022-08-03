package com.eastream.eastream.model

data class ETitle(
    val id: String? = null,
    val title: String? = null,
    val summary: String? = null,
    val poster: String? = null,
    val networks: List<String>? = null,
    val networkImg: HashMap<String, String>? = null,
    val showLink: HashMap<String, String>? = null,
    val backdrop: String? = null,
    val voteAvg: Double? = null,
    val popularity: Double? = null,
    val TMDBId: Long? = null,
    val year: String? = null
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to this.id,
            "showId" to this.TMDBId,
            "title" to this.title,
            "summary" to this.summary,
            "poster" to this.poster,
            "networks" to this.networks,
            "networkImg" to this.networkImg,
            "showLInk" to this.showLink,
            "backdrop" to this.backdrop,
            "year" to this.year
        )
    }
}