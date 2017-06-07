package com.example.trang.tapbitextgram.manager;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Trang on 6/3/2017.
 */

public class PicturesManager {
    private static PicturesManager instance;
    private ArrayList<Bitmap> bitmapArrayListSticker;
    private ArrayList<Bitmap> bitmapArrayListBackground;
    private ArrayList<Bitmap> bitmapArrayFilters;
    private ArrayList<Bitmap> bitmapArrayFrames;
    private ArrayList<Bitmap> bitmapArrayFont;

    public ArrayList<Bitmap> getBitmapArrayFont() {
        return bitmapArrayFont;
    }

    public void setBitmapArrayFont(ArrayList<Bitmap> bitmapArrayFont) {
        this.bitmapArrayFont = bitmapArrayFont;
    }

    public ArrayList<Bitmap> getBitmapArrayFrames() {
        return bitmapArrayFrames;
    }

    public void setBitmapArrayListSticker(ArrayList<Bitmap> bitmapArrayListSticker) {
        this.bitmapArrayListSticker = bitmapArrayListSticker;
    }

    public ArrayList<Bitmap> getBitmapArrayListSticker() {
        return bitmapArrayListSticker;
    }

    public void setBitmapArrayFrames(ArrayList<Bitmap> bitmapArrayFrames) {
        this.bitmapArrayFrames = bitmapArrayFrames;
    }

    public ArrayList<Bitmap> getBitmapArrayFilters() {
        return bitmapArrayFilters;
    }

    public void setBitmapArrayFilters(ArrayList<Bitmap> bitmapArrayFilters) {
        this.bitmapArrayFilters = bitmapArrayFilters;
    }

    public ArrayList<Bitmap> getBitmapArrayListBackground() {
        return bitmapArrayListBackground;
    }

    public void setBitmapArrayListBackground(ArrayList<Bitmap> bitmapArrayListBackground) {
        this.bitmapArrayListBackground = bitmapArrayListBackground;
    }

    public ArrayList<Bitmap> getBitmapArrayList() {
        return bitmapArrayListSticker;
    }

    public void setBitmapArrayList(ArrayList<Bitmap> bitmapArrayList) {
        this.bitmapArrayListSticker = bitmapArrayList;
    }

    public static PicturesManager getInstance() {
        if (instance == null) {
            instance = new PicturesManager();
        }
        return instance;
    }

}
