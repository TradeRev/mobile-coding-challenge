package com.example.joao.photoscodechallenge.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.support.v7.widget.RecyclerView
import com.example.joao.photoscodechallenge.R
import com.example.joao.photoscodechallenge.adapter.MyImageDetailsAdapter
import com.example.joao.photoscodechallenge.extensions.visible
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.image_details_item.*

/**
 * Created by Joao Alvares Neto on 05/05/2018.
 */

class DetailsActivity: AppCompatActivity() {

    private val snapHelper by lazy { LinearSnapHelper() }

    private var currentPosition: Int = 0

    private val linearLayoutManager by lazy {
        LinearLayoutManager(this@DetailsActivity,
                LinearLayoutManager.HORIZONTAL,
                false)
    }

    companion object {

        private const val DETAILS = "details"
        private const val POSITION = "position"
        const val CURRENT_POSITION = "currentPosition"
        const val REQUEST_CODE = 100
        const val VIEW_NAME_HEADER_IMAGE = "IMAGE_TRANSITION"

        fun startActivityForResult(activity: Activity, position: Int, photoDetails: ArrayList<String>) {

            val intent = Intent(activity,DetailsActivity::class.java)

            val bundle = Bundle()
            bundle.putStringArrayList(DETAILS, photoDetails)
            bundle.putInt(POSITION,position)
            activity.startActivityForResult(intent.putExtras(bundle),REQUEST_CODE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        RxView
                .clicks(close)
                .subscribe({
                    setResult(Activity.RESULT_OK, mountIntent())
                    finish()
                })

        val listPhotoDetails = intent.extras.getStringArrayList(DETAILS)
        val position = intent.extras.getInt(POSITION)


        ViewCompat.setTransitionName(image, VIEW_NAME_HEADER_IMAGE)

        snapHelper.attachToRecyclerView(detailsRecyclerView)

        with(detailsRecyclerView){
            adapter = MyImageDetailsAdapter(listPhotoDetails)
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            layoutManager.scrollToPosition(position)
            visible()
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    currentPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition()
                }
            })
        }
    }

    private fun mountIntent(): Intent {
        val resultIntent = Intent()
        resultIntent.putExtra(CURRENT_POSITION, currentPosition)
        return resultIntent
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_OK, mountIntent())
        finish()
    }
}