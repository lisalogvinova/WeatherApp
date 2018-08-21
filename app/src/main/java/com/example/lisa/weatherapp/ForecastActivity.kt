package com.example.lisa.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.Snackbar.make
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        var retriever = WeatherRetriever()
        val callback = object : Callback<Weather>{
            override fun onResponse(call: Call<Weather>?, response: Response<Weather>?) {
                println("it's working")
                title = response?.body()?.query?.results?.channel?.title

                if(response?.body()?.query?.count == 0){
                    val theView = this@ForecastActivity.findViewById<View>(android.R.id.content)
                    Snackbar.make(theView, "City not found :( Give it another try!", Snackbar.LENGTH_LONG).show()
                } else {
                    var forecasts = response?.body()?.query?.results?.channel?.item?.forecast
                    var forecastStrings = mutableListOf<String>()
                    if (forecasts != null) {
                        for (forecast in forecasts) {
                            val newString = "${forecast.date} - High:${forecast.high}, Low:${forecast.low}, ${forecast.text}"
                            forecastStrings.add(newString)
                        }
                    }
                    var listView = findViewById<ListView>(R.id.forecastListView)
                    var adapter = ArrayAdapter(this@ForecastActivity, android.R.layout.simple_list_item_1, forecastStrings)
                    listView.adapter = adapter
                }
            }
            override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                println("no response :(")
            }
        }
        val searchTerm = intent.extras.getString("searchTerm")
        retriever.getForecast(callback, searchTerm)
    }
}
