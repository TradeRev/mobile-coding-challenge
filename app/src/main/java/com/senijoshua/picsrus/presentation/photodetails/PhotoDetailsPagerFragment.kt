package com.senijoshua.picsrus.presentation.photodetails

import android.support.v4.app.Fragment
import android.support.v4.app.SharedElementCallback
import android.support.v4.view.ViewPager
import android.transition.TransitionInflater
import android.view.View
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
        photoPager.addOnPageChangeListener(pageChangeListener)
        initSharedElementTransition()
        //prevents the fragment's enter transition from overriding the shared element transition
        postponeEnterTransition()
    }

    fun initSharedElementTransition(){
        // Defines how the shared photo view transitions
        //when it animates to a new position. See source:
        //https://android-developers.googleblog.com/2018/02/continuous-shared-element-transitions.html
        sharedElementEnterTransition = TransitionInflater.from(context)
                .inflateTransition(R.transition.photo_detail_transition)

        setEnterSharedElementCallback(object : SharedElementCallback(){
            override fun onMapSharedElements(names: MutableList<String>?, sharedElements: MutableMap<String, View>?) {
                super.onMapSharedElements(names, sharedElements)

                var currentPhotoDetailFragment: Fragment =
                        photoPager.adapter!!.instantiateItem(photoPager, PhotoListActivity.currentListPosition) as Fragment

                sharedElements!![names!![0]] = currentPhotoDetailFragment.view!!.findViewById(R.id.photo_full_screen)
            }
        })
    }

    var pageChangeListener = object : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            PhotoListActivity.currentListPosition = position
        }
    }
}