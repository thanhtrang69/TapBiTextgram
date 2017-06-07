package com.example.trang.tapbitextgram.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.trang.tapbitextgram.R;
import com.example.trang.tapbitextgram.activity.DeleteItemActivity;
import com.example.trang.tapbitextgram.adapter.PicturesAdapter;
import com.example.trang.tapbitextgram.enity.ImageLoadSdCard;
import com.example.trang.tapbitextgram.enity.Key;
import com.example.trang.tapbitextgram.manager.ImageManager;
import com.example.trang.tapbitextgram.manager.PicturesManager;

import java.util.ArrayList;

/**
 * Created by Trang on 6/3/2017.
 */

public class PictruresFragment extends Fragment implements PicturesAdapter.onClickItemImage {
    private View view;
    private RecyclerView rlPictureFt;
    private PicturesAdapter picturesAdapter;
    private ImageManager imageManager;
    private ArrayList<ImageLoadSdCard> sdCardArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_saved_pictures, container, false);
        initView();
        return view;
    }

    public PictruresFragment() {
    }
    private void initView() {
        imageManager = ImageManager.getInstance();
        sdCardArrayList = imageManager.loadImageExternalStorage();
        rlPictureFt = (RecyclerView) view.findViewById(R.id.rl_pictures);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rlPictureFt.setItemAnimator(new DefaultItemAnimator());
        rlPictureFt.setLayoutManager(gridLayoutManager);
        picturesAdapter = new PicturesAdapter(getActivity(), sdCardArrayList);
        rlPictureFt.setAdapter(picturesAdapter);
        picturesAdapter.setClickItemImage(this);
    }

    @Override
    public void onClickItem(int position) {
        Intent intent = new Intent(getActivity(), DeleteItemActivity.class);
        intent.putExtra(Key.POSITION, position);
        startActivityForResult(intent, Key.DELETE_OK);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Key.DELETE_OK && resultCode == getActivity().RESULT_OK && data != null) {
            initView();
        }
    }

    @Override
    public void onResume() {
        initView();
        super.onResume();
    }
}
