package com.example.recyclerviewmultipleviews

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ReportsRvAdapter.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRv()
    }

    private fun initRv() {
        val rvAdapter = ReportsRvAdapter()
        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.adapter = rvAdapter

        rvAdapter.setListener(this@MainActivity)
        rvAdapter.submitList(data())
    }

    private fun data(): MutableList<DataModel> {
        val dataList = mutableListOf<DataModel>()

        dataList.add(
            DataModel(
                "View type one", VIEW_TYPE_HEADER
            )
        )

        dataList.add(
            DataModel(
                "Item1", VIEW_TYPE_ONE
            )
        )

        dataList.add(
            DataModel(
                "Item2", VIEW_TYPE_ONE
            )
        )
        dataList.add(
            DataModel(
                "Item3", VIEW_TYPE_ONE
            )
        )
        dataList.add(
            DataModel(
                "Item4", VIEW_TYPE_ONE
            )
        )

        dataList.add(
            DataModel(
                "View type two", VIEW_TYPE_HEADER
            )
        )

        dataList.add(
            DataModel(
                "Item1", VIEW_TYPE_TWO
            )
        )

        dataList.add(
            DataModel(
                "Item2", VIEW_TYPE_TWO
            )
        )
        dataList.add(
            DataModel(
                "Item3", VIEW_TYPE_TWO
            )
        )

        return dataList
    }

    override fun onHeaderClick(model: DataModel) {
        Toast.makeText(this, "Header clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(model: DataModel) {
        Toast.makeText(this, "Item clicked", Toast.LENGTH_SHORT).show()
    }
}