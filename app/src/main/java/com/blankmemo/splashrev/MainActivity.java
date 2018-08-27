package com.blankmemo.splashrev;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankmemo.splashrev.adapter.PhotoGalleryAdapter;
import com.blankmemo.splashrev.constants.intentConstants;
import com.blankmemo.splashrev.datamodel.PhotoData;

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
        mSplashrevMain.setLayoutManager(new GridLayoutManager(this, 3));
        mSplashrevMain.setAdapter(new PhotoGalleryAdapter(this, PhotoData.getPhotoData(), this));
    }

    @Override
    public void onItemClick(View v, PhotoData photoData) {
        Intent intent = new Intent(getApplicationContext(), PhotoDetailsActivity.class);
        intent.putExtra(intentConstants.FULL_SCREEN_PHOTO_DETAILS, photoData);
        //Log.d(TAG, "Image Title is: " + photoData.getTitle());
        startActivity(intent);

    }
}
