package com.example.andromedaproject.userprofile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andromedaproject.databinding.FragmentProfileListBinding
import com.example.andromedaproject.userprofile.adapter.ProfileAdapter
import com.example.andromedaproject.userprofile.model.UserInformationModel
import com.example.andromedaproject.utils.DragManageAdapter
import com.example.andromedaproject.utils.ItemHorizontalDivider
import com.example.andromedaproject.utils.ItemVerticalDivider
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
        setProfileListAdapter(view)
        changeLayoutManager(view)
        moveItemHelper(view)
        deleteItemHelper()
        loadDatas()
    }

    private fun setProfileListAdapter(view: View) {
        profileAdapter = ProfileAdapter(view.context)
        recyclerview_profile.adapter = profileAdapter
        recyclerview_profile.layoutManager = LinearLayoutManager(view.context)
        recyclerview_profile.addItemDecoration(ItemHorizontalDivider(20))
        recyclerview_profile.addItemDecoration(ItemVerticalDivider(20))
    }

    private fun changeLayoutManager(view: View) {
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

    private fun moveItemHelper(view: View) {
        val callback = DragManageAdapter(
            profileAdapter, view.context
        )
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(recyclerview_profile)
    }

    private fun deleteItemHelper() {
        var itemTouchHelper = ItemTouchHelper(SwipeToDeleteManager(profileAdapter))
        itemTouchHelper.attachToRecyclerView(recyclerview_profile)
    }

    private fun loadDatas() {
        val lorem = LoremIpsum.getInstance()
        val loremList = (1..150).map {
            UserInformationModel(
                "https://picsum.photos/${Random.nextInt(100, 300)}",
                lorem.name, lorem.getWords(6, 20)
            )
        }

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