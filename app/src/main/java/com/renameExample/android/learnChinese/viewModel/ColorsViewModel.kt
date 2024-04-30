package com.renameExample.android.learnChinese.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.renameExample.android.learnChinese.R
import com.renameExample.android.learnChinese.model.Word

class ColorsViewModel : ViewModel() {
    private val _words = MutableLiveData<List<Word>>()
    val words: LiveData<List<Word>>
        get() = _words

    init {
        _words.value = listOf(
            Word("red", "紅色", R.drawable.color_red, R.raw.color_red),
            Word("mustard yellow", "芥末黃", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow),
            Word("dusty yellow", "土黃色", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow),
            Word("green", "綠色", R.drawable.color_green, R.raw.color_green),
            Word("brown", "咖啡色", R.drawable.color_brown, R.raw.color_brown),
            Word("gray", "灰色", R.drawable.color_gray, R.raw.color_gray),
            Word("black", "黑色", R.drawable.color_black, R.raw.color_black),
            Word("white", "白色", R.drawable.color_white, R.raw.color_white)
        )
    }
}