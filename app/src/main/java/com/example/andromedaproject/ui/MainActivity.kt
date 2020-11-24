package com.example.andromedaproject.ui

import android.app.Activity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.andromedaproject.R
import com.example.andromedaproject.userprofile.adapter.MainViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(),GestureDetector.OnGestureListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setResult(Activity.RESULT_OK, intent)

        setViewPagerAdapter()
        setBottomNavigation()
        tabAndSlideView()

    }

    private fun setViewPagerAdapter() {
        viewpager_fragment.adapter = MainViewPagerAdapter(supportFragmentManager)
        viewpager_fragment.offscreenPageLimit = 2
    }

    private fun setBottomNavigation() {
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

    private fun tabAndSlideView() {
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

    override fun onDown(p0: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        TODO("Not yet implemented")
    }

    override fun onLongPress(p0: MotionEvent?) {}

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        TODO("Not yet implemented")
    }

    override fun onShowPress(p0: MotionEvent?) {}



}