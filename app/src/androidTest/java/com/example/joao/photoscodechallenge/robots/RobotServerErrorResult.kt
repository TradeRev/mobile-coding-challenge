package com.example.joao.photoscodechallenge.robots

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.example.joao.photoscodechallenge.R
import org.hamcrest.Matchers.not

/**
 * Created by Joao Alvares Neto on 07/05/2018.
 */
class RobotServerErrorResult {

    fun errorHasBeenShown(): Boolean{
        onView(withText(R.string.server_error_message)).check(matches(isDisplayed()))

        onView(withId(R.id.errorButton)).check(matches(not((isDisplayed()))))
        onView(withText(R.string.try_again)).check(matches(not((isDisplayed()))))

        return true
    }
}