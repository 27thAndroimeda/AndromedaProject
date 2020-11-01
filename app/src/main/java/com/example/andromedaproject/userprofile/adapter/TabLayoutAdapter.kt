package com.example.andromedaproject.userprofile.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.andromedaproject.userprofile.fragment.FriendsListFragment
import com.example.andromedaproject.userprofile.fragment.InformationFragment

class TabLayoutAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> InformationFragment()
            else -> FriendsListFragment()

        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Info"
            else -> "Friends"
        }
    }
}