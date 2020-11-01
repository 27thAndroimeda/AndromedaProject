package com.example.andromedaproject.userprofile.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.andromedaproject.userprofile.fragment.HomeFragment
import com.example.andromedaproject.userprofile.fragment.MyPageFragment
import com.example.andromedaproject.userprofile.fragment.ProfileListFragment

class MainViewPagerAdapter (fm: FragmentManager): FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getItem(position: Int) :Fragment{
        return when(position){
            0 -> ProfileListFragment()
            1 -> HomeFragment()
            else -> MyPageFragment()
        }
    }
    override fun getCount() = 3
}