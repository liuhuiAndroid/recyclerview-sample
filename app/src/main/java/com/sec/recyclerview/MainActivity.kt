package com.sec.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sec.recyclerview.cell.MainEmptyCell
import com.sec.recyclerview.cell.MainTextCell
import com.sec.recyclerview.model.Topic
import com.sec.recyclerview.model.topics
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewAdapter: MainAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        smartRefreshLayout.setOnRefreshListener {
            initData()
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = createMainAdapter {
            onClickCallback { position, type ->
                Toast.makeText(this@MainActivity, "$position,$type", Toast.LENGTH_SHORT).show()
            }
        }

        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        viewAdapter.submitList(mutableListOf<ItemCell>().apply {
            add(MainEmptyCell())
        })

        initData()
        refreshData()
    }

    private fun refreshData() {
        GlobalScope.launch(Dispatchers.IO) {
            delay(3000)
            val newCells = mutableListOf<ItemCell>()
            newCells.add(MainTextCell(Topic("A", 1, "2")))
            viewAdapter.addNewTopic(newCells) {
                // 滚动到最新一条数据
                viewManager.scrollToPosition(viewAdapter.itemCount - 1)
            }
        }
    }

    private fun initData() {
        GlobalScope.launch(Dispatchers.IO) {
            // 模拟从网络获取数据
            delay(1500)
            val cells = mutableListOf<ItemCell>()
            topics.forEach {
                cells.add(MainTextCell(it))
            }
            viewAdapter.submitList(cells)
            button.setOnClickListener {
                viewAdapter.updateTitle(121, "AAA")
            }
            smartRefreshLayout.finishRefresh(2000)
        }
    }

}