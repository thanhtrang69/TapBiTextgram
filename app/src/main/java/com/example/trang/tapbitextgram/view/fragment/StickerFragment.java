package com.example.trang.tapbitextgram.view.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.trang.tapbitextgram.R;
import com.example.trang.tapbitextgram.adapter.CanvasAdapter;
import com.example.trang.tapbitextgram.adapter.PicturesAdapter;
import com.example.trang.tapbitextgram.adapter.StickerAdapter;
import com.example.trang.tapbitextgram.adapter.TemPlatesAdapter;
import com.example.trang.tapbitextgram.manager.PicturesManager;
import com.example.trang.tapbitextgram.view.BubbleInputDialog;
import com.example.trang.tapbitextgram.view.BubbleTextView;
import com.example.trang.tapbitextgram.view.StickerView;

import java.util.ArrayList;

/**
 * Created by Trang on 6/5/2017.
 */

public class StickerFragment extends Fragment implements StickerAdapter.onClickItemImage{
    private View view;
    private StickerAdapter stickerAdapter;
    private RecyclerView recyclerView;
    private PicturesManager picturesManager;
    private ArrayList<Bitmap> bitmapArrayList;
    private ArrayList<View> mView;
    private RelativeLayout mContentRootView;
    private BubbleTextView mCurrentEditTextView;
    private StickerView mCurrentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_templates, container, false);
        initView();
        return view;
    }


    public StickerFragment() {
    }

    private void initView() {
        mContentRootView = (RelativeLayout) getActivity().findViewById(R.id.rl_iv_content);
        mView = new ArrayList<>();

        picturesManager = PicturesManager.getInstance();
        bitmapArrayList = picturesManager.getBitmapArrayListSticker();
        Log.d("aaaaaaaaaaaa", "initView: " + bitmapArrayList.size());
        recyclerView = (RecyclerView) view.findViewById(R.id.rl_templates);
        stickerAdapter = new StickerAdapter(getActivity(), bitmapArrayList);
        GridLayoutManager gis = new GridLayoutManager(getActivity(), 1);
        gis.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(gis);
        recyclerView.setAdapter(stickerAdapter);

        stickerAdapter.setClickItemImage(this);
    }



    private void setCurrentEdit(StickerView stickerView) {
        if (mCurrentView != null) {
            mCurrentView.setInEdit(false);
        }
        if (mCurrentEditTextView != null) {
            mCurrentEditTextView.setInEdit(false);
        }
        mCurrentView = stickerView;
        stickerView.setInEdit(true);
    }

    @Override
    public void onClickItem(int position) {
        final StickerView stickerView = new StickerView(getActivity());
        stickerView.setBitmap(bitmapArrayList.get(position));
//        stickerView.setImageResource(R.mipmap.ic_cat);
        stickerView.setOperationListener(new StickerView.OperationListener() {
            @Override
            public void onDeleteClick() {
                mView.remove(stickerView);
                mContentRootView.removeView(stickerView);
            }

            @Override
            public void onEdit(StickerView stickerView) {
                if (mCurrentEditTextView != null) {
                    mCurrentEditTextView.setInEdit(false);
                }
                mCurrentView.setInEdit(false);
                mCurrentView = stickerView;
                mCurrentView.setInEdit(true);
            }

            @Override
            public void onTop(StickerView stickerView) {
//                int position = mView.indexOf(stickerView);
//                if (position == mView.size() - 1) {
//                    return;
//                }
//                StickerView stickerTemp = (StickerView) mView.remove(position);
//                mView.add(mView.size(), stickerTemp);
            }
        });

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mContentRootView.addView(stickerView, lp);
        mView.add(stickerView);
        setCurrentEdit(stickerView);
    }
}
