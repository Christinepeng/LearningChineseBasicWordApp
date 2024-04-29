package com.renameExample.android.learnChinese

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main)

        // Find the View that shows the numbers category
        val numbers = findViewById<View>(R.id.numbers) as TextView

        // Set a click listener on that View
        numbers.setOnClickListener { // Create a new intent to open the {@link NumbersActivity}
            val numbersIntent = Intent(this@MainActivity, NumbersActivity::class.java)

            // Start the new activity
            startActivity(numbersIntent)
        }

        // Find the View that shows the family category
        val family = findViewById<View>(R.id.family) as TextView

        // Set a click listener on that View
        family.setOnClickListener { // Create a new intent to open the {@link FamilyActivity}
            val familyIntent = Intent(this@MainActivity, FamilyActivity::class.java)

            // Start the new activity
            startActivity(familyIntent)
        }

        // Find the View that shows the colors category
        val colors = findViewById<View>(R.id.colors) as TextView

        // Set a click listener on that View
        colors.setOnClickListener { // Create a new intent to open the {@link ColorsActivity}
            val colorsIntent = Intent(this@MainActivity, ColorsActivity::class.java)

            // Start the new activity
            startActivity(colorsIntent)
        }

        // Find the View that shows the phrases category
        val phrases = findViewById<View>(R.id.phrases) as TextView

        // Set a click listener on that View
        phrases.setOnClickListener { // Create a new intent to open the {@link PhrasesActivity}
            val phrasesIntent = Intent(this@MainActivity, PhrasesActivity::class.java)

            // Start the new activity
            startActivity(phrasesIntent)
        }
    }
}