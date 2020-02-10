package com.wbh.firebaseapp.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.wbh.firebaseapp.Model.Movie
import com.wbh.firebaseapp.R
import com.wbh.firebaseapp.Services.Holder
import kotlinx.android.synthetic.main.custom_item.view.*

class Adapter(internal var context: Context, internal var moviesList: List<Movie>) : RecyclerView.Adapter<Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_item, parent, false)

        return Holder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {

        Log.d("list Movies",moviesList[position].name)
        holder.name.text = moviesList[position].name
        holder.firstDate.text = context.getString(R.string.date_air) + ": " + moviesList[position].first_air_date
        holder.original_name.text = context.getString(R.string.original_name) + ": " + moviesList[position].original_name
        holder.androidLayout.setOnClickListener {
            Toast.makeText(context, holder.name.text, Toast.LENGTH_SHORT).show()
        }
    }
    override fun getItemCount(): Int {
        return moviesList.size
    }
}