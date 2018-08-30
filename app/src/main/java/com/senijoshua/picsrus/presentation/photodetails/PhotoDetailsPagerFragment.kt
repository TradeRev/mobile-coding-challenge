package com.senijoshua.picsrus.presentation.photodetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.SharedElementCallback
import android.support.v4.view.ViewPager
import android.transition.TransitionInflater
import android.view.View
import com.senijoshua.picsrus.R
import com.senijoshua.picsrus.presentation.SharedStates.currentListPosition
import com.senijoshua.picsrus.presentation.SharedStates.currentPhotoList
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById

@EFragment(R.layout.fragment_photo_details_pager)
class PhotoDetailsPagerFragment : Fragment() {

    @ViewById(R.id.photo_details_pager)
    lateinit var photoPager: ViewPager

    lateinit var photoPagerAdapter: PhotoDetailsPagerAdapter

    var savedState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedState = savedInstanceState
    }

    @AfterViews
    fun onViewCreated() {
        photoPagerAdapter = PhotoDetailsPagerAdapter(this, currentPhotoList)
        photoPager.adapter = photoPagerAdapter
        photoPager.currentItem = currentListPosition
        photoPager.addOnPageChangeListener(pageChangeListener)
        initSharedElementTransition()
        //prevents the fragment's enter transition from overriding the shared element transition
        if (savedState == null) {
            //postpone only on the first instance of creation
            postponeEnterTransition()
        }
    }

    fun initSharedElementTransition() {
        sharedElementEnterTransition = TransitionInflater.from(context)
                .inflateTransition(R.transition.photo_detail_transition)

        //mirror of the callback at the list fragment
        setEnterSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(names: MutableList<String>?, sharedElements: MutableMap<String, View>?) {
                super.onMapSharedElements(names, sharedElements)

                var currentPhotoDetailFragment: Fragment =
                        photoPager.adapter!!.instantiateItem(photoPager, currentListPosition) as Fragment

                sharedElements!![names!![0]] = currentPhotoDetailFragment.view!!.findViewById(R.id.photo_full_screen)
            }
        })
    }

    var pageChangeListener = object : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            currentListPosition = position
        }
    }
}