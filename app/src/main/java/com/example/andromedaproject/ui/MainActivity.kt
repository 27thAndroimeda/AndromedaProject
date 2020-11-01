package com.example.andromedaproject.ui

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.andromedaproject.R
import com.example.andromedaproject.userprofile.adapter.MainViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setResult(Activity.RESULT_OK, intent)
        setViewPagerAdapter()
        setBottomNavigation()
        viewPagerSlide()

    }
    fun setViewPagerAdapter() {

        viewpager_fragment.adapter = MainViewPagerAdapter(supportFragmentManager)
        viewpager_fragment.offscreenPageLimit = 2
    }

    fun setBottomNavigation() {
        bottom_navigation_main.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()
            when (it.itemId) {
                R.id.item_profile -> index = 0
                R.id.item_home -> index = 1
                R.id.item_mypage -> index = 2
            }
            viewpager_fragment.currentItem = index
            true
        }
    }

    fun viewPagerSlide() {
        viewpager_fragment.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                bottom_navigation_main.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }

}