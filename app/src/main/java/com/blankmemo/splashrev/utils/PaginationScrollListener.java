package com.blankmemo.splashrev.utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by hongyuchen on 2018-08-29.
 */

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener{
    private GridLayoutManager mGridlayoutManager;

    public PaginationScrollListener(GridLayoutManager gridLayoutManager) {
        this.mGridlayoutManager = gridLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = mGridlayoutManager.getChildCount();
        int totalItemCount = mGridlayoutManager.getItemCount();
        int firstVisibleItemPosition = mGridlayoutManager.findFirstVisibleItemPosition();

        Log.d("PaginationListener", "visibleitemcount is "+visibleItemCount + " Totalitemcount is "+ totalItemCount + " first visibleitemposition is " + firstVisibleItemPosition);
        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0) {
                loadMoreItems();
            }
        }
    }

    public abstract void loadMoreItems();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}
