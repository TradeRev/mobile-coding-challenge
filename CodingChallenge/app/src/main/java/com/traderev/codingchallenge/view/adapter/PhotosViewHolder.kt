package com.traderev.codingchallenge.view.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.practice.gallery.R
import com.practice.gallery.model.Photos
import kotlinx.android.synthetic.main.item_photo_list.view.*
import com.squareup.picasso.Picasso.get as picassoGet

class PhotosViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val set = ConstraintSet()

    fun bind(photos: Photos?) {
        if (photos != null) {
            picassoGet().run { load(photos.urls.small).into(itemView.iv_source) }
            with(set) {

                val posterRatio = String.format("%d:%d", photos.width, photos.height)
                clone(itemView.parent_constraint)
                setDimensionRatio(itemView.iv_source.id, posterRatio)
                applyTo(itemView.parent_constraint)

            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): PhotosViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_photo_list, parent, false)

            return PhotosViewHolder(view)
        }
    }
}