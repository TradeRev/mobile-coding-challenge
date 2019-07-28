package com.traderev.codingchallenge.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso
import com.traderev.codingchallenge.model.Photos


class PhotoPagerAdapter(
    clickedPosition: Int,
    photoList: ArrayList<Photos>?
) : PagerAdapter() {

    private var photos: List<Photos>? = photoList
    private var currentPosition = clickedPosition
    private lateinit var photoView: PhotoView

    override fun getCount(): Int {
        val size = photos?.size
        return if (size != null) {
            val nonNullableInt: Int = size
            nonNullableInt
        } else 0
    }

    override fun instantiateItem(container: ViewGroup, position: Int): View {
        photoView = PhotoView(container.context)
        Picasso.get().load(photos?.get(position)?.urls?.small).into(photoView)
        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        return photoView
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, currentPosition, photoView)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }


}