package com.sec.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class ItemVH(itemView: View, val support: MainSupport) : RecyclerView.ViewHolder(itemView) {

    open fun bind(itemCell: ItemCell, payloads: MutableList<Any>) {
        // do nothing
    }

}