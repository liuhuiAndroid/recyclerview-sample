package com.sec.recyclerview.cell

import android.view.View
import com.sec.recyclerview.ItemCell
import com.sec.recyclerview.ItemVH
import com.sec.recyclerview.MainSupport
import com.sec.recyclerview.R
import com.sec.recyclerview.model.Topic

class MainInitCell : ItemCell {

    override fun layoutResId(): Int = R.layout.main_init_view

    override fun itemId(): String = "MainInitCell"

    override fun itemContent(): String = "MainInitCell"

    override fun onCreateViewHolder(itemView: View, support: MainSupport): ItemVH =
        MainInitCellVH(itemView)

    override fun getTopic(): Topic? = null
}

class MainInitCellVH(itemView: View) : ItemVH(itemView)