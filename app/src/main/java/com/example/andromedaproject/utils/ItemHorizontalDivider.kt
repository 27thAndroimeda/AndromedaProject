package com.example.andromedaproject.utils

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView

class ItemHorizontalDivider(private val divWidth: Int): RecyclerView.ItemDecoration(){
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        super.getItemOffsets(outRect, itemPosition, parent)
        outRect.left = divWidth
        outRect.right = divWidth
    }
}