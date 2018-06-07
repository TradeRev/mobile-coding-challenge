package com.example.joao.photoscodechallenge.robots

import android.support.test.espresso.Espresso.onView

import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.example.joao.photoscodechallenge.R

/**
 * Created by Joao Alvares Neto on 07/05/2018.
 */
class RobotLoadingResult {

    fun isSuccess(): Boolean{
        onView(withId(R.id.progress)).check(matches(isDisplayed()))
        return true
    }
}