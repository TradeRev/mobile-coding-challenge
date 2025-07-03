package com.example.joao.photoscodechallenge.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joao.photoscodechallenge.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_details_item.view.*

/**
 * Created by Joao Alvares Neto on 05/05/2018.
 */
class MyImageDetailsAdapter(private val urls: MutableList<String>)
    : RecyclerView.Adapter<MyImageDetailsAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.image_details_item, parent, false))
    }

    override fun getItemCount(): Int {
        Log.i("AAAA", "adaptersize" + urls.size.toString())

        return urls.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val url = urls[position]
        Log.i("AAAA DetailsADapter", position.toString())
        Picasso
                .get()
                .load(url)
                .into(holder.itemView.image)

    }
}