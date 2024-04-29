package com.renameExample.android.learnChinese

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val numbers = findViewById<View>(R.id.numbers) as TextView

        numbers.setOnClickListener {
            val numbersIntent = Intent(this@MainActivity, NumbersActivity::class.java)

            startActivity(numbersIntent)
        }

        val family = findViewById<View>(R.id.family) as TextView

        family.setOnClickListener {
            val familyIntent = Intent(this@MainActivity, FamilyActivity::class.java)

            startActivity(familyIntent)
        }

        val colors = findViewById<View>(R.id.colors) as TextView

        colors.setOnClickListener {
            val colorsIntent = Intent(this@MainActivity, ColorsActivity::class.java)

            startActivity(colorsIntent)
        }

        val phrases = findViewById<View>(R.id.phrases) as TextView

        phrases.setOnClickListener {
            val phrasesIntent = Intent(this@MainActivity, PhrasesActivity::class.java)

            startActivity(phrasesIntent)
        }
    }
}