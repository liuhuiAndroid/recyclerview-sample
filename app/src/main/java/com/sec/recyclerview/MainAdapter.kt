package com.sec.recyclerview

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IntRange
import androidx.recyclerview.widget.*
import com.sec.recyclerview.const.AdapterNotifyConst

class MainAdapter(private val support: MainSupport) : RecyclerView.Adapter<ItemVH>() {

    /**
     * 当前列表
     */
    val currentList: MutableList<ItemCell>
        get() = differ.currentList

    private val differ = AsyncListDiffer(AdapterListUpdateCallback(this), DiffConfig.default())

    private val keyList = mutableListOf<Int>()

    private val sparseArray by lazy {
        SparseArray<List<ItemCell>>()
    }

    /**
     * 全量更新数据
     */
    fun submitList(list: List<ItemCell>, callback: () -> Unit = {}) {
        keyList.clear()
        sparseArray.clear()
        differ.submitList(list, callback)
    }

    /**
     * 按照范围提交数据
     * @param position 局部更新的位置，范围>=0
     */
    fun submitList(
        @IntRange(from = 0) position: Int, itemCell: ItemCell,
        callback: () -> Unit = {}
    ) {
        val temp = listOf(itemCell)
        submitList(position, temp, callback)
    }

    /**
     * 按照范围提交数据
     * @param position 局部更新的位置，范围>=0
     */
    fun submitList(
        @IntRange(from = 0) position: Int, temp: List<ItemCell>,
        callback: () -> Unit = {}
    ) {
        if (!keyList.contains(position)) {
            keyList.add(position)
        }
        sparseArray.put(position, temp)
        val resultList = mutableListOf<ItemCell>()
        keyList.sorted().forEach { key ->
            resultList.addAll(sparseArray[key, mutableListOf()])
        }
        differ.submitList(resultList, callback)
    }

    /**
     * 添加新消息
     */
    fun addNewTopic(list: List<ItemCell>, callback: () -> Unit = {}) {
        val result = mutableListOf<ItemCell>()
        differ.currentList.forEach {
            result.add(it)
        }
        list.forEach {
            result.add(it)
        }
        differ.submitList(result, callback)
    }

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
                    ), support
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

    /**
     * 设置消息已读状态
     */
    fun updateTitle(courses: Int, name: String) {
        differ.currentList.forEachIndexed { index, itemCell ->
            val topic = itemCell.getTopic()
            if (topic != null && topic.courses == courses) {
                topic.name = name
                notifyItemChanged(index, AdapterNotifyConst.TEXT_TITLE)
            }
        }
    }

}