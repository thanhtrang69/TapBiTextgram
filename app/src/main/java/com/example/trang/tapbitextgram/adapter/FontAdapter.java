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

public class FontAdapter extends RecyclerView.Adapter<FontAdapter.FontHolder> {
    private Context mContext;
    private ArrayList<Bitmap> imageArrayList;
    private PicturesAdapter.onClickItemImage clickItemImage;

    public void setClickItemImage(PicturesAdapter.onClickItemImage clickItemImage) {
        this.clickItemImage = clickItemImage;
    }

    public FontAdapter(Context mContext, ArrayList<Bitmap> imageArrayList) {
        this.mContext = mContext;
        this.imageArrayList = imageArrayList;
    }

    @Override
    public FontAdapter.FontHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new FontHolder(LayoutInflater.from(mContext).inflate(R.layout.item_layout_set_font, parent, false));
    }


    @Override
    public void onBindViewHolder(FontAdapter.FontHolder holder, int position) {
            holder.imageView.setImageBitmap(imageArrayList.get(position));


    }

    @Override
    public int getItemCount() {
        return 34;
    }

    public class FontHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        private LinearLayout linearLayout;

        public FontHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_set_font);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_set_layout_font);
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
