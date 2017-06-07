package com.example.trang.tapbitextgram.enity;

import android.net.Uri;

/**
 * Created by Trang on 6/7/2017.
 */

public class ImageLoadSdCard {
    private Uri urlImage;
    private String name;

    public String getName() {
        return name;
    }

    public ImageLoadSdCard(Uri urlImage, String name) {
        this.urlImage = urlImage;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageLoadSdCard(Uri urlImage) {
        this.urlImage = urlImage;
    }

    public Uri getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(Uri urlImage) {
        this.urlImage = urlImage;
    }

    public ImageLoadSdCard() {
    }
}
