package com.traderev.codingchallenge.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.traderev.codingchallenge.R
import com.traderev.codingchallenge.common.State
import kotlinx.android.synthetic.main.item_list_footer.view.*

//For showing the loader as the last item of the grid
class ListFooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(status: State?) {
        itemView.progress_bar.visibility = if (status == State.LOADING) VISIBLE else View.INVISIBLE
        itemView.tv_error.visibility = if (status == State.ERROR) VISIBLE else View.INVISIBLE
    }

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): ListFooterViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_footer, parent, false)
            view.tv_error.setOnClickListener { retry() }
            return ListFooterViewHolder(view)
        }
    }
}