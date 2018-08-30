package com.blankmemo.splashrev.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankmemo.splashrev.R;
import com.blankmemo.splashrev.datamodel.PhotoData;
import com.blankmemo.splashrev.utils.CommonUtils;
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
        TextView photoUser = view.findViewById(R.id.rl_photo_user);
        //Log.d(TAG, "The URL is " + (mPhotoData.get(position)).getUrls().getRegular());

        Glide.with(view.getContext())
                .load((mPhotoData.get(position)).getUrls().getRegular())
                .into(photoFullScreenImageView);
        photoUser.setText("By: " + mPhotoData.get(position).getUser().getUsername()
                                + "  "
                                +"Created on: " + CommonUtils.convertDate(mPhotoData.get(position).getCreatedAt()));

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }


}
