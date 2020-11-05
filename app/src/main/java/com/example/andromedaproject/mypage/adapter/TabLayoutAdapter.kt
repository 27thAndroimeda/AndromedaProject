package com.example.andromedaproject.mypage.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.andromedaproject.mypage.fragment.FriendsListFragment
import com.example.andromedaproject.mypage.fragment.InformationFragment
import java.lang.IllegalArgumentException

class TabLayoutAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> InformationFragment()
            1 -> FriendsListFragment()
            else -> throw IllegalArgumentException("wrong fragment position: $position")
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Info"
            1 -> "Friends"
            else -> throw IllegalArgumentException("wrong page title: $position")
        }
    }
}