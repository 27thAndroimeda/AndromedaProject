package com.example.andromedaproject.userprofile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andromedaproject.R
import com.example.andromedaproject.utils.ItemDivider
import com.example.andromedaproject.utils.DragManageAdapter
import com.example.andromedaproject.userprofile.adapter.ProfileAdapter
import com.example.andromedaproject.utils.SwipeToDeleteAdapter
import com.example.andromedaproject.userprofile.model.UserInformationModel
import kotlinx.android.synthetic.main.fragment_profile_list.*

import kotlin.random.Random

class ProfileListFragment : Fragment() {
    var datas = mutableListOf<UserInformationModel>()
    lateinit var profileAdapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    fun init(view:View) {
        profileAdapter = ProfileAdapter(view.context)
        recyclerview_profile.adapter = profileAdapter
        recyclerview_profile.layoutManager = LinearLayoutManager(view.context)
        recyclerview_profile.addItemDecoration(ItemDivider(20, 20))
        moveItemHelper(view)
        deleteItemHelper()
        loadDatas()
    }

    fun moveItemHelper(view:View) {
        val callback = DragManageAdapter(
            profileAdapter, view.context,
            ItemTouchHelper.UP
                .or(
                    ItemTouchHelper
                        .DOWN
                ),
            ItemTouchHelper.LEFT
                .or(
                    ItemTouchHelper
                        .RIGHT
                )
        )
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(recyclerview_profile)
    }

    fun deleteItemHelper(){
        var itemTouchHelper = ItemTouchHelper(SwipeToDeleteAdapter(profileAdapter))
        itemTouchHelper.attachToRecyclerView(recyclerview_profile)
    }

    fun loadDatas() {
        datas.apply {
            add(
                UserInformationModel(
                    userProfileImage = "https://picsum.photos/${Random.nextInt(100, 300)}",
                    userName = "Hand Peace",
                    userDescription = "27기 ON SOPT 부회장"
                )
            )
            add(
                UserInformationModel(
                    userProfileImage = "https://picsum.photos/${Random.nextInt(100, 300)}",
                    userName = "손평화",
                    userDescription = "안드로이드 파트"
                )
            )
            add(
                UserInformationModel(
                    userProfileImage = "https://picsum.photos/${Random.nextInt(100, 300)}",
                    userName = "sson_peace7",
                    userDescription = "평화 인스타 아이디!!!"
                )
            )
            add(
                UserInformationModel(
                    userProfileImage = "https://picsum.photos/${Random.nextInt(100, 300)}",
                    userName = "Hand Peace",
                    userDescription = "My Description!!!"
                )
            )
            add(
                UserInformationModel(
                    userProfileImage = "https://picsum.photos/${Random.nextInt(100, 300)}",
                    userName = "Hand Peace",
                    userDescription = "Here is profile list fragment"
                )
            )
            add(
                UserInformationModel(
                    userProfileImage = "https://picsum.photos/${Random.nextInt(100, 300)}",
                    userName = "Hand Peace",
                    userDescription = "27기 ON SOPT 부회장"
                )
            )
            add(
                UserInformationModel(
                    userProfileImage = "https://picsum.photos/${Random.nextInt(100, 300)}",
                    userName = "Hand Peace",
                    userDescription = "27기 ON SOPT 부회장"
                )
            )
            profileAdapter.datas = datas
            profileAdapter.notifyDataSetChanged()
        }
    }
}