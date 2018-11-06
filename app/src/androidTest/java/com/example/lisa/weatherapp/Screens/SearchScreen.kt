package com.example.lisa.weatherapp.Screens

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import com.example.lisa.weatherapp.R
import org.junit.Assert

class SearchScreen : BaseScreen(){
    val searchScreen = uiDevice.findObject(UiSelector().resourceId("android:id/content"))
    val searchField = Espresso.onView(ViewMatchers.withId(R.id.searchCityText))
    val searchButton = Espresso.onView(ViewMatchers.withId(R.id.getForecastButton))

    init {
        Assert.assertTrue("Search screen is not displayed", searchScreen.waitForExists(5000))
    }

    fun clearSearchField(){
        searchField.perform(clearText())
    }

    fun clickSearchButton() : SearchResultScreen {
        searchButton.perform(click())
        return SearchResultScreen()
    }

    fun setSearchText(string: String){
        searchField.perform(typeText(string))
    }
}