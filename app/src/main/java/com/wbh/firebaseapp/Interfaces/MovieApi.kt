package com.wbh.firebaseapp.Interfaces
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {


    @GET("/3/tv/popular")
    fun getTvPopular(@Query("api_key") api: String): Call<JsonObject>


    @GET("/3/tv/{id}")
    fun getDetailsMovie(@Path("id") id: String,@Query("api_key") api: String) : Call<JsonObject>
}
