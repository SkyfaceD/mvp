package org.skyfaced.mvp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.skyfaced.mvp.databinding.ItemBinding

class SimpleAdapter(
    private val onClick: (text: String) -> Unit,
) : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {
    var currentList = emptyList<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(items: List<String>) {
        currentList = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var item: String = ""

        init {
            this.binding.root.setOnClickListener { onClick(item) }
        }

        fun bind(text: String) {
            item = text
            binding.root.text = text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(currentList[position])

    override fun getItemCount() = currentList.size
}