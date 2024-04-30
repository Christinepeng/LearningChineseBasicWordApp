package com.renameExample.android.learnChinese.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.renameExample.android.learnChinese.R
import com.renameExample.android.learnChinese.model.Word

class PhrasesViewModel : ViewModel() {
    private val _words = MutableLiveData<List<Word>>()
    val words: LiveData<List<Word>>
        get() = _words

    init {
        _words.value = listOf(
            Word("Where are you going?", "你要去哪裡？", R.raw.phrase_where_are_you_going),
            Word("What is your name?", "你叫什麼名字？", R.raw.phrase_what_is_your_name),
            Word("My name is...", "我的名字是...", R.raw.phrase_my_name_is),
            Word("How are you feeling?", "你感覺怎麼樣？", R.raw.phrase_how_are_you_feeling),
            Word("I’m feeling good.", "我感覺很好。", R.raw.phrase_im_feeling_good),
            Word("Are you coming?", "你來嗎？", R.raw.phrase_are_you_coming),
            Word("Yes, I’m coming.", "是的，我來了。", R.raw.phrase_yes_im_coming),
            Word("I’m coming.", "我來了。", R.raw.phrase_im_coming),
            Word("Let’s go.", "走吧。", R.raw.phrase_lets_go),
            Word("Come here.", "來這裡。", R.raw.phrase_come_here)
        )
    }
}