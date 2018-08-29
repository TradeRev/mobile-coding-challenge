package com.blankmemo.splashrev;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.blankmemo.splashrev.adapter.PhotoFullScreenAdapter;
import com.blankmemo.splashrev.constants.intentConstants;
import com.blankmemo.splashrev.datamodel.PhotoData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoFullScreenActivity extends AppCompatActivity {
    @BindView(R.id.rl_photo_full_screen_view_pager)
    ViewPager mPhotoFullScreenViewPager;
    private PhotoFullScreenAdapter mPhotoFullScreenAdapter;

    private static final String TAG="PhotoDetails Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_full_screen_view);

        ButterKnife.bind(this);
        int position = getIntent().getIntExtra(intentConstants.FULL_SCREEN_PHOTO_POSITION,0);
        //ArrayList<PhotoData> photoData = getIntent().getParcelableArrayListExtra(intentConstants.FULL_SCREEN_PHOTO_DETAILS);
        //mPhotoFullScreenViewPager.setAdapter(new PhotoFullScreenAdapter(this, photoData));
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
