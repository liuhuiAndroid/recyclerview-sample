package com.sec.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sec.recyclerview.cell.MainTextCell
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewAdapter: MainAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = MainAdapter()
        recyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }

        val cells = mutableListOf<ItemCell>()
        cells.add(MainTextCell("AAA"))
        cells.add(MainTextCell("BBB"))
        cells.add(MainTextCell("CCC"))
        cells.add(MainTextCell("DDD"))
        viewAdapter.submitList(cells)
    }

}