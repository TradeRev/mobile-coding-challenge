package com.blankmemo.splashrev.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blankmemo.splashrev.R;
import com.blankmemo.splashrev.datamodel.PhotoData;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by hongyuchen on 2018-08-27.
 */

public class PhotoFullScreenAdapter extends PagerAdapter{
    private List<PhotoData> mPhotoData;
    private Activity mActivity;
    private static final String TAG="PhotoFullScreen Adapter";

    public PhotoFullScreenAdapter(Activity activity, List<PhotoData> photoData) {
        this.mActivity = activity;
        this.mPhotoData = photoData;
    }

    @Override
    public int getCount() {
        return mPhotoData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.photo_full_screen, container,false);

        ImageView photoFullScreenImageView = view.findViewById(R.id.rl_photo_full_screen_imageview);

        Log.d(TAG, mPhotoData.get(position).getAuthor() );

        Glide.with(mActivity)
                .load(mPhotoData.get(position).getUrl())
                .error(R.drawable.ic_cloud_off)
                .into(photoFullScreenImageView);

//Go back to Gallery view when click on a photo
        photoFullScreenImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.onBackPressed();
            }
        });
        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
