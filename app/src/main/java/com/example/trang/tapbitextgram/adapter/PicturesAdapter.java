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
import com.example.trang.tapbitextgram.enity.ImageLoadSdCard;

import java.util.ArrayList;

/**
 * Created by Trang on 6/3/2017.
 */

public class PicturesAdapter extends RecyclerView.Adapter<PicturesAdapter.PictureHolder> {
    private Context mContext;
    private ArrayList<ImageLoadSdCard> arrayList;
    private onClickItemImage clickItemImage;

    public void setClickItemImage(onClickItemImage clickItemImage) {
        this.clickItemImage = clickItemImage;
    }

    public PicturesAdapter(Context mContext, ArrayList<ImageLoadSdCard> bitmapArrayList) {
        this.mContext = mContext;
        this.arrayList = bitmapArrayList;
    }



    @Override
    public PicturesAdapter.PictureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PictureHolder(LayoutInflater.from(mContext).inflate(R.layout.item_pictures, parent, false));
    }

    @Override
    public void onBindViewHolder(PicturesAdapter.PictureHolder holder, int position) {
        holder.imageView.setImageURI(arrayList.get(position).getUrlImage());

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class PictureHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private LinearLayout linearLayout;

        public PictureHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_item_pictures);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_item_pictures);
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
