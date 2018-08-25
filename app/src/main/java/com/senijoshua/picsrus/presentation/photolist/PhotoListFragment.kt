package com.senijoshua.picsrus.presentation.photolist

import android.support.v4.app.Fragment
import com.senijoshua.picsrus.R
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment

@EFragment(R.layout.fragment_photo_list)
class PhotoListFragment : Fragment(), PhotoListContract.PhotoView {

    @AfterViews
    fun onViewCreated(){
        //instantiate presenter here with repo, schedulers and view instance
        //load images with presenter here.
    }


    override fun onPhotoListLoaded() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun photoListLoadError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}

