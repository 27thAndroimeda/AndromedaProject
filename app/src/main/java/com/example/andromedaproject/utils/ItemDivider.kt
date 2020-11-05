package com.example.andromedaproject.utils

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class ItemDivider(private val divHeight: Int, private val divWidth: Int): RecyclerView.ItemDecoration(){
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        super.getItemOffsets(outRect, itemPosition, parent)
        outRect.top = divHeight
        outRect.bottom = divHeight
        outRect.left = divWidth
        outRect.right = divWidth
    }
}