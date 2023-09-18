package com.iqonicthemes.qibus_softui.ui.adapter


import android.util.SparseBooleanArray

import java.util.ArrayList

import androidx.recyclerview.widget.RecyclerView

abstract class QIBusSoftUISelectableAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    /*variable declaration*/
    private val mSelectedItems: SparseBooleanArray

    /*item count*/
    val selectedItemCount: Int
        get() = mSelectedItems.size()

    /*get selected item*/
    val selectedItems: List<Int>
        get() {
            val items = ArrayList<Int>(mSelectedItems.size())
            for (i in 0 until mSelectedItems.size()) {
                items.add(mSelectedItems.keyAt(i))
            }
            return items
        }

    /*constructor*/
    init {
        mSelectedItems = SparseBooleanArray()
    }

    fun isSelected(position: Int): Boolean {
        return selectedItems.contains(position)
    }

    /*toggle selection */
    fun toggleSelection(position: Int) {
        if (mSelectedItems.get(position, false)) {
            mSelectedItems.delete(position)
        } else {
            mSelectedItems.put(position, true)
        }
        notifyItemChanged(position)
    }
}