package com.example.trang.tapbitextgram.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trang.tapbitextgram.R;

/**
 * Created by Trang on 6/3/2017.
 */

public class DesignsFragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_saved_designs, container, false);
        initView();
        return view;
    }

    public DesignsFragment() {
    }

    private void initView() {

    }
}
