package com.example.recyclerviewmultipleviews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewmultipleviews.databinding.ItemRvHeaderBinding
import com.example.recyclerviewmultipleviews.databinding.ItemRvViewTypeOneBinding
import com.example.recyclerviewmultipleviews.databinding.ItemRvViewTypeTwoBinding


class ReportsRvAdapter : ListAdapter<DataModel, RecyclerView.ViewHolder>(DiffCallback) {

    private lateinit var listener: OnItemClickListener


    override fun submitList(list: MutableList<DataModel>?) {
        if (list != null) {
            listMessages = list
        }
        super.submitList(list)
    }

    override fun getItemViewType(position: Int): Int {
        return when (listMessages[position].viewType) {
            VIEW_TYPE_HEADER -> VIEW_TYPE_HEADER
            VIEW_TYPE_ONE -> VIEW_TYPE_ONE
            else -> VIEW_TYPE_TWO
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HEADER -> {
                HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_header, parent, false))
            }

            VIEW_TYPE_ONE -> {
                TypeOneViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_view_type_one, parent, false))
            }

            else -> {
                TypeTwoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_view_type_two, parent, false))
            }
        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BindView).bind(getItem(position))
    }

    interface BindView {
        fun bind(model: DataModel)
    }


    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), BindView {

        private val binding = ItemRvHeaderBinding.bind(itemView)

        override fun bind(model: DataModel) {
            binding.apply {
                tv.text = model.text
            }
        }

        init {
            binding.root.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    getItem(bindingAdapterPosition).let {
                        listener.onHeaderClick(it)
                    }
                }
            }
        }
    }

    inner class TypeOneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), BindView {

        private val binding = ItemRvViewTypeOneBinding.bind(itemView)

        override fun bind(model: DataModel) {
            binding.apply {
                tv.text = model.text
            }
        }

        init {
            binding.root.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    getItem(bindingAdapterPosition).let {
                        listener.onItemClick(it)
                    }
                }
            }
        }
    }

    inner class TypeTwoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), BindView {

        private val binding = ItemRvViewTypeTwoBinding.bind(itemView)

        override fun bind(model: DataModel) {
            binding.apply {
                tv.text = model.text
            }
        }

        init {
            binding.root.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    getItem(bindingAdapterPosition).let {
                        listener.onItemClick(it)
                    }
                }
            }
        }
    }

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onHeaderClick(model: DataModel)
        fun onItemClick(model: DataModel)
    }

    // DiffCallback
    companion object {
        private var listMessages = mutableListOf<DataModel>()

        private val DiffCallback = object : DiffUtil.ItemCallback<DataModel>() {
            override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}
