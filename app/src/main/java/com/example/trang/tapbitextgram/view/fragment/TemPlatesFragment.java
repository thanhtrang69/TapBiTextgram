package com.example.trang.tapbitextgram.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trang.tapbitextgram.R;
import com.example.trang.tapbitextgram.activity.ScreenWorksActivity;
import com.example.trang.tapbitextgram.adapter.CanvasAdapter;
import com.example.trang.tapbitextgram.adapter.PicturesAdapter;
import com.example.trang.tapbitextgram.adapter.TemPlatesAdapter;
import com.example.trang.tapbitextgram.enity.Key;
import com.example.trang.tapbitextgram.manager.PicturesManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Trang on 6/5/2017.
 */

public class TemPlatesFragment extends Fragment implements TemPlatesAdapter.onClickItemImageTemPlates {
    private View view;
    private TemPlatesAdapter temPlatesAdapter;
    private RecyclerView recyclerView;
    private PicturesManager picturesManager;
    private ArrayList<Bitmap> bitmapArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_templates, container, false);
        initView();
        return view;
    }


    public TemPlatesFragment() {
    }

    private void initView() {
        picturesManager = PicturesManager.getInstance();
        bitmapArrayList = picturesManager.getBitmapArrayListBackground();
        Log.d("aaaaaaaaaaaa", "initView: " + bitmapArrayList.size());
        recyclerView = (RecyclerView) view.findViewById(R.id.rl_templates);
        temPlatesAdapter = new TemPlatesAdapter(getActivity(), bitmapArrayList);
        GridLayoutManager gis = new GridLayoutManager(getActivity(), 1);
        gis.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(gis);
        recyclerView.setAdapter(temPlatesAdapter);
        temPlatesAdapter.setClickItemImage(this);
    }


    @Override
    public void onClickItem(int position) {
        String[] backgrounds;
        try {
            backgrounds = getActivity().getAssets().list(Key.BACKGROUNDS_FACE);
            if (backgrounds[position].length()<7){
                InputStream inputStream = getActivity().getAssets().open(Key.BACKGROUNDS_FACE + "/" + backgrounds[position]);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ((ScreenWorksActivity) getActivity()).setImageCanvas(bitmap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
