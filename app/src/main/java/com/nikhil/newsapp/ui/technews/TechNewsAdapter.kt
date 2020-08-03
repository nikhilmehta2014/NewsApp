package com.nikhil.newsapp.ui.technews

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nikhil.newsapp.R
import com.nikhil.newsapp.databinding.ItemTechNewsBinding
import com.nikhil.newsapp.models.Article

class TechNewsAdapter : RecyclerView.Adapter<TechNewsAdapter.TechNewsViewHolder>() {

    private var context: Context? = null

    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechNewsViewHolder {
        context = parent.context
        return TechNewsViewHolder(
            ItemTechNewsBinding.inflate(
                LayoutInflater.from(context)
            )
        )
    }

    override fun getItemCount(): Int {
        println("differ.currentList.size = ${differ.currentList.size}")
        return differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    override fun onBindViewHolder(holder: TechNewsViewHolder, position: Int) {
        //Fade transition for News Item
        holder.itemView.animation =
            AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation)
        val article = differ.currentList[position]
        holder.itemView.apply {
            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
        holder.bind(article)
    }

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

    inner class TechNewsViewHolder(private val binding: ItemTechNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            binding.article = item
            binding.executePendingBindings()
        }
    }
}