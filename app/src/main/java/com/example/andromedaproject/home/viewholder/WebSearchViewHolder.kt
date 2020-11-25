package com.example.andromedaproject.home.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.andromedaproject.R
import com.example.andromedaproject.home.model.DocumentsData

class WebSearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val title = itemView.findViewById<TextView>(R.id.textview_title)
    val contents = itemView.findViewById<TextView>(R.id.textview_contents)
    val url = itemView.findViewById<TextView>(R.id.textview_url)
    val datetime = itemView.findViewById<TextView>(R.id.textview_datetime)

    fun bind(datas: DocumentsData){
        title.text = datas.title
        contents.text = datas.contents
        url.text = datas.url
        datetime.text = datas.datetime
    }
}