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

public class StickerAdapter extends RecyclerView.Adapter<StickerAdapter.TemPlatesHolder> {
    private Context mContext;
    private ArrayList<Bitmap> imageArrayList;
    private onClickItemImage clickItemImage;

    public void setClickItemImage(onClickItemImage clickItemImage) {
        this.clickItemImage = clickItemImage;
    }

    public StickerAdapter(Context mContext, ArrayList<Bitmap> imageArrayList) {
        this.mContext = mContext;
        this.imageArrayList = imageArrayList;
    }

    @Override
    public StickerAdapter.TemPlatesHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new TemPlatesHolder(LayoutInflater.from(mContext).inflate(R.layout.item_sticker, parent, false));
    }


    @Override
    public void onBindViewHolder(StickerAdapter.TemPlatesHolder holder, int position) {
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
            imageView = (ImageView) itemView.findViewById(R.id.iv_sticker);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_sticker);
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
