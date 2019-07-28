package com.traderev.codingchallenge.view.adapter


import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.practice.gallery.common.State
import com.practice.gallery.model.Photos


class PhotoListAdapter(
    listener: Listener,
    private val retry: () -> Unit
) : PagedListAdapter<Photos, ViewHolder>(PhotosDiffCallback) {

    private val dataViewType = 1
    private val footerViewType = 2
    private var state = State.LOADING
    private var clickListener = listener
    private var clickedPosition = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == dataViewType) PhotosViewHolder.create(parent) else ListFooterViewHolder.create(
            retry,
            parent
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) == dataViewType) {
            (holder as PhotosViewHolder).bind(getItem(position))

            holder.itemView.setOnClickListener {
                clickedPosition = position
                clickListener.onImageClicked(it, position)
            }
        } else (holder as ListFooterViewHolder).bind(state)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) dataViewType else footerViewType
    }

    companion object {
        val PhotosDiffCallback = object : DiffUtil.ItemCallback<Photos>() {
            override fun areItemsTheSame(oldItem: Photos, newItem: Photos): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Photos, newItem: Photos): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }

    interface Listener {
        fun onImageClicked(view: View, position: Int)
    }
}