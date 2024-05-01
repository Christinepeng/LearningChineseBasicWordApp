package com.renameExample.android.learnChinese.view

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.renameExample.android.learnChinese.R

class CategoryAdapter(private val categories: List<String>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTextView: TextView = itemView.findViewById(R.id.categoryTextView)

        init {
            itemView.setOnClickListener { view ->
                (view.context as MainActivity).onCategoryClicked(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryTextView.text = category
        when (position) {
            0 -> holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.category_numbers))
            1 -> holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.category_family))
            2 -> holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.category_colors))
            3 -> holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.category_phrases))
            else -> holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.category_numbers))
        }
        holder.itemView.tag = category
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}