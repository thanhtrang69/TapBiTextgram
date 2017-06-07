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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.trang.tapbitextgram.R;
import com.example.trang.tapbitextgram.adapter.FiltersAdapter;
import com.example.trang.tapbitextgram.enity.Key;
import com.example.trang.tapbitextgram.manager.PicturesManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Trang on 6/5/2017.
 */

public class FiltersFragment extends Fragment implements FiltersAdapter.onClickItemImage {
    private View view;
    private RecyclerView recyclerView;
    private PicturesManager picturesManager;
    private ArrayList<Bitmap> bitmapArrayList;
    private FiltersAdapter filtersAdapter;
    private ImageView ivShowFilter;
    private ImageView ivShow;
    private SeekBar seekBar;
    private Button button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_filter, container, false);
        initView();
        return view;
    }


    public FiltersFragment() {
    }

    private void initView() {
        ivShow = (ImageView) getActivity().findViewById(R.id.iv_show);
        ivShowFilter = (ImageView) getActivity().findViewById(R.id.iv_filter_show);
        seekBar = (SeekBar) view.findViewById(R.id.sb_filter);
        button = (Button) view.findViewById(R.id.bt_filter);
        picturesManager = PicturesManager.getInstance();
        bitmapArrayList = picturesManager.getBitmapArrayFilters();
        recyclerView = (RecyclerView) view.findViewById(R.id.rl_filter);
        filtersAdapter = new FiltersAdapter(getActivity(), bitmapArrayList);
        GridLayoutManager gis = new GridLayoutManager(getActivity(), 1);
        gis.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(gis);
        recyclerView.setAdapter(filtersAdapter);
        filtersAdapter.setClickItemImage(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivShow != null) {
                    ivShow.setAlpha(255);
                } else {
                    return;
                }
            }
        });

    }

    @Override
    public void onClickItem(int position) {


        String[] filters;
        try {
            filters = getActivity().getAssets().list(Key.FILTER);
            InputStream inputStream = getActivity().getAssets().open(Key.FILTER + "/" + filters[position]);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ivShowFilter.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        seekBar.setMax(255);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setProgress(progress);
                ivShow.setAlpha(255 - seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
