package com.renameExample.android.learnChinese.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.renameExample.android.learnChinese.databinding.ActivityMainBinding
import com.renameExample.android.learnChinese.model.Category
import com.renameExample.android.learnChinese.viewModel.CategoryViewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(this)
        categoryViewModel = CategoryViewModel()
        categoryAdapter = CategoryAdapter { category -> onCategoryClicked(category) }
        binding.categoryRecyclerView.adapter = categoryAdapter

        categoryAdapter.submitList(categoryViewModel.getCategories())
    }

    private fun onCategoryClicked(category: Category) {
        val intent = when (category.name) {
            "Numbers" -> Intent(this, NumbersActivity::class.java)
            "Family" -> Intent(this, FamilyActivity::class.java)
            "Colors" -> Intent(this, ColorsActivity::class.java)
            "Phrases" -> Intent(this, PhrasesActivity::class.java)
            else -> null
        }
        startActivity(intent)
    }
}