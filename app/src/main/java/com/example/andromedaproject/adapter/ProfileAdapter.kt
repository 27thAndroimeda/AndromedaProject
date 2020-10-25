package com.example.andromedaproject.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.andromedaproject.viewholder.ProfileViewHolder
import com.example.andromedaproject.R
import com.example.andromedaproject.model.UserInformationModel
import com.example.andromedaproject.ui.ProfileDetailActivity

class ProfileAdapter(private val context: Context) : RecyclerView.Adapter<ProfileViewHolder>() {
    var datas = mutableListOf<UserInformationModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_profile, parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holderLinear: ProfileViewHolder, position: Int) {
        holderLinear.bind(datas[position])

        val model = datas.get(position)

        holderLinear.itemView.setOnClickListener {
            val intent = Intent(context, ProfileDetailActivity::class.java)
            intent.putExtra("profileImageDetail", model.userProfileImage)
            intent.putExtra("userNameDetail", model.userName)
            intent.putExtra("userDescriptionDetail", model.userDescription)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    fun swapItems(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition..toPosition - 1) {
                datas.set(i, datas.set(i + 1, datas.get(i)))
            }
        } else {
            for (i in fromPosition..toPosition + 1) {
                datas.set(i, datas.set(i - 1, datas.get(i)))
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    fun deleteItems(position: Int) {
        datas.removeAt(position)
        notifyItemRemoved(position)
    }
}