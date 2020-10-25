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

    //리사이클러뷰 아이템 drag관련 메서드
    fun swapItems(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition..toPosition - 1) {
                //datas는 위에서 MutableList로 작성했는데 set함수를 통해서 각각의 인덱스에 값을 부여할 수 있습니다.
                // ex) stringList.set(3, "a") -> stringList의 3번 인덱스에 a를 넣겠다.
                datas.set(i, datas.set(i + 1, datas.get(i)))
            }
        } else {
            for (i in fromPosition..toPosition + 1) {
                datas.set(i, datas.set(i - 1, datas.get(i)))
            }
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    //아이템 삭제하는 메서드
    fun deleteItems(position: Int) {
        //리스트의 삭제는 removeAt이라는 함수를 사용합니다. 괄호한에 본인이 지우고 싶은 인덱스를 넣으면 됩니다!
        datas.removeAt(position)
        notifyItemRemoved(position)
    }
}