package com.senijoshua.picsrus.utils

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

abstract class EndlessScrollListener : RecyclerView.OnScrollListener() {
    var currentPage = 0
    var isLoading = true
    var previousTotal = 0 //number of items in the dataset after the last load
    val loadThreshold = 10 //number of items from the end of the list beyond which a load occurs
    var firstVisibleItem = 0 //the position of the first visible item in the recyclerview
    var visibleItemCount = 0 //number of items currently visible in the recyclerview
    var totalItemCount = 0 //Total number of items loaded into the recyclerview's adapter
    lateinit var gridLayoutManager: GridLayoutManager


    fun initScrollComponents(gridLayoutManager: GridLayoutManager, pageToLoad: Int){
       this.gridLayoutManager = gridLayoutManager
        currentPage = pageToLoad
    }


    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) { //execute only when the list is being scrolled downwards
            visibleItemCount = recyclerView!!.childCount
            totalItemCount = gridLayoutManager.itemCount
            firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition()

            if (isLoading) {
                if (totalItemCount > previousTotal) {
                    isLoading = false
                    previousTotal = totalItemCount
                }
            }

            if (!isLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + loadThreshold)) {
                currentPage += 1
                onLoadMore(currentPage)
            }
        }
    }

    abstract fun onLoadMore(pageToLoad: Int)
}