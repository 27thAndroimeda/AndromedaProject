package com.example.andromedaproject.mypage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andromedaproject.R
import com.example.andromedaproject.mypage.model.Repository


class GitHubUserAdapter(private val context: Context) : RecyclerView.Adapter<GitHubUserViewHolder>() {
    var datas = mutableListOf<Repository>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubUserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_friends_list, parent, false)
        return GitHubUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: GitHubUserViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}

class GitHubUserViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
    val userImageGitHub = itemView.findViewById<ImageView>(R.id.imageview_github_user)
    val githubName = itemView.findViewById<TextView>(R.id.textview_github_name)
    val githubId = itemView.findViewById<TextView>(R.id.textview_github_id)

    fun bind(userData: Repository){
        Glide.with(itemView).load(userData.owner.avatar_url).into(userImageGitHub)
        githubName.text = userData.name
        githubId.text = userData.node_id
    }
}