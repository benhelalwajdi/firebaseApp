package com.wbh.firebaseapp.Interfaces
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
interface MovieApi {
    @GET("/3/tv/popular?api_key=a64a27dcf41da11a62705aa1b5b0fa4b&language=en-US&page=1")
    fun getMovies(): Call<JsonObject>
}
