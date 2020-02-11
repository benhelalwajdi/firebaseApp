package com.wbh.firebaseapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.JsonObject
import com.wbh.firebaseapp.Interfaces.MovieApi
import com.wbh.firebaseapp.Model.Movie
import com.wbh.firebaseapp.R
import com.wbh.firebaseapp.Adapter.Adapter
import com.wbh.firebaseapp.Constant.Constant
import com.wbh.firebaseapp.Services.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    private var firebaseAuth: FirebaseAuth? = null
    internal var androidRecyclerView: RecyclerView? = null
    internal var androidList: MutableList<Movie>? = null
    internal var customAdapter: Adapter? = null

    val retrofit = Retrofit.Builder()
        .baseUrl(Constant().Url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(MovieApi::class.java)
    val Entitys = service.getTvPopular(Constant().api)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()
        androidRecyclerView = findViewById(R.id.android_recycler_view) as RecyclerView
        androidList = ArrayList<Movie>()
        setValues()
    }

    private fun setValues() {
        Entitys.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d("TAN", response.body()?.getAsJsonArray("results").toString())

                val jsonArray = response.body()?.getAsJsonArray("results")
                val jsonObject: JsonObject? = jsonArray?.get(0)?.asJsonObject
                val original_name= jsonObject?.get("original_name")
                Log.d("TAN", original_name.toString())
                for(aa in jsonArray!!) {
                    androidList!!.add(
                        Movie(aa.asJsonObject.get("original_name").toString(),aa.asJsonObject.get("genre_ids").asJsonArray,
                            aa.asJsonObject.get("name").toString(),aa.asJsonObject.get("origin_country").asJsonArray,
                            aa.asJsonObject.get("vote_count").toString(),aa.asJsonObject.get("first_air_date").toString(),
                            aa.asJsonObject.get("backdrop_path").toString(),aa.asJsonObject.get("original_language").asString,
                            aa.asJsonObject.get("id").toString(),aa.asJsonObject.get("vote_average").asString,
                            aa.asJsonObject.get("overview").toString(),aa.asJsonObject.get("poster_path").asString))
                    customAdapter = Adapter(this@MainActivity, androidList!!)
                    val mLayoutManager = LinearLayoutManager(applicationContext)
                    androidRecyclerView!!.layoutManager = mLayoutManager
                    androidRecyclerView!!.adapter = customAdapter
                }
            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("TAN", "Error : $t")
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this.applicationContext, "User LogOut", Toast.LENGTH_LONG).show()
        UserService().logOutAction(this.firebaseAuth!!, this.applicationContext)
    }
}
