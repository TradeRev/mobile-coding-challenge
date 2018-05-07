package com.example.joao.photoscodechallenge.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import com.example.joao.photoscodechallenge.R
import com.example.joao.photoscodechallenge.adapter.MyImageDetailsAdapter
import com.example.joao.photoscodechallenge.extensions.visible
import kotlinx.android.synthetic.main.activity_details.*

/**
 * Created by Joao Alvares Neto on 05/05/2018.
 */

class DetailsActivity: AppCompatActivity() {

    companion object {

        private const val DETAILS = "details"
        private const val POSITION = "position"

        fun start(activity: Activity, position: Int, photoDetails: ArrayList<String>) {

            val intent = Intent(activity,DetailsActivity::class.java)

            val bundle = Bundle()
            bundle.putStringArrayList(DETAILS, photoDetails)
            bundle.putInt(POSITION,position)

            activity.startActivity(intent.putExtras(bundle))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val listPhotoDetails = intent.extras.getStringArrayList(DETAILS)
        val position = intent.extras.getInt(POSITION)

        LinearSnapHelper().attachToRecyclerView(detailsRecyclerView)

        with(detailsRecyclerView){
            adapter = MyImageDetailsAdapter(listPhotoDetails)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DetailsActivity,
                    LinearLayoutManager.HORIZONTAL,
                    false)
            visible()
        }
        detailsRecyclerView.layoutManager.scrollToPosition(position)
    }
}