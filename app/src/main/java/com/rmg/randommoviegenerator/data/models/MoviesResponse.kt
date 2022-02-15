package com.rmg.randommoviegenerator.data.models

import com.google.gson.annotations.SerializedName
import com.rmg.randommoviegenerator.data.models.Movie

data class MoviesResponse(
    val page: Int,
    val results: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)