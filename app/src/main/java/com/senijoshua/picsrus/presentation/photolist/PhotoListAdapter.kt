package com.senijoshua.picsrus.presentation.photolist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        var view: View = LayoutInflater.from(parent.context).inflate(R.layout.listitem_photo, parent, false)
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
        var photo: Photos = photosList[position]
        GlideApp.with(context)
                .load(photo.urls.regular)
                .error(R.drawable.ic_broken_image_black_24dp)
                .into(holder.photoView)

        holder.photoView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                clickListener.onPhotoClicked(holder.adapterPosition, holder.photoView)
            }
        })
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photoView: ImageView = itemView.findViewById(R.id.photo_item)
    }
}