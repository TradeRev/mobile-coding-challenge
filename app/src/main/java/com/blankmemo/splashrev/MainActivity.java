package com.blankmemo.splashrev;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.blankmemo.splashrev.adapter.ListItemClickListener;
import com.blankmemo.splashrev.adapter.PhotoGalleryAdapter;
import com.blankmemo.splashrev.api.ApiConstants;
import com.blankmemo.splashrev.api.ApiFactory;
import com.blankmemo.splashrev.api.ApiInterface;
import com.blankmemo.splashrev.constants.intentConstants;
import com.blankmemo.splashrev.datamodel.PhotoData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements ListItemClickListener{
    @BindView(R.id.cl_splashrev_main) RecyclerView mSplashrevMain;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    private CompositeDisposable compositeDisposable;
    private PhotoGalleryAdapter mAdapter;
    private List<PhotoData> mPhotoData;
    private ApiFactory ApiFactory;


    private static final String TAG="Main Activity";
    private int defaultPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        //init variables, could use dagger to inject instead
        mPhotoData=new ArrayList<>();
        compositeDisposable=new CompositeDisposable();
        Map<String,String> photosMap=new HashMap<>();
        photosMap.put("client_id", ApiConstants.API_KEY);
        photosMap.put("page", "1");

        mProgressBar.setVisibility(View.VISIBLE);

        mSplashrevMain.setLayoutManager(new GridLayoutManager(this, 3));

        //make API call
        ApiFactory=
                new ApiFactory(new Retrofit.Builder(),new OkHttpClient.Builder(),new HttpLoggingInterceptor());


        compositeDisposable.add(
                ApiFactory.createApi(ApiConstants.API_END_POINT)
                        .getPhotoData(photosMap)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<PhotoData>>() {
                            @Override
                            public void onSuccess(List<PhotoData> photoData) {
                                mPhotoData=photoData;
                                mAdapter=new PhotoGalleryAdapter(mPhotoData);
                                mAdapter.setOnItemClickListener(MainActivity.this);
                                mSplashrevMain.setAdapter(mAdapter);
                                mProgressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }
                        }));

        mSplashrevMain.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(!recyclerView.canScrollVertically(1)){
                    onPageEnd();
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void onPageEnd() {
        Map<String,String> photosMap=new HashMap<>();
        photosMap.put("client_id", ApiConstants.API_KEY);
        photosMap.put("page", Integer.toString(mPhotoData.size()/10)+1);
        compositeDisposable.add(ApiFactory.createApi(ApiConstants.API_END_POINT)
                .getPhotoData(photosMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<PhotoData>>() {
                    @Override
                    public void onSuccess(List<PhotoData> photoData) {
                        mPhotoData.addAll(mPhotoData.size(),photoData);
                        mAdapter.notifyDataSetChanged();
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onItemClick(View v, int position) {
        defaultPosition = position;
        Intent intent = new Intent(getApplicationContext(), PhotoFullScreenActivity.class);
        //intent.putParcelableArrayListExtra(intentConstants.FULL_SCREEN_PHOTO_DETAILS, photoData);
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
            Log.d(TAG, "Default Position is  " + defaultPosition);
            if (resultCode == RESULT_OK) {
                int gridPosition = data.getIntExtra(intentConstants.VIEW_PAGER_PHOTO_POSITION, defaultPosition);
                mSplashrevMain.scrollToPosition(gridPosition);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(compositeDisposable!=null && !compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }

}
