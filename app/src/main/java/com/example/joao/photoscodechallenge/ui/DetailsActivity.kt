package com.example.joao.photoscodechallenge.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearSnapHelper
import com.example.joao.photoscodechallenge.R
import com.example.joao.photoscodechallenge.adapter.MyImageDetailsAdapter
import com.example.joao.photoscodechallenge.entry.PhotoDetails
import kotlinx.android.synthetic.main.activity_details.*

/**
 * Created by Joao Alvares Neto on 05/05/2018.
 */

class DetailsActivity: AppCompatActivity() {

    companion object {
        fun start(activity: Activity, position: Int, photoDetails: ArrayList<PhotoDetails>) {

            val intent = Intent(activity,DetailsActivity::class.java)

            val bundle = Bundle()
            bundle.putParcelableArrayList("photoDetails", photoDetails)
            bundle.putInt("position",position)

            activity.startActivity(intent.putExtras(bundle))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val listPhotoDetails = intent.extras.getParcelableArrayList<PhotoDetails>("photoDetails")
        val position = intent.extras.getInt("position")

        val items = arrayListOf<String>()

        for(item in listPhotoDetails){
            items.add(item.url)
        }

        LinearSnapHelper().attachToRecyclerView(detailsRecyclerView)

        with(detailsRecyclerView){
            adapter = MyImageDetailsAdapter(items)
            setHasFixedSize(true)
            layoutManager = android.support.v7.widget.LinearLayoutManager(this@DetailsActivity,
                    android.support.v7.widget.LinearLayoutManager.HORIZONTAL,
                    false)
            visibility = android.view.View.VISIBLE
        }
        detailsRecyclerView.layoutManager.scrollToPosition(position)
    }
}