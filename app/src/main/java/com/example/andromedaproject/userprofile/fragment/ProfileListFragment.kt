package com.example.andromedaproject.userprofile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.andromedaproject.R
import com.example.andromedaproject.databinding.FragmentProfileListBinding
import com.example.andromedaproject.userprofile.adapter.ProfileAdapter
import com.example.andromedaproject.userprofile.model.UserInformationModel
import com.example.andromedaproject.utils.DragManageAdapter
import com.example.andromedaproject.utils.ItemDivider
import com.example.andromedaproject.utils.SwipeToDeleteAdapter
import com.thedeanda.lorem.LoremIpsum
import kotlinx.android.synthetic.main.fragment_profile_list.*
import kotlin.random.Random

class ProfileListFragment : Fragment() {
    private lateinit var mBinding: FragmentProfileListBinding
    var datas = mutableListOf<UserInformationModel>()
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
        init(view)
        changeLayoutManager(view)
    }

    fun init(view: View) {
        profileAdapter = ProfileAdapter(view.context)
        recyclerview_profile.adapter = profileAdapter
        recyclerview_profile.layoutManager = LinearLayoutManager(view.context)
        recyclerview_profile.addItemDecoration(ItemDivider(20, 20))
        moveItemHelper(view)
        deleteItemHelper()
        loadDatas()
    }

    fun changeLayoutManager(view: View) {
        button_change_layoutmanager.setOnClickListener {
            if (recyclerview_profile.layoutManager is GridLayoutManager) {
                recyclerview_profile.layoutManager = LinearLayoutManager(view.context)
            } else {
                recyclerview_profile.layoutManager =
                    GridLayoutManager(view.context, 2, GridLayoutManager.VERTICAL, false)
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
        var itemTouchHelper = ItemTouchHelper(SwipeToDeleteAdapter(profileAdapter))
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
        datas.apply {
            for (i in 0 until loremList.size) {
                add(
                    loremList.get(i)
                )
            }
        }
        profileAdapter.datas = datas
        profileAdapter.notifyDataSetChanged()
    }
}