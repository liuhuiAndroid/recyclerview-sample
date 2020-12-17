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

class MainTextCell(private val topic: Topic) : ItemCell {

    override fun layoutResId(): Int = R.layout.main_text_view

    override fun itemId(): String = "${topic.courses}"

    override fun itemContent(): String = topic.toString()

    override fun onCreateViewHolder(itemView: View, support: MainSupport): ItemVH =
        MainTextCellVH(itemView, support)

    override fun getTopic(): Topic = topic
}

class MainTextCellVH(itemView: View, support: MainSupport) : ItemVH(itemView) {

    init {
        itemView.textContent.debounceClick {
            support.invokeClickCallback(absoluteAdapterPosition, 1)
        }
    }

    override fun bind(itemCell: ItemCell, payloads: MutableList<Any>) {
        payloads.containsEntity(AdapterNotifyConst.TEXT_TITLE) {
            bindText(itemCell, itemCell.getTopic())
            return
        }
        bindAll(itemCell, itemCell.getTopic())
    }

    private fun bindAll(itemCell: ItemCell, topic: Topic?) {
        itemCell.sameAs<MainTextCell> {
            itemView.textTitle.text = topic?.name
            itemView.textContent.text = topic?.imageUrl
        }
    }

    private fun bindText(itemCell: ItemCell, topic: Topic?) {
        itemView.textTitle.text = topic?.name
    }
}