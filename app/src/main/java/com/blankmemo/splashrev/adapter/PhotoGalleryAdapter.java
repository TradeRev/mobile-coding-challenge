package com.blankmemo.splashrev.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blankmemo.splashrev.R;
import com.blankmemo.splashrev.datamodel.PhotoData;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by hongyuchen on 2018-08-26.
 */

public class PhotoGalleryAdapter extends RecyclerView.Adapter<PhotoGalleryViewHolder> {

    private ArrayList<PhotoData> mPhotoData;
    private PhotoClickListener mListener;
    private Context mContext;


    //Photo click listener interface
    public interface PhotoClickListener{
        void onItemClick(View v, ArrayList<PhotoData> photoData, int position);
    }

    public PhotoGalleryAdapter(Context context, ArrayList<PhotoData> photoData, PhotoClickListener mListener) {
        this.mContext = context;
        this.mPhotoData = photoData;
        this.mListener = mListener;
    }


    @NonNull
    @Override
    public PhotoGalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_photo_viewholder,parent,false);
        return new PhotoGalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PhotoGalleryViewHolder holder, final int position) {

        final PhotoData photoData = mPhotoData.get(holder.getAdapterPosition());
        ImageView photoView = holder.mPhotoView;

        Glide.with(mContext)
                .load(photoData.getUrl())
                .placeholder(R.drawable.ic_cloud_off)
                .into(photoView);

        //Click listener for each image
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(v, mPhotoData, position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return mPhotoData!=null?mPhotoData.size():0;
    }
}
