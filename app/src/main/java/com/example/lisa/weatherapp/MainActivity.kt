package com.example.lisa.weatherapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var getForecastButton = findViewById<Button>(R.id.getForecastButton)
        getForecastButton.setOnClickListener{
            var moveIntent = Intent(this, ForecastActivity::class.java)
            val searchCityText = findViewById<EditText>(R.id.searchCityText)
            moveIntent.putExtra("searchTerm", searchCityText.text.toString())
            startActivity(moveIntent)
        }
    }
}
