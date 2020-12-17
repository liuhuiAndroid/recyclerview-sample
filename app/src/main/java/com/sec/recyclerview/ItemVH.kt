package com.sec.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class ItemVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    open fun bind(itemCell: ItemCell, payloads: MutableList<Any>) {
        // do nothing
    }

}