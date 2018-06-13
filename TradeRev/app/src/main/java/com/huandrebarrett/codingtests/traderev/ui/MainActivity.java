package com.huandrebarrett.codingtests.traderev.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import com.huandrebarrett.codingtests.traderev.R;
import com.huandrebarrett.codingtests.traderev.service.PhotoService;
import com.huandrebarrett.codingtests.traderev.service.PhotoServiceImpl;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;
import static android.support.v7.widget.LinearLayoutManager.VERTICAL;
import static com.huandrebarrett.codingtests.traderev.ui.FullScreenImageActivity.BUNDLE_KEY;
import static com.huandrebarrett.codingtests.traderev.ui.FullScreenImageActivity.LIST_KEY;
import static com.huandrebarrett.codingtests.traderev.ui.FullScreenImageActivity.RECYCLER_POSITION_KEY;
import static com.huandrebarrett.codingtests.traderev.ui.FullScreenImageActivity.VIEWPAGER_POSITION_KEY;

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private ArrayList<String> thumbNailImages;
    private ArrayList<String> bigImages;
    private final static int FULL_SCREEN_ACTIVITY = 1000;
    private final static int COLUMNS = 3;
    public final static String REGULAR_API_KEY = "regular";
    public final static String SMALL_API_KEY = "small";
    public final static String URL_API_KEY = "urls";
    private final static String UNSPLASH_URL = "https://api.unsplash.com/photos/random/?client_id=720485d9148d99656b05124c57e81ce4197ad23316d10849896c10ee1cef2f14&count=48";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setUpListeners();
        makeCall();
    }

    private void setUpListeners() {
        myRecyclerViewAdapter.setClickListener(new MyRecyclerViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String transitionName) {

                Intent intent = new Intent(MainActivity.this, FullScreenImageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(LIST_KEY, bigImages);
                bundle.putInt(RECYCLER_POSITION_KEY, position);
                intent.putExtra(BUNDLE_KEY, bundle);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(MainActivity.this,
                                view,
                                transitionName);
                startActivityForResult(intent, FULL_SCREEN_ACTIVITY, options.toBundle());
            }
        });
        myRecyclerViewAdapter.setOnBottomReachedListener(new MyRecyclerViewAdapter.ListEndListener() {
            @Override
            public void onListEnd(int position) {
                makeCall();
            }
        });
    }

    private void init() {
        recyclerView = findViewById(R.id.recycler);
        thumbNailImages = new ArrayList<>();
        bigImages = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, COLUMNS, VERTICAL, false));
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(MainActivity.this);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }


    private Request buildRequest() {
        return new Request.Builder()
                .get()
                .url(UNSPLASH_URL)
                .build();
    }

    private Callback buildCallBack() {
        return new Callback() {
            @Override
            public void onFailure(@NonNull Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    updateLists(response);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myRecyclerViewAdapter.setList(thumbNailImages);
                            myRecyclerViewAdapter.notifyDataSetChanged();

                        }
                    });

                }
            }
        };
    }

    private void updateLists(Response response) {
        try {
            final String myResponse = response.body().string();
            JSONArray json = new JSONArray(myResponse);
            for (int i = 0; i < json.length(); i++) {
                thumbNailImages.add(json.getJSONObject(i).getJSONObject(URL_API_KEY).getString(SMALL_API_KEY));
                bigImages.add(json.getJSONObject(i).getJSONObject(URL_API_KEY).getString(REGULAR_API_KEY));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeCall() {
        PhotoService photoService = new PhotoServiceImpl();
        photoService.getPhotos(buildRequest(),buildCallBack());
    }

    public void onActivityReenter(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            postponeEnterTransition();
            // Start the postponed transition when the recycler view is ready to be drawn.
            recyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            int position = getSharedPreference().getInt(VIEWPAGER_POSITION_KEY, 0);
                            RecyclerView.SmoothScroller smoothScroller = new CenterSmoothScroller(MainActivity.this,recyclerView.getChildAt(position) == null);
                            smoothScroller.setTargetPosition(position);
                            recyclerView.getLayoutManager().startSmoothScroll(smoothScroller);
                            startPostponedEnterTransition();
                        }
                    });
                    return true;
                }
            });
        }
    }

    private SharedPreferences getSharedPreference() {
        return getDefaultSharedPreferences(MainActivity.this);
    }

    static class CenterSmoothScroller extends LinearSmoothScroller {

        private boolean isOffScreen;

        CenterSmoothScroller(Context context, boolean isOffScreen) {
            super(context);
            this.isOffScreen = isOffScreen;
        }

        @Override
        public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
            if (isOffScreen) {
                return (boxStart + (boxEnd - boxStart) / 2) - (viewStart + (viewEnd - viewStart) / 2);
            } else
                return super.calculateDtToFit(viewStart, viewEnd, boxStart, boxEnd, snapPreference);
        }
    }
}
