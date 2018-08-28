package com.blankmemo.splashrev.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.blankmemo.splashrev.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hongyuchen on 2018-08-26.
 */

public class PhotoGalleryViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.ll_photo)
    ImageView mPhotoView;


    public PhotoGalleryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }
}