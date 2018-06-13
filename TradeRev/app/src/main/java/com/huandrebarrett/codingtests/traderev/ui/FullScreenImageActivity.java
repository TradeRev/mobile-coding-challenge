package com.huandrebarrett.codingtests.traderev.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.huandrebarrett.codingtests.traderev.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class FullScreenImageActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ImagePagerAdapter adapter;
    public final static String BUNDLE_KEY = "bundle";
    public final static String LIST_KEY = "list";
    public final static String RECYCLER_POSITION_KEY = "recyclerPosition";
    public final static String VIEWPAGER_POSITION_KEY = "viewPagerPosition";
    public final static String IMAGE = "Image";
    private int origPos;
    private List<String> images;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity);

        viewPager = findViewById(R.id.view_pager);
        extractBundleData(getBundle());
        setupViewPager(getBundle());

    }

    private Bundle getBundle() {
        return getIntent().getBundleExtra(BUNDLE_KEY);
    }

    private void setupViewPager(Bundle bundle) {
        adapter = new ImagePagerAdapter(images);
        viewPager.setAdapter(adapter);
        int position = bundle.getInt(RECYCLER_POSITION_KEY);
        viewPager.setTransitionName(IMAGE + position);
        viewPager.setCurrentItem(position);
        origPos = position;
    }

    private void extractBundleData(Bundle bundle) {
        images = bundle.getStringArrayList(LIST_KEY);
    }

    private class ImagePagerAdapter extends PagerAdapter {
        private List<String> mImages;

        ImagePagerAdapter(List<String> mImages) {
            this.mImages = mImages;
        }

        @Override
        public int getCount() {
            return mImages.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == ((ImageView) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            Context context = FullScreenImageActivity.this;
            ImageView imageView = new ImageView(context);
            int padding = context.getResources().getDimensionPixelSize(
                    R.dimen.padding_medium);
            imageView.setPadding(padding, padding, padding, padding);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            Picasso.get().load(mImages.get(position)).into(imageView);
            container.addView(imageView, 0);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            ((ViewPager) container).removeView((ImageView) object);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.anim);
    }

    @Override
    public void onBackPressed() {
        getDefaultSharedPreferences(this)
                .edit()
                .putInt(VIEWPAGER_POSITION_KEY, viewPager.getCurrentItem())
                .apply();
        setResult(Activity.RESULT_OK);
        super.onBackPressed();
        if (origPos != viewPager.getCurrentItem()) {
            finish();
        }
    }
}
