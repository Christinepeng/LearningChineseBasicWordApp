package com.renameExample.android.learnChinese

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class WordAdapter(context: Context?, words: ArrayList<Word>?, private val mColorResourceId: Int) :
    ArrayAdapter<Word>(
        context!!, 0, words!!
    ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(
                R.layout.list_item, parent, false
            )
        }
        val currentWord = getItem(position)
        val miwokTextView = listItemView!!.findViewById<View>(R.id.miwok_text_view) as TextView
        miwokTextView.text = currentWord!!.miwokTranslation
        val defaultTextView = listItemView.findViewById<View>(R.id.default_text_view) as TextView
        defaultTextView.text = currentWord.defaultTranslation
        val imageView = listItemView.findViewById<View>(R.id.image) as ImageView
        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getmImageResourceId())
            imageView.setVisibility(View.VISIBLE)
        } else {
            imageView.setVisibility(View.GONE)
        }
        val textContainer = listItemView.findViewById<View>(R.id.text_container)
        val color = ContextCompat.getColor(context, mColorResourceId)
        textContainer.setBackgroundColor(color)
        return listItemView
    }
}
