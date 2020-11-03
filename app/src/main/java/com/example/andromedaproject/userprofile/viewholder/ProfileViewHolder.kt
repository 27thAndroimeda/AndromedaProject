package com.example.andromedaproject.userprofile.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.andromedaproject.R
import com.example.andromedaproject.userprofile.model.UserInformationModel

class ProfileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    val imageviewProfile: ImageView = itemView.findViewById(R.id.imageview_profile)
    val userName: TextView = itemView.findViewById(R.id.textview_user_name)
    val userDescription: TextView = itemView.findViewById(R.id.textview_user_description)

    fun bind(userInformationModel: UserInformationModel){
        Glide.with(itemView).load(userInformationModel.userProfileImage).into(imageviewProfile)
        userName.text = userInformationModel.userName
        userDescription.text = userInformationModel.userDescription
        userDescription.isSelected = true
    }
}