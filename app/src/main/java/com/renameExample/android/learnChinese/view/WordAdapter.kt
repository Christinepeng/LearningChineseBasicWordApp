package com.renameExample.android.learnChinese.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.renameExample.android.learnChinese.R
import com.renameExample.android.learnChinese.model.Word

class WordAdapter(
    private val context: Context?,
    private val words: ArrayList<Word>?,
    private val listener: WordItemClickListener?,
    private val mColorResourceId: Int
) : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val miwokTextView: TextView = itemView.findViewById(R.id.miwok_text_view)
        val defaultTextView: TextView = itemView.findViewById(R.id.default_text_view)
        val imageView: ImageView = itemView.findViewById(R.id.image)
        val textContainer: View = itemView.findViewById(R.id.text_container)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val currentWord = words?.get(position)
        holder.miwokTextView.text = currentWord?.miwokTranslation
        holder.defaultTextView.text = currentWord?.defaultTranslation
        if (currentWord?.hasImage() == true) {
            holder.imageView.setImageResource(currentWord.getmImageResourceId())
            holder.imageView.visibility = View.VISIBLE
        } else {
            holder.imageView.visibility = View.GONE
        }
        val color = ContextCompat.getColor(context!!, mColorResourceId)
        holder.textContainer.setBackgroundColor(color)

        holder.itemView.setOnClickListener {
            listener?.onWordClicked(currentWord!!)
        }
    }

    override fun getItemCount(): Int {
        return words?.size ?: 0
    }

    fun addAll(newWords: List<Word>) {
        words!!.addAll(newWords)
        notifyDataSetChanged()
    }
}
interface WordItemClickListener {
    fun onWordClicked(word: Word)
}

//class WordAdapter(context: Context?,
//                  words: ArrayList<Word>?,
//                  private val listener: WordItemClickListener?,
//                  private val mColorResourceId: Int
//) : ArrayAdapter<Word>(        context!!, 0, words!!    ) {
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        var listItemView = convertView
//        if (listItemView == null) {
//            listItemView = LayoutInflater.from(context).inflate(
//                R.layout.list_item, parent, false
//            )
//        }
//        val currentWord = getItem(position)
//        val miwokTextView = listItemView!!.findViewById<View>(R.id.miwok_text_view) as TextView
//        miwokTextView.text = currentWord!!.miwokTranslation
//        val defaultTextView = listItemView.findViewById<View>(R.id.default_text_view) as TextView
//        defaultTextView.text = currentWord.defaultTranslation
//        val imageView = listItemView.findViewById<View>(R.id.image) as ImageView
//        if (currentWord.hasImage()) {
//            imageView.setImageResource(currentWord.getmImageResourceId())
//            imageView.setVisibility(View.VISIBLE)
//        } else {
//            imageView.setVisibility(View.GONE)
//        }
//        val textContainer = listItemView.findViewById<View>(R.id.text_container)
//        val color = ContextCompat.getColor(context, mColorResourceId)
//        textContainer.setBackgroundColor(color)
//
//        listItemView.setOnClickListener {
//            listener?.onWordClicked(currentWord)
//        }
//
//        return listItemView
//    }
//}
//
//interface WordItemClickListener {
//    fun onWordClicked(word: Word)
//}