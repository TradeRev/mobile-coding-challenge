package com.senijoshua.picsrus.presentation.photodetails

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import com.senijoshua.picsrus.data.models.Photos
import com.senijoshua.picsrus.presentation.SharedStates.DETAIL_PHOTO


class PhotoDetailsPagerAdapter(var fragment: Fragment, var photoList: List<Photos>) : FragmentStatePagerAdapter(fragment.childFragmentManager) {

    override fun getItem(position: Int): Fragment {
        val photo = photoList[position]

        val detailFragment: PhotoDetailsFragment = PhotoDetailsFragment_()
        val args = Bundle()
        args.putParcelable(DETAIL_PHOTO, photo)
        detailFragment.arguments = args
        return detailFragment
    }

    override fun getCount(): Int {
        return photoList.size
    }
}