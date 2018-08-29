package com.senijoshua.picsrus.presentation.photodetails

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import com.senijoshua.picsrus.R
import com.senijoshua.picsrus.data.models.Photos
import com.senijoshua.picsrus.utils.StringUtility.Companion.getString

class PhotoDetailsPagerAdapter(var fragment: Fragment, var photoList: List<Photos>) : FragmentStatePagerAdapter(fragment.childFragmentManager) {

    override fun getItem(position: Int): Fragment {
        val photo = photoList[position]
        var social: String? = photo.user.instagram_username
        var likes: Int? = photo.likes

        return PhotoDetailsFragment_.builder().photoUrl(photo.urls.regular)
                .userName(getString(R.string.username_prefix) + photo.user.name)
                .socialAccount(if (social == null) getString(R.string.no_social_text) else getString(R.string.social_prefix) + photo.user.instagram_username)
                .numberOfLikes(if (likes == 0 || likes == null) getString(R.string.no_likes) else photo.likes.toString()+ getString(R.string.likes_suffix))
                .transitionName(photo.id).build()
    }

    override fun getCount(): Int {
        return photoList.size
    }
}