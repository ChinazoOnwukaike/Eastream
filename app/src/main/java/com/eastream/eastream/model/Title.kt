package com.eastream.eastream.model

data class Title(
    val id: Number,
    val title: String,
    val summary: String,
    val poster: String,
    val networks: List<String>,
    val networkImg: String,
    val showLink: String,
    val backdrop: String,
    val voteAvg: Number,
    val popularity: Number
) {
}