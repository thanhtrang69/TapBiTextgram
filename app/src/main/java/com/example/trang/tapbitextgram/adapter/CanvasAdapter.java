package com.example.trang.tapbitextgram.adapter;

import android.content.Context;
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

public class CanvasAdapter extends RecyclerView.Adapter<CanvasAdapter.CanvasHolder> {
    private Context mContext;
    private ArrayList<Image> imageArrayList;
    private onClickItemImage clickItemImage;

    public void setClickItemImage(onClickItemImage clickItemImage) {
        this.clickItemImage = clickItemImage;
    }

    public CanvasAdapter(Context mContext) {
        this.mContext = mContext;
        initData();
    }

    @Override
    public CanvasAdapter.CanvasHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new CanvasHolder(LayoutInflater.from(mContext).inflate(R.layout.item_canvas, parent, false));
    }

    private void initData() {
        imageArrayList = new ArrayList<>();
        imageArrayList.add(new Image(R.drawable.ic_add_a_photo_black_24dp));
        imageArrayList.add(new Image(R.drawable.ic_add_box_black_24dp));
        imageArrayList.add(new Image(R.drawable.ic_book_black_24dp));
        imageArrayList.add(new Image(R.drawable.ic_autorenew_black_24dp));
    }

    @Override
    public void onBindViewHolder(CanvasAdapter.CanvasHolder holder, int position) {
        holder.imageView.setImageResource(imageArrayList.get(position).getUrlImg());

    }

    @Override
    public int getItemCount() {
        Log.d("yyyyyyyyyyyy", "getItemCount: "+imageArrayList.size());
        return imageArrayList.size();
    }

    public class CanvasHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private LinearLayout linearLayout;

        public CanvasHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_canvas);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll_canvas);
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
