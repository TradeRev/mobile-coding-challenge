package com.blankmemo.splashrev.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.blankmemo.splashrev.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hongyuchen on 2018-08-26.
 */

public class PhotoGalleryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    ImageView mPhotoView;
    private ListItemClickListener mListener;


    public PhotoGalleryViewHolder(@NonNull View itemView, ListItemClickListener mListener) {
        super(itemView);
        this.mListener=mListener;
        mPhotoView = itemView.findViewById(R.id.ll_photo);
        mPhotoView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(mListener!=null) {
            mListener.onItemClick(view, getAdapterPosition());
        }
    };
}