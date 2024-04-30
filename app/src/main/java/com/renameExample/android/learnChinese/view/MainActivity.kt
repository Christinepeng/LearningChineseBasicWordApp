package com.renameExample.android.learnChinese.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.renameExample.android.learnChinese.R
import com.renameExample.android.learnChinese.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val numbersLiveData = MutableLiveData<Boolean>()
    private val familyLiveData = MutableLiveData<Boolean>()
    private val colorsLiveData = MutableLiveData<Boolean>()
    private val phrasesLiveData = MutableLiveData<Boolean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.numbers.setOnClickListener {
            numbersLiveData.value = true
        }

        binding.family.setOnClickListener {
            familyLiveData.value = true
        }

        binding.colors.setOnClickListener {
            colorsLiveData.value = true
        }

        binding.phrases.setOnClickListener {
            phrasesLiveData.value = true
        }

        observeLiveData()
    }

    private fun observeLiveData() {
        numbersLiveData.observe(this, Observer {
            val numbersIntent = Intent(this@MainActivity, NumbersActivity::class.java)
            startActivity(numbersIntent)
        })

        familyLiveData.observe(this, Observer {
            val familyIntent = Intent(this@MainActivity, FamilyActivity::class.java)
            startActivity(familyIntent)
        })

        colorsLiveData.observe(this, Observer {
            val colorsIntent = Intent(this@MainActivity, ColorsActivity::class.java)
            startActivity(colorsIntent)
        })

        phrasesLiveData.observe(this, Observer {
            val phrasesIntent = Intent(this@MainActivity, PhrasesActivity::class.java)
            startActivity(phrasesIntent)
        })
    }
}