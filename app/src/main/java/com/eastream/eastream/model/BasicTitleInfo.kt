package com.eastream.eastream.model

data class BasicTitleInfo(
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
    )
