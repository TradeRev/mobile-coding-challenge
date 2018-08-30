package com.senijoshua.picsrus.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.senijoshua.picsrus.R
import com.senijoshua.picsrus.presentation.photolist.PhotoListFragment
import com.senijoshua.picsrus.presentation.photolist.PhotoListFragment_
import com.senijoshua.picsrus.utils.GlobalConstants.KEY_RELOAD_BUFFER
import org.androidannotations.annotations.EActivity


@EActivity(R.layout.activity_photo_list)
class PhotoListActivity : AppCompatActivity() {

    var reloadBuffer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            //state was saved
            reloadBuffer = savedInstanceState.getInt(KEY_RELOAD_BUFFER, 0)
            return
        }
        supportFragmentManager
                .beginTransaction()
                .add(R.id.photo_fragment_container, PhotoListFragment_(), PhotoListFragment::class.java.name)
                .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_RELOAD_BUFFER, reloadBuffer)
    }

}
