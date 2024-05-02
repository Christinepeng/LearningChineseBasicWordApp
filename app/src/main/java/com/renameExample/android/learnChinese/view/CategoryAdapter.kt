package com.renameExample.android.learnChinese.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.renameExample.android.learnChinese.R
import com.renameExample.android.learnChinese.model.Category

class CategoryAdapter(private val onClickListener: (Category) -> Unit) :
    ListAdapter<Category, CategoryAdapter.ViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
        holder.itemView.setOnClickListener { onClickListener(category) }
        when (position) {
            0 -> holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.category_numbers))
            1 -> holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.category_family))
            2 -> holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.category_colors))
            3 -> holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.category_phrases))
            else -> holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.category_numbers))
        }
        holder.itemView.tag = category
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryTextView: TextView = itemView.findViewById(R.id.categoryTextView)

        fun bind(category: Category) {
            categoryTextView.text = category.name
        }
    }

    private class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
}