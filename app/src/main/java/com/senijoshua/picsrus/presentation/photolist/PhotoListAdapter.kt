package com.senijoshua.picsrus.presentation.photolist

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.senijoshua.picsrus.R
import com.senijoshua.picsrus.data.models.Photos
import com.senijoshua.picsrus.utils.GlideApp

class PhotoListAdapter(var context: Context, var clickListener: PhotoClickListener) : RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder>() {

    var photosList: MutableList<Photos> = ArrayList()

    fun setList(photoList: List<Photos>) {
        photosList.addAll(photoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.listitem_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (photosList.isNotEmpty()) {
            photosList.size
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo: Photos = photosList[position]
        val photoView = holder.photoView
        GlideApp.with(context)
                .load(photo.urls.regular)
                .listener(requestListener(holder.adapterPosition))
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(photoView)
        photoView.transitionName = photo.id
        photoView.setOnClickListener {clickListener.onPhotoClicked(holder.adapterPosition, photoView)}
    }

    private fun requestListener(adapterPosition: Int) : RequestListener<Drawable> {
        return object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                //Prevent UI hangups even though loadFailed
                clickListener.onPhotoLoaded(adapterPosition)
                return false
            }

            override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                //the transition is postponed until this image finishes loading and when
                //it's done, we start the enter transition that was previously postponed.
                clickListener.onPhotoLoaded(adapterPosition)
                return false
            }
        }
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photoView: ImageView = itemView.findViewById(R.id.photo_item)
    }
}