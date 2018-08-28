package com.blankmemo.splashrev;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mSplashrevMain.setHasFixedSize(true);
        mSplashrevMain.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        mSplashrevMain.setAdapter(new PhotoGalleryAdapter(this, PhotoData.getPhotoData(), this));
    }

    @Override
    public void onItemClick(View v, ArrayList<PhotoData> photoData, int position) {
        Intent intent = new Intent(getApplicationContext(), PhotoFullScreenActivity.class);
        intent.putParcelableArrayListExtra(intentConstants.FULL_SCREEN_PHOTO_DETAILS, photoData);
        intent.putExtra(intentConstants.FULL_SCREEN_PHOTO_POSITION,position);
        //Log.d(TAG, "Image Title is: " + photoData.getTitle());
        startActivity(intent);

    }
}
