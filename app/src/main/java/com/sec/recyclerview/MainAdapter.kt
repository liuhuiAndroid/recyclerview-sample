package com.sec.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.*

class MainAdapter : RecyclerView.Adapter<ItemVH>() {

    private val config: AsyncDifferConfig<ItemCell> =
        AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<ItemCell>() {

            override fun areItemsTheSame(oldItem: ItemCell, newItem: ItemCell): Boolean {
                return oldItem.itemId() == newItem.itemId()
            }

            override fun areContentsTheSame(oldItem: ItemCell, newItem: ItemCell): Boolean {
                return oldItem.itemContent() == newItem.itemContent()
            }

        }).build()

    private val differ = AsyncListDiffer(AdapterListUpdateCallback(this), config)

    class MainViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    /**
     * 更新列表
     */
    fun submitList(list: List<ItemCell>, callback: () -> Unit = {}) {
        differ.submitList(list, callback)
    }

    /**
     * 当前列表
     */
    fun currentList(): MutableList<ItemCell> = differ.currentList

    override fun getItemViewType(position: Int) = differ.currentList[position].layoutResId()

    override fun getItemCount() = differ.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {
        differ.currentList.forEach {
            if (viewType == it.layoutResId()) {
                return it.onCreateViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        viewType,
                        parent,
                        false
                    )
                )
            }
        }
        throw IllegalArgumentException("viewType not found")
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        // do nothing
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int, payloads: MutableList<Any>) {
        holder.bind(differ.currentList[position], payloads)
    }

}