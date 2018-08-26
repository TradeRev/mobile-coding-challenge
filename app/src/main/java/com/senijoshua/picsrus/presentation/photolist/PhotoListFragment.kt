package com.senijoshua.picsrus.presentation.photolist

import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.senijoshua.picsrus.R
import com.senijoshua.picsrus.data.RetrofitFactory
import com.senijoshua.picsrus.data.models.Photos
import com.senijoshua.picsrus.data.repo.PhotoRepoAPI
import com.senijoshua.picsrus.data.repo.PhotoRepoImpl
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

    @AfterViews
    fun onViewCreated(){
        presenter = PhotoListPresenter(PhotoRepoImpl(RetrofitFactory().createRetrofitInstance()
                .create(PhotoRepoAPI::class.java)),
                this, AndroidSchedulers.mainThread(), Schedulers.io())
        photoListAdapter = PhotoListAdapter(activity!!, photoClickListener)
        gridLayoutManager = GridLayoutManager(activity!!, 2)
        photoList.layoutManager = gridLayoutManager
        photoList.adapter = photoListAdapter
        presenter.loadPhotoList()
    }


    var photoClickListener: PhotoClickListener = object : PhotoClickListener {
        override fun onPhotoClicked(position: Int, photo: Photos, sharedImageView: View) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    override fun onPhotoListLoaded(list: List<Photos>) {
        photoListAdapter.setList(list)
    }

    override fun photoListLoadError() {
        Toast.makeText(activity, "Load error", Toast.LENGTH_LONG).show()
    }



}

