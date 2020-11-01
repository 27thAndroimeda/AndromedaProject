package com.example.andromedaproject.utils

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.andromedaproject.userprofile.adapter.ProfileAdapter


//DragManageAdapter는 만들 때 생성자로 넣어놓은 ProfileAdapter, Context, Int, Int이렇게 4개가 꼭 필요합니다!
class DragManageAdapter(adapter: ProfileAdapter, context: Context, dragDirs: Int, swipeDirs: Int) :
    ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
    //위에서 생성자로 넣은 adapter는 ProfileAdapter인데 알아보기 쓸 때 그냥 adapter라고 쓰기보다
    //한눈에 알아볼 수 있도록 profileAdapter라는 변수로 다시 설정해주겠습니다.
    val profileAdapter = adapter

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        //adapterposition을 통해서 리사이클러뷰 아이템의 포지션을 알아낼 수 있습니다.
        //profileAdapter에 구현되어있는 swapItems에 각각 포지션값들을 넣어줍니다.
        profileAdapter.swapItems(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }

    override fun getSwipeDirs(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return 0
    }
}