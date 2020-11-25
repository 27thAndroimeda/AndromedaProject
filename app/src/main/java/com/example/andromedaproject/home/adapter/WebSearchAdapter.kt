package com.example.andromedaproject.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.andromedaproject.R
import com.example.andromedaproject.ui.WebViewActivity
import com.example.andromedaproject.home.model.DocumentsData
import com.example.andromedaproject.home.viewholder.WebSearchViewHolder
import kotlinx.android.synthetic.main.item_web_search.view.*

class WebSearchAdapter(private val context: Context ) : RecyclerView.Adapter<WebSearchViewHolder>() {
    var datas = listOf<DocumentsData>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebSearchViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_web_search, parent, false)
        return WebSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: WebSearchViewHolder, position: Int) {
        holder.bind(datas[position])

        val eachModel = datas.get(position)

        holder.itemView.textview_url.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("web_url",eachModel.url)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }


}