package com.blankmemo.splashrev;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.blankmemo.splashrev.adapter.PhotoFullScreenAdapter;
import com.blankmemo.splashrev.constants.intentConstants;
import com.blankmemo.splashrev.datamodel.PhotoData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoFullScreenActivity extends AppCompatActivity {
    @BindView(R.id.rl_photo_full_screen_view_pager)
    ViewPager mPhotoFullScreenViewPager;
    List<PhotoData> mPhotoData;
    private static final String TAG="PhotoFull Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_full_screen_view);

        ButterKnife.bind(this);
        int position = getIntent().getIntExtra(intentConstants.FULL_SCREEN_PHOTO_POSITION,0);
        mPhotoData = (List<PhotoData>)getIntent().getSerializableExtra(intentConstants.FULL_SCREEN_PHOTO_DETAILS);
        mPhotoFullScreenViewPager.setAdapter(new PhotoFullScreenAdapter(this, mPhotoData));
        //Log.d(TAG,"the position and url is " + position + url);

        // displaying selected image first
        mPhotoFullScreenViewPager.setCurrentItem(position);
    }

    @Override
    public void onBackPressed() {
        Intent position = new Intent();
        position.putExtra(intentConstants.VIEW_PAGER_PHOTO_POSITION, mPhotoFullScreenViewPager.getCurrentItem());
        setResult(RESULT_OK, position);
        super.onBackPressed();
    }


}
