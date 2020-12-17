package com.sec.recyclerview

import android.view.View
import androidx.annotation.LayoutRes
import com.sec.recyclerview.model.Topic

interface ItemCell {

    @LayoutRes
    fun layoutResId(): Int

    /**
     * item标志，用于比较item是否一样
     */
    fun itemId(): String

    /**
     * item内容，用于比较内容是否一致
     */
    fun itemContent(): String

    fun onCreateViewHolder(itemView: View, support: MainSupport): ItemVH

    fun getTopic(): Topic
}