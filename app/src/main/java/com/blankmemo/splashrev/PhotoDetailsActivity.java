package com.blankmemo.splashrev;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankmemo.splashrev.constants.intentConstants;
import com.blankmemo.splashrev.datamodel.PhotoData;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoDetailsActivity extends AppCompatActivity {
    @BindView(R.id.ll_photo_full_screen_imageview) ImageView photoFullScreenImageView;
    @BindView(R.id.ll_photo_author) TextView photoAuthor;

    private static final String TAG="PhotoDetails Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);

        ButterKnife.bind(this);

        PhotoData photoData = getIntent().getParcelableExtra(intentConstants.FULL_SCREEN_PHOTO_DETAILS);
        Log.d(TAG, "Image Url is: " + photoData.getUrl());

        Glide.with(this)
                .load(photoData.getUrl())
                .error(R.drawable.ic_cloud_off)
                .into(photoFullScreenImageView);
    }



}
