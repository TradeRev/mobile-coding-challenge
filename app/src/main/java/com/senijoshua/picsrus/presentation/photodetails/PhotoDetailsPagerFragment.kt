package com.senijoshua.picsrus.presentation.photodetails

import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.senijoshua.picsrus.R
import com.senijoshua.picsrus.presentation.PhotoListActivity
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById

@EFragment(R.layout.fragment_photo_details_pager)
class PhotoDetailsPagerFragment : Fragment() {

    @ViewById(R.id.photo_details_pager)
    lateinit var photoPager: ViewPager

    lateinit var photoPagerAdapter: PhotoDetailsPagerAdapter

    @AfterViews
    fun onViewCreated(){
        photoPagerAdapter = PhotoDetailsPagerAdapter(this, PhotoListActivity.currentPhotoList)
        photoPager.adapter = photoPagerAdapter
        photoPager.currentItem = PhotoListActivity.currentListPosition
    }
}