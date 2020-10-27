package com.example.andromedaproject.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.andromedaproject.adapter.ProfileAdapter

class SwipeToDeleteAdapter (adapter: ProfileAdapter): ItemTouchHelper.SimpleCallback(0,
    ItemTouchHelper.LEFT)
{
    var profileAdapter = adapter

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        var position = viewHolder.adapterPosition
        profileAdapter.deleteItems(position)
    }
}