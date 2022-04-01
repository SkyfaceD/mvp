package org.skyfaced.mvp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.skyfaced.mvp.databinding.ItemWaifuBinding
import org.skyfaced.mvp.model.ImageDto

class WaifuAdapter : RecyclerView.Adapter<WaifuAdapter.ViewHolder>() {
    var currentList = emptyList<ImageDto>()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(items: List<ImageDto>) {
        currentList = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemWaifuBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var item: ImageDto

        fun bind(item: ImageDto) {
            this.item = item
            binding.root.load(item.url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemWaifuBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(currentList[position])

    override fun getItemCount() = currentList.size
}