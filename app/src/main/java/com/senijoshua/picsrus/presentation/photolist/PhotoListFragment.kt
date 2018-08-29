package com.senijoshua.picsrus.presentation.photolist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.SharedElementCallback
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.TransitionInflater
import android.transition.TransitionSet
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.senijoshua.picsrus.R
import com.senijoshua.picsrus.data.RetrofitFactory
import com.senijoshua.picsrus.data.models.Photos
import com.senijoshua.picsrus.data.repo.PhotoRepoAPI
import com.senijoshua.picsrus.data.repo.PhotoRepoImpl
import com.senijoshua.picsrus.presentation.PhotoListActivity
import com.senijoshua.picsrus.presentation.photodetails.PhotoDetailsPagerFragment
import com.senijoshua.picsrus.presentation.photodetails.PhotoDetailsPagerFragment_
import com.senijoshua.picsrus.utils.EndlessScrollListener
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment
import org.androidannotations.annotations.ViewById
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

@EFragment(R.layout.fragment_photo_list)
class PhotoListFragment : Fragment(), PhotoListContract.PhotoView {
    @ViewById(R.id.photos_list)
    lateinit var photoList: RecyclerView
    lateinit var presenter: PhotoListPresenter
    lateinit var photoListAdapter: PhotoListAdapter
    lateinit var gridLayoutManager: GridLayoutManager
    var pageNumber: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = PhotoListPresenter(PhotoRepoImpl(RetrofitFactory().createRetrofitInstance()
                .create(PhotoRepoAPI::class.java)), this, AndroidSchedulers.mainThread(), Schedulers.io())
    }

    @AfterViews
    fun onViewCreated(){
        initListComponents()
        updateScrollPosition()
        initTransitions()
        postponeEnterTransition()
    }

    fun initListComponents() {
        photoListAdapter = PhotoListAdapter(activity!!, photoClickListener)
        gridLayoutManager = GridLayoutManager(activity!!, 2)
        photoList.layoutManager = gridLayoutManager
        photoList.adapter = photoListAdapter
        scrollListener.initScrollComponents(gridLayoutManager, pageNumber)
        photoList.addOnScrollListener(scrollListener)
        presenter.loadPhotoList(pageNumber)
    }

    var photoClickListener: PhotoClickListener = object : PhotoClickListener {
        override fun onPhotoLoaded(position: Int) {
            if (PhotoListActivity.currentListPosition == position){
                startPostponedEnterTransition()
            }
        }

        override fun onPhotoClicked(position: Int, sharedImageView: ImageView) {
            PhotoListActivity.currentListPosition = position
            //exclude the selected view from the fade-out
            val fragmentExitTransition = exitTransition as TransitionSet
            fragmentExitTransition.excludeTarget(sharedImageView, true)
            activity!!.supportFragmentManager
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .addSharedElement(sharedImageView, sharedImageView.transitionName)
                    .addToBackStack(null)
                    .add(R.id.photo_fragment_container, PhotoDetailsPagerFragment_(), PhotoDetailsPagerFragment::class.java.name)
                    .commit()
        }
    }

    var scrollListener: EndlessScrollListener = object : EndlessScrollListener() {
        override fun onLoadMore(pageToLoad: Int) {
            pageNumber = pageToLoad
            presenter.loadPhotoList(pageNumber)
        }
    }

    fun initTransitions(){
        exitTransition = TransitionInflater.from(context)
                .inflateTransition(R.transition.photo_list_transition)

        //adjust the shared element mapping and map the shared element names to their appropriate views
        setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(names: MutableList<String>?, sharedElements: MutableMap<String, View>?) {
                super.onMapSharedElements(names, sharedElements)

                // Get the ViewHolder for the clicked position.
                val selectedViewHolder: RecyclerView.ViewHolder = photoList.findViewHolderForAdapterPosition(PhotoListActivity.currentListPosition)

                // Map the first shared element name to the said view's ImageView.
                sharedElements!![names!![0]] = selectedViewHolder.itemView.findViewById(R.id.photo_item)
            }
        })
    }

    fun updateScrollPosition(){
        val layoutManager = photoList.layoutManager
        val currentPosition = PhotoListActivity.currentListPosition
        val viewAtPosition = layoutManager.findViewByPosition(currentPosition)
        // Scroll to position if the view for the current position is null (not currently part of
        // layout manager children), or it's not completely visible.
        if (viewAtPosition == null || layoutManager
                        .isViewPartiallyVisible(viewAtPosition, false, true)) {
            photoList.post { layoutManager.scrollToPosition(currentPosition) }
        }
    }

    override fun onPhotoListLoaded(list: List<Photos>) {
        photoListAdapter.setList(list)
        PhotoListActivity.currentPhotoList = photoListAdapter.photosList
    }

    override fun photoListLoadError(errorMessage: String) {
        Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show()
    }

}

