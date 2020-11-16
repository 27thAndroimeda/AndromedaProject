package com.example.andromedaproject.userprofile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.andromedaproject.databinding.FragmentProfileListBinding
import com.example.andromedaproject.userprofile.ProfileRoomHelper
import com.example.andromedaproject.userprofile.RoomProfile
import com.example.andromedaproject.userprofile.adapter.ProfileAdapter
import com.example.andromedaproject.userprofile.model.UserInformationModel
import com.example.andromedaproject.utils.DragManageAdapter
import com.example.andromedaproject.utils.ItemDivider
import com.example.andromedaproject.utils.SwipeToDeleteManager
import com.thedeanda.lorem.LoremIpsum
import kotlinx.android.synthetic.main.fragment_profile_list.*
import kotlin.random.Random

class ProfileListFragment : Fragment() {
    private lateinit var mBinding: FragmentProfileListBinding
    private val data = mutableListOf<UserInformationModel>()

    //    var helper: ProfileRoomHelper? = null
    lateinit var profileAdapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentProfileListBinding.inflate(inflater, container, false).let {
            mBinding = it
            it.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//
//        helper = Room.databaseBuilder(view.context, ProfileRoomHelper::class.java,"profile_data")
//            .allowMainThreadQueries()
//            .build()
//
//        profileAdapter.datas = (helper?.roomProfileDao()?.getAll() ?: mutableListOf()) as MutableList<UserInformationModel>

        profileAdapter = ProfileAdapter(view.context)
        recyclerview_profile.adapter = profileAdapter

        recyclerview_profile.layoutManager = LinearLayoutManager(view.context)
        recyclerview_profile.addItemDecoration(ItemDivider(20, 20))

        changeLayoutManager(view)

        moveItemHelper(view)
        deleteItemHelper()

        loadDatas()
    }

    fun changeLayoutManager(view: View) {
        button_change_layoutmanager.setOnClickListener {

            when (recyclerview_profile.layoutManager) {
                is GridLayoutManager -> recyclerview_profile.layoutManager =
                    LinearLayoutManager(view.context)

                is LinearLayoutManager -> recyclerview_profile.layoutManager =
                    GridLayoutManager(view.context, 2, GridLayoutManager.VERTICAL, false)

                else -> throw IllegalArgumentException("unknown layout manager: ${recyclerview_profile.layoutManager}")
            }
        }
    }

    fun moveItemHelper(view: View) {
        val callback = DragManageAdapter(
            profileAdapter, view.context
        )
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(recyclerview_profile)
    }

    fun deleteItemHelper() {
        var itemTouchHelper = ItemTouchHelper(SwipeToDeleteManager(profileAdapter))
        itemTouchHelper.attachToRecyclerView(recyclerview_profile)
    }

    fun loadDatas() {
        val lorem = LoremIpsum.getInstance()
        val loremList = (1..100).map {
            UserInformationModel(
                "https://picsum.photos/${Random.nextInt(100, 300)}",
                lorem.name, lorem.getWords(6, 20)
            )
        }

//        val profile = RoomProfile("https://picsum.photos/${Random.nextInt(100, 300)}",
//        lorem.name, lorem.getWords(6,20))
//        helper?.roomProfileDao()?.insert(profile)
//        profileAdapter.datas.clear()
//        profileAdapter.datas.addAll(helper?.roomProfileDao()?.getAll() ?: mutableListOf())

        data.apply {
            for (i in loremList.indices) {
                add(
                    loremList.get(i)
                )
            }
        }
        profileAdapter.datas = data
        profileAdapter.notifyDataSetChanged()
    }
}