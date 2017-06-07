package com.example.trang.tapbitextgram.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trang.tapbitextgram.R;
import com.example.trang.tapbitextgram.adapter.FramesAdapter;
import com.example.trang.tapbitextgram.enity.Key;
import com.example.trang.tapbitextgram.manager.PicturesManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Trang on 6/5/2017.
 */

public class FramesFragment extends Fragment implements FramesAdapter.onClickItemImage, View.OnClickListener {
    private View view;
    private RecyclerView recyclerView;
    private PicturesManager picturesManager;
    private ArrayList<Bitmap> bitmapArrayList;
    private FramesAdapter framesAdapter;
    private ImageView ivShow;
    private TextView tvEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_frames, container, false);
        initView();
        return view;
    }


    public FramesFragment() {
    }

    private void initView() {
        tvEdit = (TextView) view.findViewById(R.id.tv_cancel);
        ivShow = (ImageView) getActivity().findViewById(R.id.iv_filter_frame);
        picturesManager = PicturesManager.getInstance();
        bitmapArrayList = picturesManager.getBitmapArrayFrames();
        recyclerView = (RecyclerView) view.findViewById(R.id.rl_frames);
        framesAdapter = new FramesAdapter(getActivity(), bitmapArrayList);
        GridLayoutManager gis = new GridLayoutManager(getActivity(), 1);
        gis.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(gis);
        recyclerView.setAdapter(framesAdapter);

        framesAdapter.setClickItemImage(this);
        tvEdit.setOnClickListener(this);
    }

    @Override
    public void onClickItem(int position) {
        String[] frames;
        try {
            frames = getActivity().getAssets().list(Key.FRAME);
            InputStream inputStream = getActivity().getAssets().open(Key.FRAME + "/" + frames[position]);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ivShow.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                ivShow.setImageBitmap(null);
                break;

        }
    }
}
