package com.example.trang.tapbitextgram.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.trang.tapbitextgram.R;

import java.util.ArrayList;

/**
 * Created by Trang on 6/5/2017.
 */

public class FramesAdapter extends RecyclerView.Adapter<FramesAdapter.FramesHolder> {
    private Context mContext;
    private ArrayList<Bitmap> imageArrayList;
    private onClickItemImage clickItemImage;

    public void setClickItemImage(onClickItemImage clickItemImage) {
        this.clickItemImage = clickItemImage;
    }

    public FramesAdapter(Context mContext, ArrayList<Bitmap> imageArrayList) {
        this.mContext = mContext;
        this.imageArrayList = imageArrayList;
    }

    @Override
    public FramesAdapter.FramesHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new FramesHolder(LayoutInflater.from(mContext).inflate(R.layout.item_frames, parent, false));
    }


    @Override
    public void onBindViewHolder(FramesAdapter.FramesHolder holder, int position) {
            holder.imageView.setImageBitmap(imageArrayList.get(position));


    }

    @Override
    public int getItemCount() {
        return imageArrayList.size();
    }

    public class FramesHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        private LinearLayout linearLayout;

        public FramesHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_frames);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_frames);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickItemImage.onClickItem(getAdapterPosition());
                }
            });
        }
    }

    public interface onClickItemImage {
        void onClickItem(int position);
    }
}
