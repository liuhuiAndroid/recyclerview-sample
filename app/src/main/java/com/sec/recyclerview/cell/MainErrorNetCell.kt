package com.sec.recyclerview.cell

import android.view.View
import com.sec.recyclerview.ItemCell
import com.sec.recyclerview.ItemVH
import com.sec.recyclerview.MainSupport
import com.sec.recyclerview.R
import com.sec.recyclerview.ext.debounceClick
import com.sec.recyclerview.model.Topic
import kotlinx.android.synthetic.main.main_error_net.view.*

class MainErrorNetCell : ItemCell {

    override fun layoutResId() = R.layout.main_error_net

    override fun itemId() = "MainErrorNetCell"

    override fun itemContent() = "MainErrorNetCell"

    override fun onCreateViewHolder(itemView: View, support: MainSupport) =
        MainErrorNetVH(itemView, support)

    override fun getTopic(): Topic? = null
}

class MainErrorNetVH(itemView: View, support: MainSupport) : ItemVH(itemView) {

    init {
        itemView.reload.debounceClick {
            support.retry?.invoke()
        }
    }
}