package com.huandrebarrett.codingtests.traderev.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.huandrebarrett.codingtests.traderev.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.huandrebarrett.codingtests.traderev.ui.FullScreenImageActivity.IMAGE;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<String> mData = new ArrayList();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private ListEndListener onBottomReachedListener;

    MyRecyclerViewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == mData.size() - 1){
            onBottomReachedListener.onListEnd(position);
        }
        String picture = mData.get(position);
        holder.imageView.setTransitionName("Image"+position);
        Picasso.get().load(picture).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.picture);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(imageView, getAdapterPosition(),IMAGE+getAdapterPosition());
        }
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public void setOnBottomReachedListener(ListEndListener onBottomReachedListener){
        this.onBottomReachedListener = onBottomReachedListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position,String transitionName);
    }

    public interface ListEndListener {
        void onListEnd(int position);
    }

    public void setList(List<String> strings){
        mData.clear();
        mData.addAll(strings);
    }
}
