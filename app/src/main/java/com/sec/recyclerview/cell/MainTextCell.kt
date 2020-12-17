package com.sec.recyclerview.cell

import android.view.View
import com.sec.recyclerview.ItemCell
import com.sec.recyclerview.ItemVH
import com.sec.recyclerview.R
import kotlinx.android.synthetic.main.main_text_view.view.*

class MainTextCell(val content: String) : ItemCell {

    override fun layoutResId(): Int = R.layout.main_text_view

    override fun itemId(): String = "${System.currentTimeMillis()}"

    override fun itemContent(): String = content

    override fun onCreateViewHolder(itemView: View): ItemVH = MainTextCellVH(itemView)
}

class MainTextCellVH(itemView: View) : ItemVH(itemView) {

    init {

    }

    override fun bind(itemCell: ItemCell, payloads: MutableList<Any>) {
        when {
            payloads.isEmpty() -> bindAll(itemCell, itemCell.itemContent())
            "status" == payloads[0].toString() -> bindText(itemCell, itemCell.itemContent())
            else -> bindAll(itemCell, itemCell.itemContent())
        }
    }

    private fun bindAll(itemCell: ItemCell, content: String) {
        if (itemCell is MainTextCell) {
            itemView.textContent.text = content
        }
    }

    private fun bindText(itemCell: ItemCell, content: String) {
        itemView.textContent.text = content
    }
}