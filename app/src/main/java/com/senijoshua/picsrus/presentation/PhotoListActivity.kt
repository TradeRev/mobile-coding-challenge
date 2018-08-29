package com.senijoshua.picsrus.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.senijoshua.picsrus.R
import com.senijoshua.picsrus.data.models.Photos
import com.senijoshua.picsrus.presentation.photolist.PhotoListFragment
import com.senijoshua.picsrus.presentation.photolist.PhotoListFragment_
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_photo_list)
class PhotoListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
                .beginTransaction()
                .add(R.id.photo_fragment_container, PhotoListFragment_(), PhotoListFragment::class.java.name)
                .commit()
    }

    companion object {
        lateinit var currentPhotoList: List<Photos>
        var currentListPosition: Int = 0
        var currentItemPage: Int = 0
    }

}
