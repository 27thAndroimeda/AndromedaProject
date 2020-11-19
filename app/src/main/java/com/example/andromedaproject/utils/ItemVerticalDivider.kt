package com.example.andromedaproject.utils

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class ItemVerticalDivider(private val divHeight: Int): RecyclerView.ItemDecoration(){
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        outRect.top = divHeight
        outRect.bottom = divHeight
    }
}