package com.example.joao.photoscodechallenge.robots

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.example.joao.photoscodechallenge.R

/**
 * Created by Joao Alvares Neto on 07/05/2018.
 */
class RobotBadRequestResult {

    fun errorHasBeenShown(): Boolean{
        onView(withText(R.string.bad_request_error_message)).check(matches(isDisplayed()))

        onView(withId(R.id.errorButton)).check(matches((isDisplayed())))
        onView(withText(R.string.try_again)).check(matches((isDisplayed())))

        return true
    }
}