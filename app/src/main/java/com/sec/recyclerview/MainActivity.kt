package com.sec.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
            onRetry {
                initData()
                refreshData()
            }
            onClickCallback { position, type ->
                Toast.makeText(this@MainActivity, "$position,$type", Toast.LENGTH_SHORT).show()
            }
        }

        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        viewAdapter.submitInitCell()
        initData()
        refreshData()
    }

    private fun refreshData() {
        GlobalScope.launch(Dispatchers.IO) {
            delay(3000)
            viewAdapter.submitList(5, MainTextCell(Topic("E", 5, "5"))) {
                // 滚动到最新一条数据
                // viewManager.scrollToPosition(viewAdapter.itemCount - 1)
            }
            delay(1000)
            viewAdapter.submitList(4, MainTextCell(Topic("D", 4, "4")))
            delay(1000)
            viewAdapter.submitList(3, MainTextCell(Topic("C", 3, "3")))
            delay(1000)
            viewAdapter.submitList(2, MainTextCell(Topic("B", 2, "2")))
            delay(1000)
            viewAdapter.submitList(1, MainTextCell(Topic("A", 1, "1")))
        }
    }

    private fun initData() {
        GlobalScope.launch(Dispatchers.IO) {
            // 模拟从网络获取数据
            delay(2000)
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