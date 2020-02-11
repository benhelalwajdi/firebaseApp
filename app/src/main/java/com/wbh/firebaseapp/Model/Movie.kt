package com.wbh.firebaseapp.Model

import com.google.gson.JsonArray
import java.io.Serializable

class Movie(
    var original_name: String?, var genre_ids: JsonArray,
    var name: String?, var origin_country: JsonArray,
    var vote_count: String?, var first_air_date: String?,
    var backdrop_path: String?, var original_language: String,
    var id: String?, var vote_average: String,
    var overview: String?, var poster_path: String?
) : Serializable {
}