package com.blankmemo.splashrev;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.blankmemo.splashrev.adapter.PhotoGalleryAdapter;
import com.blankmemo.splashrev.constants.intentConstants;
import com.blankmemo.splashrev.datamodel.PhotoData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements PhotoGalleryAdapter.PhotoClickListener{
    @BindView(R.id.cl_splashrev_main) RecyclerView mSplashrevMain;
    private static final String TAG="Main Activity";
    private StaggeredGridLayoutManager mLayoutManager;
    private int defaultPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mSplashrevMain.setHasFixedSize(true);
//        mLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
//        mLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
//        mSplashrevMain.setLayoutManager(mLayoutManager);
//        mSplashrevMain.setAdapter(new PhotoGalleryAdapter(this, PhotoData.getPhotoData(), this));
//        mSplashrevMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                ((StaggeredGridLayoutManager)recyclerView.getLayoutManager()).invalidateSpanAssignments();
//            }
//        });


        mSplashrevMain.setLayoutManager(new GridLayoutManager(this, 3));
        mSplashrevMain.setAdapter(new PhotoGalleryAdapter(this, PhotoData.getPhotoData(), this));
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onItemClick(View v, ArrayList<PhotoData> photoData, int position) {
        defaultPosition = position;
        Intent intent = new Intent(getApplicationContext(), PhotoFullScreenActivity.class);
        intent.putParcelableArrayListExtra(intentConstants.FULL_SCREEN_PHOTO_DETAILS, photoData);
        intent.putExtra(intentConstants.FULL_SCREEN_PHOTO_POSITION,position);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, v, "profile");
        //Log.d(TAG, "Image Title is: " + photoData.getTitle());
            startActivityForResult(intent, intentConstants.START_FULL_SCREEN_REQUEST_CODE, options.toBundle());


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == intentConstants.START_FULL_SCREEN_REQUEST_CODE) {
            Log.d(TAG, "Result code is " + resultCode);
            if (resultCode == RESULT_OK) {
                int gridPosition = data.getIntExtra(intentConstants.VIEW_PAGER_PHOTO_POSITION, defaultPosition);
                mSplashrevMain.scrollToPosition(gridPosition);
            }
        }
    }
}
