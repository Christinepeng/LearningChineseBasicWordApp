package com.renameExample.android.learnChinese.viewModel

import androidx.lifecycle.ViewModel
import com.renameExample.android.learnChinese.model.Category

class CategoryViewModel : ViewModel() {

    fun getCategories(): List<Category> {
        return listOf(
            Category("Numbers"),
            Category("Family"),
            Category("Colors"),
            Category("Phrases")
        )
    }
}