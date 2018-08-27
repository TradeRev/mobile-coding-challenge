package com.senijoshua.picsrus.presentation.photodetails

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import com.senijoshua.picsrus.data.models.Photos

class PhotoDetailsPagerAdapter(var fragment: Fragment, var photoList: List<Photos>) : FragmentStatePagerAdapter(fragment.childFragmentManager) {

    override fun getItem(position: Int): Fragment {
        val photo = photoList[position]

        return PhotoDetailsFragment_.builder().photoUrl(photo.urls.regular)
                .userName(photo.user.name).socialAccount(photo.user.instagram_username).numberOfLikes(photo.likes.toString()).build()
    }

    override fun getCount(): Int {
        return photoList.size
    }
}