package com.example.andromedaproject.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andromedaproject.R
import com.example.andromedaproject.Utils.ItemDecoration
import com.example.andromedaproject.adapter.DragManageAdapter
import com.example.andromedaproject.adapter.ProfileAdapter
import com.example.andromedaproject.adapter.SwipeToDeleteAdapter
import com.example.andromedaproject.model.UserInformationModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var datas = mutableListOf<UserInformationModel>()
    lateinit var profileAdapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setResult(Activity.RESULT_OK, intent)
        init()
    }

    fun init() {
        profileAdapter = ProfileAdapter(this)
        recyclerview_profile.adapter = profileAdapter
        recyclerview_profile.layoutManager = LinearLayoutManager(this)
        recyclerview_profile.addItemDecoration(ItemDecoration(5, 5))
        moveItemHelper()
        deleteItemHelper()
        loadDatas()
    }

    fun moveItemHelper() {
        val callback = DragManageAdapter(
            profileAdapter, this,
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

    fun deleteItemHelper() {
        var itemTouchHelper = ItemTouchHelper(SwipeToDeleteAdapter(profileAdapter))
        itemTouchHelper.attachToRecyclerView(recyclerview_profile)
    }

    fun loadDatas() {
        datas.apply {
            add(
                UserInformationModel(
                    userProfileImage = R.drawable.sopt_logo,
                    userName = "Hand Peace",
                    userDescription = "27기 ON SOPT 부회장"
                )
            )
            add(
                UserInformationModel(
                    userProfileImage = R.drawable.sopt_logo,
                    userName = "손평화",
                    userDescription = "안드로이드 파트"
                )
            )
            add(
                UserInformationModel(
                    userProfileImage = R.drawable.sopt_logo,
                    userName = "sson_peace7",
                    userDescription = "평화 인스타 아이디!!!"
                )
            )
            add(
                UserInformationModel(
                    userProfileImage = R.drawable.sopt_logo,
                    userName = "Hand Peace",
                    userDescription = "27기 ON SOPT 부회장"
                )
            )
            add(
                UserInformationModel(
                    userProfileImage = R.drawable.sopt_logo,
                    userName = "Hand Peace",
                    userDescription = "27기 ON SOPT 부회장"
                )
            )
            profileAdapter.datas = datas
            profileAdapter.notifyDataSetChanged()
        }
    }

}