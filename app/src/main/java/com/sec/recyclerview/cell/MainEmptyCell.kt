package com.sec.recyclerview.cell

import android.view.View
import com.sec.recyclerview.ItemCell
import com.sec.recyclerview.ItemVH
import com.sec.recyclerview.MainSupport
import com.sec.recyclerview.R
import com.sec.recyclerview.const.AdapterNotifyConst
import com.sec.recyclerview.ext.containsEntity
import com.sec.recyclerview.ext.debounceClick
import com.sec.recyclerview.ext.sameAs
import com.sec.recyclerview.model.Topic
import kotlinx.android.synthetic.main.main_text_view.view.*

class MainEmptyCell : ItemCell {

    override fun layoutResId(): Int = R.layout.main_empty_view

    override fun itemId(): String = "${System.currentTimeMillis()}"

    override fun itemContent(): String = "${System.currentTimeMillis()}"

    override fun onCreateViewHolder(itemView: View, support: MainSupport): ItemVH =
        MainEmptyCellVH(itemView)

    override fun getTopic(): Topic? = null
}

class MainEmptyCellVH(itemView: View) : ItemVH(itemView)