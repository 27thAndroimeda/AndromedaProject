package com.example.andromedaproject.userprofile.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.andromedaproject.home.HomeFragment
import com.example.andromedaproject.mypage.fragment.MyPageFragment
import com.example.andromedaproject.userprofile.fragment.ProfileListFragment
import java.lang.IllegalStateException

class MainViewPagerAdapter(fm: FragmentManager) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> ProfileListFragment()
            1 -> HomeFragment()
            2 -> MyPageFragment()
            else -> throw IllegalStateException("Fragment Position : $position ")
        }
    }

    override fun getCount() = 3
}