package com.senijoshua.picsrus.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.senijoshua.picsrus.R
import com.senijoshua.picsrus.presentation.photolist.PhotoListFragment
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_photo_list)
class PhotoListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager
                .beginTransaction()
                .add(R.id.photo_fragment_continer, PhotoListFragment_(), PhotoListFragment::class.java.name)
                .commit()
    }

}
