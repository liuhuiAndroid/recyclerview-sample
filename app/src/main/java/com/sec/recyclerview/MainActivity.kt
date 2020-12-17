package com.sec.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sec.recyclerview.cell.MainTextCell
import com.sec.recyclerview.model.topics
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var support: MainSupport
    private lateinit var viewAdapter: MainAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        support = MainSupport().apply {
            onClickCallback { position, type ->
                Toast.makeText(this@MainActivity, "$position,$type", Toast.LENGTH_SHORT).show()
            }
        }
        viewManager = LinearLayoutManager(this)
        viewAdapter = MainAdapter(support)

        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        // 从网络获取数据
        val cells = mutableListOf<ItemCell>()
        topics.forEach {
            cells.add(MainTextCell(it))
        }
        viewAdapter.submitList(cells)

        button.setOnClickListener {
            viewAdapter.updateTitle(121, "AAA")
        }
    }

}