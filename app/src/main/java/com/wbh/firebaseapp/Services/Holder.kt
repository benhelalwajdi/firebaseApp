package com.wbh.firebaseapp.Services

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wbh.firebaseapp.R

class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var androidLayout: View
    var name: TextView
    var firstDate: TextView
    var original_name: TextView

    init {

        androidLayout = itemView.findViewById(R.id.android_layout)
        name = itemView.findViewById(R.id.name) as TextView
        firstDate = itemView.findViewById(R.id.firstDate) as TextView
        original_name = itemView.findViewById(R.id.original_name) as TextView
    }
}