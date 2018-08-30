package com.senijoshua.picsrus

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.senijoshua.picsrus.presentation.PhotoListActivity_
import com.senijoshua.picsrus.presentation.photolist.PhotoListFragment_
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PhotoListActivityTest {

    @get:Rule
    var photoListActivity: ActivityTestRule<PhotoListActivity_> = ActivityTestRule(PhotoListActivity_::class.java, false, false)

    var photoListActivityIntent = Intent()

    @Before
    fun setUp(){
        photoListActivity.launchActivity(photoListActivityIntent)
    }

    /**
     * Verify that the photoListFragment is properly attached on activity start
     * and that photos from the server is loaded into the list
     */
    @Test
    fun photoListFragment_isAttachedAndDisplaysData(){
        //Arrange
        val photoListFragment = PhotoListFragment_()
        photoListActivity.activity.supportFragmentManager
                .beginTransaction()
                .add(R.id.photo_fragment_container, photoListFragment)
                .commit()
        onView(withId(R.id.photos_list)).check(matches(isDisplayed()))
        //Act

        //Assert

    }



    @Test
    fun photoDetailsFragment_isStartedAndDisplaysPhoto(){
        //then page on the fragment,
        //then press back and then verify
        //the pressed photo is still visible in the list
    }
}