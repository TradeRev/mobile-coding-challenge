package com.traderev.codingchallenge.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.traderev.codingchallenge.R
import com.traderev.codingchallenge.common.Constants
import com.traderev.codingchallenge.common.LogType
import com.traderev.codingchallenge.common.State
import com.traderev.codingchallenge.model.PhotoSingleton
import com.traderev.codingchallenge.model.Photos
import com.traderev.codingchallenge.utility.UtilHelper
import com.traderev.codingchallenge.view.adapter.PhotoListAdapter
import com.traderev.codingchallenge.viewmodel.PhotoListViewModel
import com.traderev.codingchallenge.viewmodel.PhotoListViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_photo_list.*
import javax.inject.Inject

class PhotoListActivity : AppCompatActivity() {

    @Inject
    lateinit var photoListViewModelFactory: PhotoListViewModelFactory
    private lateinit var viewModel: PhotoListViewModel
    private lateinit var photoListAdapter: PhotoListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_list)
        AndroidInjection.inject(this)
        viewModel = ViewModelProviders.of(this, photoListViewModelFactory).get(
            PhotoListViewModel::class.java
        )
        initAdapter()
        initState()
    }

    private fun initAdapter() {
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        photoListAdapter = PhotoListAdapter(object : PhotoListAdapter.Listener {

            override fun onImageClicked(view: View, position: Int) {

                transition(view, position)
            }

        }) { viewModel.retry() }

        rv_posters.layoutManager = staggeredGridLayoutManager
        rv_posters.adapter = photoListAdapter
        viewModel.photoList.observe(this, Observer {

            photoListAdapter.submitList(it)
        })
    }

    private fun transition(view: View, position: Int) {

        val intent = Intent(this@PhotoListActivity, PhotoPagerActivity::class.java)
        intent.putExtra(Constants.CURRENTPHOTOPOSITION, position)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            view,
            getString(R.string.transition)
        )
        startActivityForResult(intent, Constants.LASTPHOTOPOSITION, options.toBundle())


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val position: Int? = data?.getIntExtra(Constants.CURRENTPHOTOPOSITION, 0)
        rv_posters.scrollToPosition(position as Int)
        UtilHelper.displayLog(
            "" + "" + data.getIntExtra(Constants.CURRENTPHOTOPOSITION, 0),
            PhotoListActivity::class.java.simpleName,
            LogType.DEBUG
        )

    }


    private fun initState() {
        tv_error.setOnClickListener { viewModel.retry() }

        viewModel.getResponse().observe(
            this,
            Observer {
                val updatedPhotos: ArrayList<Photos>? = PhotoSingleton.getPhotos()
                updatedPhotos?.addAll(it)
                if (updatedPhotos != null) {
                    PhotoSingleton.setPhotos(updatedPhotos)
                } else if (it != null) {
                    PhotoSingleton.setPhotos(it as ArrayList<Photos>)
                }

            }
        )
        viewModel.getState().observe(this, Observer { state ->
            pb_progress.visibility = if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE
            tv_error.visibility = if (viewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE

            else View.GONE
            if (!viewModel.listIsEmpty()) {
                photoListAdapter.setState(state ?: State.DONE)
            }
        })
    }

}