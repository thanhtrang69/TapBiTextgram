package com.example.trang.tapbitextgram.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.trang.tapbitextgram.R;
import com.example.trang.tapbitextgram.enity.Image;

import java.util.ArrayList;

/**
 * Created by Trang on 6/5/2017.
 */

public class TemPlatesAdapter extends RecyclerView.Adapter<TemPlatesAdapter.TemPlatesHolder> {
    private Context mContext;
    private ArrayList<Bitmap> imageArrayList;
    private onClickItemImageTemPlates clickItemImage;

    public void setClickItemImage(onClickItemImageTemPlates clickItemImage) {
        this.clickItemImage = clickItemImage;
    }

    public TemPlatesAdapter(Context mContext, ArrayList<Bitmap> imageArrayList) {
        this.mContext = mContext;
        this.imageArrayList = imageArrayList;
    }

    @Override
    public TemPlatesAdapter.TemPlatesHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new TemPlatesHolder(LayoutInflater.from(mContext).inflate(R.layout.item_templates, parent, false));
    }


    @Override
    public void onBindViewHolder(TemPlatesAdapter.TemPlatesHolder holder, int position) {
        holder.imageView.setImageBitmap(imageArrayList.get(position));


    }

    @Override
    public int getItemCount() {
        return imageArrayList.size();
    }

    public class TemPlatesHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        private LinearLayout linearLayout;

        public TemPlatesHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_templates);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_templates);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickItemImage.onClickItem(getAdapterPosition());
                }
            });
        }
    }

    public interface onClickItemImageTemPlates {
        void onClickItem(int position);
    }
}
