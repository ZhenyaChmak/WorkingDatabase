package com.example.workingdatabase.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addDecorationUser(bottom: Int) {
    addItemDecoration(object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val itemCount = parent.adapter?.itemCount ?: return
            val itemPosition = parent.getChildLayoutPosition(view)
            if (itemPosition != itemCount - 1) {
                outRect.bottom = bottom
            }
        }
    })

}

