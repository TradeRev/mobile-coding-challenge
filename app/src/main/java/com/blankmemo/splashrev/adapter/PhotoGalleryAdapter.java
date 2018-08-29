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
import java.util.List;

/**
 * Created by hongyuchen on 2018-08-26.
 */

public class PhotoGalleryAdapter extends RecyclerView.Adapter<PhotoGalleryViewHolder> {
    private static final int ITEM = 0;
    private static final int LOADING = 1;

    private ArrayList<PhotoData> mPhotoData;
    private PhotoClickListener mListener;
    private Context mContext;
    private boolean isLoadingAdded = false;


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
        View view = null;
        if (viewType == ITEM) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_photo_viewholder, parent, false);

        } else if (viewType == LOADING) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_photo_progressbar_viewholder, parent, false);

        }

        return new PhotoGalleryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final PhotoGalleryViewHolder holder, final int position) {
        if (getItemViewType(position)==ITEM) {
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
                    mListener.onItemClick(v, mPhotoData, holder.getAdapterPosition());
                }
            });

        }
        else if (getItemViewType(position)==LOADING){

        }

    }


    @Override
    public int getItemCount() {
        return mPhotoData!=null?mPhotoData.size():0;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mPhotoData.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void add(PhotoData photoData) {
        mPhotoData.add(photoData);
        notifyItemInserted(mPhotoData.size() - 1);
    }

    public void addAll(List<PhotoData> photoDataList) {
        for (PhotoData photoData : photoDataList) {
            add(photoData);
        }
    }

    public void remove(PhotoData photoData) {
        int position = mPhotoData.indexOf(photoData);
        if (position > -1) {
            mPhotoData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = mPhotoData.size() - 1;
        PhotoData photoData= getItem(position);

        if (photoData != null) {
            mPhotoData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public PhotoData getItem(int position) {
        return mPhotoData.get(position);
    }
}
