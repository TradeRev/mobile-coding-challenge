package com.traderev.codingchallenge.view.activities


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.traderev.codingchallenge.R
import com.traderev.codingchallenge.common.Constants
import com.traderev.codingchallenge.model.PhotoSingleton
import com.traderev.codingchallenge.model.Photos
import com.traderev.codingchallenge.view.adapter.PhotoPagerAdapter
import kotlinx.android.synthetic.main.activity_view_pager.*


class PhotoPagerActivity : AppCompatActivity() {


    private lateinit var photoPagerAdapter: PhotoPagerAdapter
    private var clickedPosition: Int = 0

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        clickedPosition = intent.getIntExtra(Constants.CURRENTPHOTOPOSITION, 0)
        initAdapter()
    }

    override fun onBackPressed() {

        val intent = Intent()
        intent.putExtra(Constants.CURRENTPHOTOPOSITION, view_pager.currentItem)
        setResult(Constants.LASTPHOTOPOSITION, intent)
        super.onBackPressed()
    }

    private fun initAdapter() {

        var list: ArrayList<Photos> = ArrayList()
        list.addAll(PhotoSingleton.getPhotos() as ArrayList)
        photoPagerAdapter = PhotoPagerAdapter(clickedPosition, list)
        photoPagerAdapter.notifyDataSetChanged()
        view_pager.adapter = photoPagerAdapter
        view_pager.setCurrentItem(clickedPosition, true)

    }


}
