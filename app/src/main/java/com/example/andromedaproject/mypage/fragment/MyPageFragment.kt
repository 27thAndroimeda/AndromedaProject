package com.example.andromedaproject.mypage.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.andromedaproject.R
import com.example.andromedaproject.mypage.adapter.TabLayoutAdapter
import kotlinx.android.synthetic.main.fragment_my_page.*


class MyPageFragment : Fragment() {
    private val tabLayoutAdapter by lazy { TabLayoutAdapter(childFragmentManager) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
    }


    private fun setUpViewPager() {
        viewpager_mypage.adapter = tabLayoutAdapter
        tablayout_mypage.setupWithViewPager(viewpager_mypage)
    }
}