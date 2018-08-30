package com.blankmemo.splashrev.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankmemo.splashrev.R;
import com.blankmemo.splashrev.datamodel.PhotoData;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by hongyuchen on 2018-08-26.
 */

public class PhotoGalleryAdapter extends RecyclerView.Adapter<PhotoGalleryViewHolder>{

    private List<PhotoData> mPhotoData;
    private ListItemClickListener mListener;


    public PhotoGalleryAdapter( List<PhotoData> photoData) {
        this.mPhotoData = photoData;
    }


    @NonNull
    @Override
    public PhotoGalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_photo_viewholder, parent, false);

        return new PhotoGalleryViewHolder(view, mListener);

    }

    @Override
    public void onBindViewHolder(@NonNull final PhotoGalleryViewHolder holder, int position) {
            Glide.with(holder.mPhotoView.getContext())
                    .load(mPhotoData.get(holder.getAdapterPosition()).getUrls().getSmall())
                    .into(holder.mPhotoView);
    }


    @Override
    public int getItemCount() {
        return mPhotoData!=null?mPhotoData.size():0;
    }

    public void setOnItemClickListener(ListItemClickListener listener){
        this.mListener = listener;
    }

}
