package com.example.trang.tapbitextgram.view.viewpager;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.example.trang.tapbitextgram.App;
import com.example.trang.tapbitextgram.R;
import com.example.trang.tapbitextgram.view.fragment.DesignsFragment;
import com.example.trang.tapbitextgram.view.fragment.PictruresFragment;

/**
 * Created by Trang on 6/3/2017.
 */

public class MainSreenViewPager extends FragmentStatePagerAdapter {
    public MainSreenViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new PictruresFragment();
                break;
            case 1:
                fragment = new DesignsFragment();
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "SAVED PICTURE";
                break;
            case 1:
                title = "SAVED DESIGN";
                break;
        }
        return title;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
