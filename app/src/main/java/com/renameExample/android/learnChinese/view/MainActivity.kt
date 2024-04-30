package com.renameExample.android.learnChinese.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.renameExample.android.learnChinese.R

class MainActivity : AppCompatActivity() {
    private val numbersLiveData = MutableLiveData<Boolean>()
    private val familyLiveData = MutableLiveData<Boolean>()
    private val colorsLiveData = MutableLiveData<Boolean>()
    private val phrasesLiveData = MutableLiveData<Boolean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numbers = findViewById<TextView>(R.id.numbers)
        val family = findViewById<TextView>(R.id.family)
        val colors = findViewById<TextView>(R.id.colors)
        val phrases = findViewById<TextView>(R.id.phrases)

        numbers.setOnClickListener {
            numbersLiveData.value = true
        }

        family.setOnClickListener {
            familyLiveData.value = true
        }

        colors.setOnClickListener {
            colorsLiveData.value = true
        }

        phrases.setOnClickListener {
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