package com.senijoshua.picsrus.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.senijoshua.picsrus.R
import com.senijoshua.picsrus.data.models.Photos
import com.senijoshua.picsrus.presentation.photolist.PhotoListFragment
import com.senijoshua.picsrus.presentation.photolist.PhotoListFragment_
import com.senijoshua.picsrus.utils.GlobalConstants.KEY_CURRENT_PHOTO_LIST
import com.senijoshua.picsrus.utils.GlobalConstants.KEY_CURRENT_POSITION
import com.senijoshua.picsrus.utils.GlobalConstants.KEY_SHOULD_LOAD
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.InstanceState

@EActivity(R.layout.activity_photo_list)
class PhotoListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            //state was saved
            currentListPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION, 0)
            shouldLoad = savedInstanceState.getBoolean(KEY_SHOULD_LOAD, false)
            currentPhotoList = savedInstanceState.getParcelableArrayList(KEY_CURRENT_PHOTO_LIST)
            return
        }
        supportFragmentManager
                .beginTransaction()
                .add(R.id.photo_fragment_container, PhotoListFragment_(), PhotoListFragment::class.java.name)
                .commit()
    }

    companion object {
        lateinit var currentPhotoList: List<Photos>
        var currentListPosition: Int = 0
        var shouldLoad: Boolean = true //Load only when the activity is first created
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_CURRENT_POSITION, currentListPosition)
        outState.putBoolean(KEY_SHOULD_LOAD, shouldLoad)
        outState.putParcelableArrayList(KEY_CURRENT_PHOTO_LIST,  ArrayList(currentPhotoList))
    }
}
