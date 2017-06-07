package com.example.trang.tapbitextgram.view.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.trang.tapbitextgram.view.fragment.BackgroundFragment;
import com.example.trang.tapbitextgram.view.fragment.CanvasFragment;
import com.example.trang.tapbitextgram.view.fragment.FiltersFragment;
import com.example.trang.tapbitextgram.view.fragment.FramesFragment;
import com.example.trang.tapbitextgram.view.fragment.StickerFragment;
import com.example.trang.tapbitextgram.view.fragment.TemPlatesFragment;

/**
 * Created by Trang on 6/5/2017.
 */

public class ShowAllViewPager extends FragmentStatePagerAdapter {

    public ShowAllViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new CanvasFragment();
                break;
            case 1:
                fragment = new TemPlatesFragment();
                break;
            case 2:
                fragment = new BackgroundFragment();
                break;
            case 3:
                fragment = new StickerFragment();
                break;
            case 4:
                fragment = new FiltersFragment();
                break;
            case 5:
                fragment = new FramesFragment();
                break;
        }
        return fragment;
    }


    @Override
    public int getCount() {
        return 6;
    }
}
