package com.sec.recyclerview

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil

object DiffConfig {

    fun default(): AsyncDifferConfig<ItemCell> {
        return AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<ItemCell>() {

            override fun areItemsTheSame(oldItem: ItemCell, newItem: ItemCell): Boolean {
                return oldItem.itemId() == newItem.itemId()
            }

            override fun areContentsTheSame(oldItem: ItemCell, newItem: ItemCell): Boolean {
                return oldItem.itemContent() == newItem.itemContent()
            }

            // ??
            override fun getChangePayload(oldItem: ItemCell, newItem: ItemCell): Any? {
                return super.getChangePayload(oldItem, newItem)
            }

        }).build()
    }

}