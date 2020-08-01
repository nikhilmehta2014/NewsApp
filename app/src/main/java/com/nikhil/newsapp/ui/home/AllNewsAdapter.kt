package com.nikhil.newsapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikhil.newsapp.databinding.ItemAllNewsBinding
import com.nikhil.newsapp.source.remote.response.GetNewsResponseEntity

class AllNewsAdapter(
    private val items: List<GetNewsResponseEntity.Article>?,
    private val clickListener: (String) -> Unit
) :
    RecyclerView.Adapter<AllNewsAdapter.AllNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllNewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAllNewsBinding.inflate(inflater)
        return AllNewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    override fun onBindViewHolder(holder: AllNewsViewHolder, position: Int) {
        items?.get(position)?.let { holder.bind(it, clickListener) }
    }

    inner class AllNewsViewHolder(private val binding: ItemAllNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GetNewsResponseEntity.Article, clickListener: (String) -> Unit) {
            binding.allNewsItem = item
            itemView.setOnClickListener {
                /**
                 * "allNewsItem" is introduced to avoid "Smart cast to 'GetNewsResponseEntity.Article' is impossible" error
                 */
                val allNewsItem = binding.allNewsItem
                allNewsItem?.url?.let { url ->
                    clickListener(url)
                }
            }
            binding.executePendingBindings()
        }
    }
}