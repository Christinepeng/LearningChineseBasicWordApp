package com.renameExample.android.learnChinese.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.renameExample.android.learnChinese.R
import com.renameExample.android.learnChinese.model.Word

class FamilyViewModel : ViewModel() {
    private val _words = MutableLiveData<List<Word>>()
    val words: LiveData<List<Word>>
        get() = _words

    init {
        _words.value = listOf(
            Word("father", "爸爸", R.drawable.family_father, R.raw.family_father),
            Word("mother", "媽媽", R.drawable.family_mother, R.raw.family_mother),
            Word("son", "兒子", R.drawable.family_son, R.raw.family_son),
            Word("daughter", "女兒", R.drawable.family_daughter, R.raw.family_daughter),
            Word("older brother", "哥哥", R.drawable.family_older_brother, R.raw.family_older_brother),
            Word("younger brother", "弟弟", R.drawable.family_younger_brother, R.raw.family_younger_brother),
            Word("older sister", "姊姊", R.drawable.family_older_sister, R.raw.family_older_sister),
            Word("younger sister", "妹妹", R.drawable.family_younger_sister, R.raw.family_younger_sister),
            Word("grandmother", "奶奶", R.drawable.family_grandmother, R.raw.family_grandmother),
            Word("grandfather", "爺爺", R.drawable.family_grandfather, R.raw.family_grandfather)
        )
    }
}