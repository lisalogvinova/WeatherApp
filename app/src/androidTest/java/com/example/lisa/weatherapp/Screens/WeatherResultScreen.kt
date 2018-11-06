package com.example.lisa.weatherapp.Screens

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.uiautomator.UiCollection
import android.support.test.uiautomator.UiObject
import android.support.test.uiautomator.UiSelector
import com.example.lisa.weatherapp.R
import org.hamcrest.Matchers
import org.junit.Assert

class WeatherResultScreen : BaseScreen(){
    private val weatherResultList = UiCollection(UiSelector().resourceId("com.example.lisa.weatherapp:id/forecastListView"))

    init {
        Assert.assertTrue("Expected elements are not displayed", weatherResultList.waitForExists(5000))
    }

    val actionBar = Espresso.onView(ViewMatchers.withId(R.id.action_bar))
    val snackbar = Espresso.onView(ViewMatchers.withId(R.id.snackbar_text))

    fun checkSnackbar(){
        snackbar.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun checkDefaultCity(string: String){
        actionBar.check(ViewAssertions.matches(ViewMatchers.withText(string)))
    }
}
