package com.renameExample.android.learnChinese.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.renameExample.android.learnChinese.R
import com.renameExample.android.learnChinese.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.categoryRecyclerView.adapter = CategoryAdapter(getCategories())
    }

    private fun getCategories(): List<String> {
        return listOf(
            getString(R.string.category_numbers),
            getString(R.string.category_family),
            getString(R.string.category_colors),
            getString(R.string.category_phrases)
        )
    }

    fun onCategoryClicked(view: View) {
        val categoryName = view.tag as String
        val intent = when (categoryName) {
            getString(R.string.category_numbers) -> Intent(this, NumbersActivity::class.java)
            getString(R.string.category_family) -> Intent(this, FamilyActivity::class.java)
            getString(R.string.category_colors) -> Intent(this, ColorsActivity::class.java)
            getString(R.string.category_phrases) -> Intent(this, PhrasesActivity::class.java)
            else -> null
        }
        startActivity(intent)
    }
}