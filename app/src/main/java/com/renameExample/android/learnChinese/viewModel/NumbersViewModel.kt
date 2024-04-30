package com.renameExample.android.learnChinese.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.renameExample.android.learnChinese.R
import com.renameExample.android.learnChinese.model.Word

class NumbersViewModel: ViewModel() {
    private val _words = MutableLiveData<List<Word>>()
    val words: LiveData<List<Word>>
        get() = _words

    init {
        _words.value = listOf(
            Word("one", "一", R.drawable.number_one, R.raw.number_one),
            Word("two", "二", R.drawable.number_two, R.raw.number_two),
            Word("three", "三", R.drawable.number_three, R.raw.number_three),
            Word("four", "四", R.drawable.number_four, R.raw.number_four),
            Word("five", "五", R.drawable.number_five, R.raw.number_five),
            Word("six", "六", R.drawable.number_six, R.raw.number_six),
            Word("seven", "七", R.drawable.number_seven, R.raw.number_seven),
            Word("eight", "八", R.drawable.number_eight, R.raw.number_eight),
            Word("nine", "九", R.drawable.number_nine, R.raw.number_nine),
            Word("ten", "十", R.drawable.number_ten, R.raw.number_ten)
        )
    }
}