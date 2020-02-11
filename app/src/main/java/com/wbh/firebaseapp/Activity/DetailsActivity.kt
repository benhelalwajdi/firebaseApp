package com.wbh.firebaseapp.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.wbh.firebaseapp.Constant.Constant
import com.wbh.firebaseapp.Interfaces.MovieApi
import com.wbh.firebaseapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetailsActivity : AppCompatActivity() {

    private var jsonObject: JsonObject? = null
    private var jsonArray: JsonArray? = null

    lateinit var name: TextView
    lateinit var original_name: TextView
    lateinit var geners : TextView
    lateinit var seasons : TextView
    lateinit var popularity :TextView
    lateinit var production_companies:TextView
    lateinit var image :ImageView
    var c:String = ""

    lateinit var overview : TextView

    val retrofit = Retrofit.Builder()
        .baseUrl(Constant().Url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(MovieApi::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val id = intent.getStringExtra("id")
        c = intent.getStringExtra("url")
        val Entitys = service.getDetailsMovie(id,Constant().api)
        //Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
        setValues(Entitys,c)
    }

    private fun setValues(Entitys: Call<JsonObject>, c: String?) {
        Entitys.enqueue(object : Callback<JsonObject> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Log.d("TAN", response.body()?.toString())
                production_companies = findViewById(R.id.nameProd)
                geners = findViewById(R.id.genres)
                name = findViewById(R.id.name)
                overview = findViewById(R.id.overview)
                original_name = findViewById(R.id.original_name)
                popularity = findViewById(R.id.popularity)
                seasons = findViewById(R.id.season)
                image = findViewById(R.id.image)

                original_name.text = response.body()?.getAsJsonPrimitive("original_name").toString()
                name.text = response.body()?.getAsJsonPrimitive("name").toString()

                jsonArray = response.body()?.getAsJsonArray("genres")
                jsonObject = jsonArray?.get(0)?.asJsonObject
                geners.text = jsonObject?.get("name").toString()

                jsonArray = response.body()?.getAsJsonArray("seasons")
                seasons.text = jsonArray?.size().toString()

                popularity.text = response.body()?.getAsJsonPrimitive("popularity").toString()

                jsonArray = response.body()?.getAsJsonArray("production_companies")
                if (jsonArray!!.size()!= 0) {
                    jsonObject = jsonArray?.get(0)?.asJsonObject
                    production_companies.text = jsonObject?.get("name").toString()
                }else
                {
                    production_companies.text = "There is no name "
                }
                overview.text = response.body()?.getAsJsonPrimitive("overview").toString()


                Log.d("Image" , this@DetailsActivity.c)
                Glide.with(this@DetailsActivity)
                    .load(c)
                    .into(image)


            }

            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("TAN", "Error : $t")
            }
        })
    }
}
